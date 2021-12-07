package com.jsxl.streamapi;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class T10_streamApiMatch {
    public static class Person {
        public int no;
        private String name;
        private int age;
        public Person (int no, String name,int age) {
            this.no = no;
            this.name = name;
            this.age=age;
        }
        public int getAge() {
            return age;
        }
        public String getName() {
                System.out.println("getName="+name);
            return name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    public static void main(String[] args) {
//　Stream有三个match方法，从语义上说：
//
//　(1).allMatch：Stream 中全部元素符合传入的 predicate，返回 true;
//　(2).anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true;
//　(3).noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true.
//
//　它们都不是要遍历全部元素才能返回结果。例如allMatch只要一个元素不满足条件，就skip剩下的所有元素，返回false。对清单13中的Person类稍做修改，加入一个age属性和getAge方法。
        // 使用 Match
        List<Person> persons = new ArrayList();
        persons.add(new Person(1, "name" + 1, 10));
        persons.add(new Person(2, "name" + 2, 21));
        persons.add(new Person(3, "name" + 3, 34));
        persons.add(new Person(4, "name" + 4, 6));
        persons.add(new Person(5, "name" + 5, 55));

        boolean isAllAdult = persons.stream().allMatch(p -> p.getAge() > 18);
        System.out.println("All are adult? " + isAllAdult);
        boolean isThereAnyChild = persons.stream().anyMatch(p -> p.getAge() < 12);
        System.out.println("Any child? " + isThereAnyChild);

    }
}
