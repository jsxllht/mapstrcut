package com.jsxl.lambda;


import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


interface MyFun{
    Integer getValue(Integer integer);
}
interface MyFunction{
    String getValue(String string);
}
interface MyFunction2<T,R>{
    Long getValue(Long l1, Long l2);
}
public class T4_Lambda_Predicate {
    List<Employee> employees = Arrays.asList(
            new Employee("张三",22,999.99),
            new Employee("张三",12,666.99),
            new Employee("张三",23,888.99),
            new Employee("张三",35,111.99),
            new Employee("张三",24,333.99)
    );

    //对一个数进行运算
    @Test
    public void test1(){
        Integer num = operation(100,x -> x * x);
        System.out.println(num);

        System.out.println(operation(200,y -> y + 200));
    }
    public Integer operation(Integer num , MyFun mf){
        return mf.getValue(num);
    }

    @Test
    public void test2(){
        Collections.sort(employees,(e1 ,e2)->{
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return -Integer.compare(e1.getAge(),e2.getAge());//负号控制升序降序
            }
        });
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void test3(){
        System.out.println(strHandler("\t函\t数\t式\t接\t口\t", str -> str.trim()));

        System.out.println(strHandler("abcdef", e -> e.toUpperCase()));
    }

    //处理字符串
    public String strHandler(String str , MyFunction mf){
        return mf.getValue(str);
    }

    @Test
    public void test4(){
        operation(100L,200L,(x,y) -> x + y);
    }
    //对2个Long类型进行处理
    public void operation(Long l1, Long l2 ,MyFunction2<Long, Long> mf){
        System.out.println(mf.getValue(l1,l2));
    }

}


