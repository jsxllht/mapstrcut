package com.jsxl.Interface_java8;

//public class SubClass extends MyClass implements Myfun {
//
//}
public class SubClass implements Myfun,MyInterface{//2个接口都有这个方法，实现2这个2个接口的类必须实现这个方法

    @Override
    public String getName() {
        return Myfun.super.getName();
    }
}
