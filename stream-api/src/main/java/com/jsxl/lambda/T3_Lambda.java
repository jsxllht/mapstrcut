package com.jsxl.lambda;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class T3_Lambda {
    //无参无返回值
    //（）->System.out.println(x);
    @Test
    public void test1() {
        int num = 0;//JDK1.7以前，必须是final
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!" + num); //num++报错
            }
        };
        r.run();
        System.out.println("-------------------");
        Runnable r1 = () -> System.out.println("Hello World!");
    }

    //  有参数无返回值
    //（x）->System.out.println(x);
    @Test
    public void test2() {
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("供给型接口");
    }

    //  有参数有返回值
    @Test
    public void test3() {
        Comparator<Integer> com = (x,y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        };
    }

    //如果只有一条语句大括号和return都可以不写
    @Test
    public void test4() {
        Comparator<Integer> com = (x,y) ->  Integer.compare(x,y);
    }

    //语法格式六:Lambda的参数列表的数据类型可以省略不写|因为JVM通过上下文推断出，数据类型，即“类型推断”
    @Test
    public void test5() {
//        String[] strs;
//        strs = {"aaa","bbb","ccc"};//报错

        List<String> list = new ArrayList<>();//类型推断

        show(new HashMap<>());//JDK1.7报错
    }

    public void show(Map<String,Integer> map){

    }
}
