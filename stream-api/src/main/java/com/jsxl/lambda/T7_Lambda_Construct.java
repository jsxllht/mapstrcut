package com.jsxl.lambda;

import org.junit.Test;

import java.util.function.Function;
import java.util.function.Supplier;

public class T7_Lambda_Construct {

    //构造器引用
    public void test1(){
        Supplier<Employee> sup1 = () -> new Employee();
        //构造器引用方式
        Supplier<Employee> sup2 = Employee::new;
        Employee employee = sup2.get();
        System.out.println(employee);
    }
    @Test
    public void test2(){
        Function<String,Employee> fun1 = (x) -> new Employee(x);
        Function<String,Employee> fun2 = Employee::new;
        System.out.println(fun2.apply("csdn"));
    }
    @Test
    public void test3(){
        Function<Integer,String[]> fun1 = (x) -> new String[x];
        System.out.println(fun1.apply(10).length);

        Function<Integer,String[]> fun2 = String[]::new;
        System.out.println(fun1.apply(20).length);
    }

}
