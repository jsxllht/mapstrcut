package com.jsxl.stream_api;

import org.junit.Test;

import java.util.Optional;

public class T5_Optional {
    @Test
    public void test1(){
        Optional<Employee> op = Optional.of(null);
        Employee emp = op.get();
        System.out.println(emp);
    }
    @Test
    public void test2(){
        Optional<Employee> op = Optional.empty();
        System.out.println(op.get());
    }
    @Test
    public void test3(){
        Optional<Employee> op = Optional.ofNullable(null);
//        if(op.isPresent()){
//            System.out.println(op.get());
//        }
//        Employee emp = op.orElse(new Employee("张三",18,888.88,Status.FREE));
//        System.out.println(emp);
        Employee emp1 = op.orElseGet(() -> new Employee());
        System.out.println(emp1);
    }

    @Test
    public void test4(){
        Optional<Employee> op = Optional.ofNullable(new Employee("张三",18,888.88,Status.FREE));
//        Optional<String> str = op.map(e -> e.getName());
//        System.out.println(str);
        Optional<String> str2 = op.flatMap(e -> Optional.of(e.getName()));
        System.out.println(str2.get());
    }
}
