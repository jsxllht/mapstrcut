package com.jsxl.streamapi;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class T7_streamApiReduce {
    public static void main(String[] args) {
//        List<Person> javaProgrammers = new ArrayList<Person>() {
//            {
//                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 2000, 18));
//                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 2371, 55));
//                add(new Person("Floyd", "Donny", "Java programmer", "male", 3322, 25));
//                add(new Person("Sindy", "Jonie", "Java programmer", "female", 35020, 15));
//                add(new Person("Vere", "Hervey", "Java programmer", "male", 2272, 25));
//                add(new Person("Maude", "Jaimie", "Java programmer", "female", 2057, 87));
//                add(new Person("Shawn", "Randall", "Java programmer", "male", 3120, 99));
//                add(new Person("Jayden", "Corrina", "Java programmer", "female", 345, 25));
//                add(new Person("Palmer", "Dene", "Java programmer", "male", 3375, 14));
//                add(new Person("Addison", "Pam", "Java programmer", "female", 3426, 20));
//            }
//        };
//    }
//        //这个方法的主要作用是把Stream元素组合起来。它提供一个起始值（种子），然后依照运算规则（BinaryOperator），
//        // 和前面Stream的第一个、第二个、第n个元素组合。从这个意义上说，字符串拼接、数值的 sum、min、max、average都是特殊的reduce。
//        // 例如Stream的sum就相当于：
// reduce 的用例

// 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println(concat);

// 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println(minValue);
// 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println(sumValue);
// 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println(sumValue);
// 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);
        System.out.println(concat);
    }
}
