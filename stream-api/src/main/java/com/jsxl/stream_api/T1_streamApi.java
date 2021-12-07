package com.jsxl.stream_api;

import com.jsxl.lambda.Employee;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class T1_streamApi {
    //创建Stream
    @Test
    public void test(){
        //可以通过Collection 系列集合提供的stream() 或 parallelStream
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //通过Arrays 中静态方法 stream() 获取数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(emps);

        //通过Stream 类中的静态方法of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        //创建无限流 --迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.limit(10).forEach(System.out::println);

        //生成
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三",22,999.99),
            new Employee("张三",12,666.99),
            new Employee("张三",23,888.99),
            new Employee("张三",23,888.99),
            new Employee("张三",35,111.99),
            new Employee("张三",24,333.99)
    );
    //中间操作
    /*
    筛选与切片
        filter——接收Lambda ,从流中排除某些元素。
        limit——截断流,使其元素不超过给定数量。
        skip(n)—跳过元素，返回一个扔掉了前n个元素的流。若渣中元素不足n 个，则返回一个空流。与limit(n)互补
        distinct—筛选，通过流所生成元素的 hashcode()和equals()去除重复元素
    */

    //内部迭代：迭代操作由Stream API 完成
    @Test
    public void Test1(){
        //中间操作不会执行任何操作
        Stream<Employee> stream = employees.stream()
                .filter(e -> {
                    System.out.println("Stream API 的中间操作");
                    return e.getAge() > 20;
                });
        //终止操作：一次性执行完全部内容，即“惰性求值”
        stream.forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test2() {
        Iterator<Employee> it = employees.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test//短路 &&　||
    public void test3() {
          employees.stream()
                .filter(e -> {
                    System.out.println("短路");
                    return e.getSalary() > 444.44;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    @Test//skip跳过前2条
    public void test4() {
        employees.stream()
                .filter(e -> e.getSalary() > 444.44)
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }

    /*
        映射
        map—接收Lambda ，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        flatMap—接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
    */
    @Test
    public void test5() {
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");

        list.stream()
                .map(str -> str.toUpperCase())
                .forEach(System.out::println);

        System.out.println("-----------------------");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        System.out.println("-----------------------");

        Stream<Stream<Character>> stream = list.stream()
                .map(T1_streamApi::filterCharacter);//类::方法 的映射
        //取值 Stream<Stream<Character>>
        stream.forEach(sm -> sm.forEach(System.out::println));

        System.out.println("-----flatMap---------");
        Stream<Character> stream1 = list.stream()
                .flatMap(T1_streamApi::filterCharacter);//{a,a,a,b,b,b...}
        //取值
        stream1.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = Lists.newArrayList();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
//        System.out.println(list);
        return list.stream();
    }

    @Test
    public void test6() {
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");

        List list1 = new ArrayList();

        list1.add(11);
        list1.add(22);
//        list1.add(list);
        list1.addAll(list);
//        System.out.println("list1.add(list)"+list1);//[11, 22, [aaa, bbb, ccc, ddd, eee]]
        System.out.println("list1.addAll(list)"+list1);//[11, 22, aaa, bbb, ccc, ddd, eee]
    }

    /*
        排序
        sorted()—自范排序(Comparable)
        sorted(comparator com)—定制扣序(Comparator)
     */
    @Test
    public void test7(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");

        list.stream()
            .sorted()
            .forEach(System.out::println);

        System.out.println("-----------------------");

        employees.stream()
                .sorted((e1,e2) -> {
                    if(e1.getAge().equals(e2.getAge())){
                        return e1.getName().compareTo(e2.getName());
                    }else {
                        return -e1.getAge().compareTo(e2.getAge());
                    }
                }).forEach(System.out::println);
    }
}
