package com.jsxl.streamapi;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class T3_streamApi {
    public static void main(String[] args) {
//        (1).Intermediate 操作
//        map/flatMap
//　我们先来看map，它的作用就是把inputStream的每个元素映射成outputStream的另外一个元素，例如：
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().map(n -> n * n)
                .collect(Collectors.toList());

//从上面例子可以看出，map生成的是个1:1映射，每个输入元素都按照规则转换成为另外一个元素。还有一些场景，是一对多映射关系的，这时需要flatMap，例如：
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );


        List<Integer> collect = inputStream.
                flatMap((childList) -> childList.stream()).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println("----");

//        Stream<Integer> integerStream = inputStream.
//                flatMap((childList) -> childList.stream());
//        System.out.println(integerStream);
//        integerStream.forEach(System.out::println);
        //流只能被操作一次，否则报错stream has already been operated upon or closed
        //integerStream.forEach(System.out::println);
//　flatMap把inputStream中的层级结构 扁平化，就是将最底层元素抽出来放到一起，最终output的新Stream里面已经没有List了，都是直接的数字。
    }
}
