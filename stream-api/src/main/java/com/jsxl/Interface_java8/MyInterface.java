package com.jsxl.Interface_java8;

public interface MyInterface {
    default String getName(){
        return "java8 新特性MyInterface";
    }
    public static void show(){
        System.out.println("接口中的静态方法");
    }
}
