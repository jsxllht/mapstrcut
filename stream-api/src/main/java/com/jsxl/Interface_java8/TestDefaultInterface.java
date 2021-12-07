package com.jsxl.Interface_java8;

public class TestDefaultInterface {
    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        System.out.println(subClass.getName());//类优先原则

        MyInterface.show();
    }
}
