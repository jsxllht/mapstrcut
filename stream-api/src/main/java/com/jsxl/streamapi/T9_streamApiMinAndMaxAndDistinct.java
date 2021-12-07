package com.jsxl.streamapi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class T9_streamApiMinAndMaxAndDistinct {
    public static void main(String[] args) throws IOException {
//min和max的功能也可以通过对Stream元素先排序，再findFirst来实现，但前者的性能会更好为O(n)，而sorted的成本是O(nlogn)。
// 同时它们作为特殊的reduce方法被独立出来也是因为求最大最小值是很常见的操作。

// 找出最长一行的长度
        BufferedReader br = new BufferedReader(new FileReader("D:\\println.txt"));
        Stream<String> lines = br.lines();
        IntStream intStream = lines.mapToInt(String::length);
        OptionalInt max = intStream.max();
        int asInt = max.getAsInt();
        System.out.println(lines);
        System.out.println(intStream);
        System.out.println(max);
        System.out.println(asInt);
//        int longest = br.lines().mapToInt(String::length).max().getAsInt();
        br.close();
//        System.out.println(longest);

//下面的例子则使用distinct来找出不重复的单词。
        BufferedReader br1 = new BufferedReader(new FileReader("D:\\println.txt"));
        // 找出全文的单词，转小写，并排序
        List<String> words = br1.lines().flatMap(line -> Stream.of(line.split(" "))).
                filter(word -> word.length() > 0).map(String::toLowerCase).distinct().sorted()
                .collect(Collectors.toList());
        br.close();
        System.out.println(words);
    }

}