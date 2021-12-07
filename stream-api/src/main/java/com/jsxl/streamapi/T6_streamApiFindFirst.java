package com.jsxl.streamapi;

import java.util.Optional;

public class T6_streamApiFindFirst {
    public static void main(String[] args) {
//这是一个termimal兼short-circuiting操作，它总是返回Stream的第一个元素或者空。这里比较重点的是它的返回值类型Optional:
// 这也是一个模仿 Scala 语言中的概念，作为一个容器，它可能含有某值，或者不包含,使用它的目的是尽可能避免NullPointerException。

// Optional 的两个用例:以下两组示例是等价的
        String text = "";
        // Java 8
        Optional.ofNullable(text).ifPresent(System.out::println);

        // Pre-Java 8
        if (text != null) {
            System.out.println(text);
        }

        // Java 8
        Integer integer = Optional.ofNullable(text).map(String::length).orElse(-1);
        System.out.println(integer);

        // Pre-Java 8
//        1.data?data(true):null(false)
//        2.var logs=wx.getStorageSync()||[] //为真返回缓存，为假返回空数组
//        3.logs==wx.setStorageSync('logs')&&alert('')//小程序中变量logs==缓存中的logs则执行alert

        Integer i = (text != null) ? text.length() : -1;
        System.out.println(i);
//        　在更复杂的if (xx != null)的情况中，使用Optional代码的可读性更好，而且它提供的是编译时检查，
//        能极大的降低NPE这种Runtime Exception 对程序的影响，或者迫使程序员更早的在编码阶段处理空值问题，而不是留到运行时再发现和调试。
//　Stream中的findAny、max/min、reduce等方法等返回Optional值。还有例如IntStream.average()返回OptionalDouble等等。
    }
}
