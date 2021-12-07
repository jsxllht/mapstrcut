package com.jsxl.stream_api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import sun.plugin.javascript.navig4.Document;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Employee {
    private String name;
    private Integer age;
    private Double salary;
    private Status status;
}

enum Status{
    FREE,
    BUSY,
    VOCATION;
}

public class T2_StreamApi {
    List<Employee> employees = Arrays.asList(
            new Employee("张三", 22, 999.99, Status.FREE),
            new Employee("赵四", 12, 666.99, Status.BUSY),
            new Employee("王五", 23, 888.99, Status.VOCATION),
            new Employee("赵六", 23, 777.99, Status.FREE),
            new Employee("田七", 35, 111.99, Status.BUSY)
    );

    /*
        查找与匹配
        allMatch—检查是否匹配所有元素
        anyMatch一经查是否至少匹配一个元素
        noneMatch—检查是否没有匹配所有元素
        findFirst——返回第一个元素
        findAny—返回当流中的任意元素
        count—返回流中元素的总个数
        max——返国流中最大值
        min——返回流中最小值
     */

    @Test
    public void test1() {
        boolean b1 = employees.stream()
                .allMatch(e -> e.getStatus().equals(Status.FREE));
        System.out.println(b1);

        boolean b2 = employees.stream()
                .anyMatch(e -> e.getStatus().equals(Status.FREE));
        System.out.println(b2);

        boolean b3 = employees.stream()
                .noneMatch(e -> e.getStatus().equals(Status.FREE));
        System.out.println(b3);
        //Optional 避免空指针异常
        Optional<Employee> op = employees.stream()
                .sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(op.get());

//        Optional<Employee> op2 = employees.stream()
//                .filter(e -> e.getStatus().equals(Status.FREE))
//                .findAny();
//        System.out.println(op2.get());

        Optional<Employee> op3 = employees.parallelStream()//获取并行流，相当与多个线程一起执行，谁抢到输出谁
                .filter(e -> e.getStatus().equals(Status.FREE))
                .findAny();
        System.out.println(op3.get());
    }

    @Test
    public void test2() {
        long count = employees.stream()
                .count();
        System.out.println(count);

        Optional<Employee> op1 = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(op1.get());

        Optional<Double> op2 = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(op2.get());

    }

    /*
        归约
        reduce(T identity ，BinaryOperator)/reduce(BinaryOperator) --可以将流中的元素反复结合起来，得到一个值。
     */
    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);
    }

    /*
        终止操作
     */
      /*
        收集
        collect -- 将流转换为其他方式。接收一个Collector接口的实现
     */
    @Test
    public void test4() {
        employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("-----------------------------");
        employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet())
                .forEach(System.out::println);
        System.out.println("-----------------------------");
        employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new))
                .forEach(System.out::println);
    }

    @Test
    public void test5() {
        //总数
        System.out.println(employees.stream()
                .collect(Collectors.counting()));
        //平均值
        System.out.println(employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary)));
        //总和
        System.out.println(employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary)));
        //最大值
        System.out.println(employees.stream()
                .collect(Collectors.maxBy((e1,e2) -> Double.compare(e1.getSalary(),e2.getSalary()))));
        //最小值
        System.out.println(employees.stream()
                .collect(Collectors.minBy((e1,e2) -> Double.compare(e1.getSalary(),e2.getSalary()))));
    }

    //分组
    @Test
    public void test6() {
        Map<Status, List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
    }
//{FREE=[Employee(name=张三, age=22, salary=999.99, status=FREE), Employee(name=赵六, age=23, salary=777.99, status=FREE)], BUSY=[Employee(name=赵四, age=12, salary=666.99, status=BUSY), Employee(name=田七, age=35, salary=111.99, status=BUSY)], VOCATION=[Employee(name=王五, age=23, salary=888.99, status=VOCATION)]}

    @Test  //多级分组
    public void test7() {
        Map<Status, Map<String, List<Employee>>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
                    if (((Employee) e).getAge() <= 35) {
                        return "青年";
                    } else if (((Employee) e).getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(map);
        //{VOCATION={青年=[Employee(name=王五, age=23, salary=888.99, status=VOCATION)]}, FREE={青年=[Employee(name=张三, age=22, salary=999.99, status=FREE), Employee(name=赵六, age=23, salary=777.99, status=FREE)]}, BUSY={青年=[Employee(name=赵四, age=12, salary=666.99, status=BUSY), Employee(name=田七, age=35, salary=111.99, status=BUSY)]}}
    }

    //分区
    @Test
    public void test8() {
        Map<Boolean, List<Employee>> map = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 888.88));
        System.out.println(map);
        //{false=[Employee(name=赵四, age=12, salary=666.99, status=BUSY), Employee(name=赵六, age=23, salary=777.99, status=FREE), Employee(name=田七, age=35, salary=111.99, status=BUSY)], true=[Employee(name=张三, age=22, salary=999.99, status=FREE), Employee(name=王五, age=23, salary=888.99, status=VOCATION)]}
    }

    @Test
    public void test9() {
        DoubleSummaryStatistics dss = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getSum());
        System.out.println(dss.getAverage());
        System.out.println(dss.getMax());
    }
    @Test
    public void test10() {
        String str = employees.stream()
                .map(Employee::getName)
//                .collect(Collectors.joining());
                .collect(Collectors.joining(",","===","==="));
//        System.out.println("collect(Collectors.joining())"+str);//张三赵四王五赵六田七
        System.out.println("collect(Collectors.joining(\",\",\"===\",\"===\"))"+str);//===张三,赵四,王五,赵六,田七===
    }

}

