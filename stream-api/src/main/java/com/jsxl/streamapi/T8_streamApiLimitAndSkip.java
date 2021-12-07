package com.jsxl.streamapi;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class T8_streamApiLimitAndSkip {
    public static void main(String[] args) {
//       testLimitAndSkip();
//        limitAndSkipSorted();
        optimizeLimitAndSkip();
    }
//limit返回Stream的前面n个元素；skip则是扔掉前n个元素（它是由一个叫 subStream的方法改名而来）。

//limit 和 skip 对运行次数的影响
        public static void testLimitAndSkip() {
            List<Person> persons = Lists.newArrayList();
            for (int i = 1; i <= 10000; i++) {
                Person person = new Person(i, "name" + i);
                persons.add(person);
            }
            List<String> personList2 = persons.stream().
                    map(Person::getName).limit(10).skip(3).collect(Collectors.toList());
            System.out.println(personList2);
        }
        private static class Person {
            public int no;
            private String name;
            public Person (int no, String name) {
                this.no = no;
                this.name = name;
            }
            public String getName() {
//                System.out.println("getName="+name);
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



//这是一个有10，000个元素的Stream，但在short-circuiting操作limit和skip的作用下，
// 管道中map操作指定的getName()方法的执行次数为 limit 所限定的10次，而最终返回结果在跳过前3个元素后只有后面7个返回。

//有一种情况是limit/skip无法达到short-circuiting目的的，就是把它们放在Stream的排序操作后，
// 原因跟sorted这个intermediate操作有关：此时系统并不知道Stream排序后的次序如何，
// 所以sorted中的操作看上去就像完全没有被limit或者skip一样。

    // limit 和 skip 对 sorted 后的运行次数无影响

    public static void limitAndSkipSorted(){
        List<Person> persons = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            Person person = new Person(i, "name" + i);
            persons.add(person);
        }
        List<Person> personList2 = persons.stream().sorted((p1, p2) ->{
                System.out.println("---p1---"+p1.getName());
                System.out.println("----p2--"+p2.getName());
                return p1.getName().compareTo(p2.getName());}).limit(4).collect(Collectors.toList());
        System.out.println(personList2);
        personList2.forEach(System.out::println);
//即虽然最后的返回元素数量是 2，但整个管道中的 sorted 表达式执行次数没有像前面例子相应减少。最后有一点需要注意的是，对一个parallel的Stream 管道来说，如果其元素是有序的，那么limit操作的成本会比较大，因为它的返回对象必须是前n个也有一样次序的元素。取而代之的策略是取消元素间的次序，或者不要用parallel Stream。
    }

    //对Stream的排序通过sorted进行，它比数组的排序更强之处在于你可以首先对Stream进行各类map、filter、limit、skip甚至distinct来减少元素数量后再排序，这能帮助程序明显缩短执行时间。例如：

// 优化：排序前进行 limit 和 skip

    public static void optimizeLimitAndSkip(){
        List<Person> persons = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            Person person = new Person(i, "name" + i);
            persons.add(person);
        }
        List<Person> personList2 = persons.stream().limit(2).sorted((p1, p2) -> p1.getName().compareTo(p2.getName())).collect(Collectors.toList());
        System.out.println(personList2);
    }
// 当然，这种优化是有business logic上的局限性的：即不要求排序后再取值。
}
