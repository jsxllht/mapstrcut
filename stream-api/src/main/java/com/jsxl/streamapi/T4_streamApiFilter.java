package com.jsxl.streamapi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class T4_streamApiFilter {
    //filter对原始Stream进行某项测试，通过测试的元素被留下来生成一个新Stream。
    public static void main(String[] args) {
        // 留下偶数
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] evens =
                Stream.of(sixNums).filter(n -> n % 2 == 0).toArray(Integer[]::new);
        List<Integer> integers = Arrays.asList(evens);
        System.out.println(integers);
        System.out.println(Arrays.toString(evens));
    }

}
