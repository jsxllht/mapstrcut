package com.jsxl.stream_api;


        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import org.junit.Test;

        import java.util.Arrays;
        import java.util.List;
        import java.util.Optional;

public class T3_streamApi {
    /*
    给定一个数字列表,如何返回一个由每个数的平方构成的列表呢?，给定【1，2，3，4，5】,应该返回【1，4，9，16，25】。
     */
    @Test
    public void test1(){
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5};
        Arrays.stream(nums)
                .map(x -> x * x)
                .forEach(System.out::println);
    }
    List<Employee> emps = Arrays.asList(
            new Employee("张三", 22, 999.99, Status.FREE),
            new Employee("赵四", 12, 666.99, Status.BUSY),
            new Employee("王五", 23, 888.99, Status.VOCATION),
            new Employee("赵六", 23, 777.99, Status.FREE),
            new Employee("田七", 35, 111.99, Status.BUSY)
    );

    @Test
    public void test2(){
        Optional<Integer> count = emps.stream()
                .map(e -> 1)
                .reduce(Integer::sum);
        System.out.println(count.get());
    }
}
