package com.jsxl.lambda;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class T6_Lambda_MethodRef {
    //对象::实例方法名
    @Test
    public void test1(){
        PrintStream ps = System.out;
        Consumer<String> con1 = x -> ps.println(x);
        Consumer<String> con2 = ps::println;
        //con2等价于
        Consumer<String> con3 = System.out::println;
        con3.accept("对象::实例方法");
    }

    @Test
    //Lambda中的泛型必须与返回值保持一致
    public void test2(){
        Employee emp = new Employee();
//        Supplier<String> supStr = () -> emp.getAge();
        Supplier<Integer> supInt = () -> emp.getAge();
    }

    //类::静态方法名
    @Test
    public void test3(){
        Comparator<Integer> com = (x, y) -> Integer.compare(x,y);
        Comparator<Integer> com1 = Integer::compare;
        System.out.println(com.compare(1, 2));
    }
    //源码：lambda体中的参数和返回值类型必须一致
//    public static int compare(int x, int y){
//        return (x < y) ? -1 : ((x == y) ? 0 : 1);
//    }
    //源码：函数式接口中的参数和返回值类型必须一致
//    int compare(T o1, T o2);

    //类::实例方法名
    @Test
    public void test4(){
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        BiPredicate<String, String> bp1 = String::equals;
        System.out.println(bp1.test("1", "1"));
    }

}
