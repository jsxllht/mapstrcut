package com.jsxl.streamapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class T2_streamApi {
    public static void main(String[] args) {
        Stream stream = Stream.of("a", "b", "c");

        // 1. Array
        String[] strArray1 = (String[]) stream.toArray(String[]::new);
        System.out.println(strArray1);

        // 2. Collection
        List<String> list1 = (List<String>) stream.collect(Collectors.toList());
        List<String> list2 = (List<String>) stream.collect(Collectors.toCollection(ArrayList::new));
        Set set1 = (Set)stream.collect(Collectors.toSet());
        Stack stack1 = (Stack)stream.collect(Collectors.toCollection(Stack::new));

        // 3. String
        String str = stream.collect(Collectors.joining()).toString();
    }
}
