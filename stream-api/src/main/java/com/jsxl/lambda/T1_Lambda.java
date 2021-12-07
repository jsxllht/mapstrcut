package com.jsxl.lambda;

import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.TreeSet;


public class T1_Lambda {
    @Test//原来的匿名内部类
    public void test(){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
    }

    //lambda表达式
    @Test
    public void testLambda(){
    Comparator<Integer> comparator = (x , y) -> Integer.compare(x,y);
        TreeSet treeSet = new TreeSet<>(comparator);
    }
}
