package com.jsxl.lambda;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class T5_Lambda_Predicate {
    //消费型接口 Consumer<T>
    @Test
    public void test1(){
        A(20,m -> System.out.println("她"+m+"岁了"));
    }
    public void A(double age, Consumer<Double> consumer){
        consumer.accept(age);
    }
    //供给型接口 Supplier<T>
    @Test
    public void test2(){
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        numList.forEach(System.out::println);
    }

    //产生指定个数的整数，并放入集合中
    public List<Integer> getNumList(Integer num, Supplier<Integer> supplier) {
        List<Integer> list = Lists.newArrayList();

        for (int i = 0; i < num; i++) {
            Integer integer = supplier.get();
            list.add(integer);
        }
        return list;
    }

    //函数型接口 Function<T, R>
    @Test
    public void Test3(){
        System.out.println(StrHandler("ABCDEF", (s) -> s.toLowerCase()));
    }
    //需求：用于处理字符串
    public String StrHandler(String str, Function<String,String> fun){
        return fun.apply(str);
    }

    //断言型接口
    @Test
    public void test4(){
        List<String> str = Arrays.asList("A", "BB", "CCC", "DDD");
        filterStr( str , m -> m.length()>=3).forEach(System.out::println);
    }
    //需求满足需求的字符串放入集合中
    public List<String> filterStr(List<String> str, Predicate<String> pre){
        List<String> strList = Lists.newArrayList();
        for (String s : str) {
            if(pre.test(s)){
                strList.add(s);
            }
        }
        return strList;
    }
}
