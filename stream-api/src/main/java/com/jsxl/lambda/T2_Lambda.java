package com.jsxl.lambda;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T2_Lambda {
        List<Employee> employees = Arrays.asList(
                new Employee("张三",22,999.99),
                new Employee("张三",12,666.99),
                new Employee("张三",23,888.99),
                new Employee("张三",35,111.99),
                new Employee("张三",24,333.99)
        );

    public List<Employee> filterEmployees(List<Employee> list){
        List<Employee> emps = Lists.newArrayList();

        for (Employee employee : list) {
            if(employee.getAge() > 15){
                emps.add(employee);
            }
        }
        return emps;
    }
    @Test
    public void filterEmployeesTest(){
        List<Employee> list = filterEmployees(employees);

        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    //优化方法
    public List<Employee> filterEmployee(List<Employee> list ,MyPredicate<Employee> mp){
        List<Employee> emps = Lists.newArrayList();
        for (Employee employee : list) {
            if(mp.test(employee)){
                emps.add(employee);
            }
        }
        return emps;
    }

    //优化方式一，策略设计模式
    @Test
    public void test1(){
        List<Employee> list1 = filterEmployee(this.employees, new FilterEmployeeByAge());
        for (Employee employee : list1) {
            System.out.println(employee);
        }
        System.out.println("---------------------------");
        List<Employee> list2 = filterEmployee(this.employees, new FilterEmployeeBySalary());
        for (Employee employee : list2) {
            System.out.println(employee);
        }

    }


    //优化方式二，匿名内部类
    @Test
    public void test2(){
       List<Employee> list = filterEmployee(this.employees, new MyPredicate<Employee>(){
           @Override
           public boolean test(Employee employee) {
                   return employee.getAge() >= 15;
           }
       });
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    //优化三 lambda表达式
    @Test
    public void test3(){
        List<Employee> list = filterEmployee(this.employees,(e)-> e.getAge() >= 15);
        list.forEach(System.out::println);
    }

    //优化四 StreamAPI
    @Test
    public void test4(){
        employees.stream()
                .filter((e -> e.getSalary() >= 777 ))
                .limit(2)
                .forEach(System.out::println);
        employees.stream()
                .map(Employee::getAge)
                .forEach(System.out::println);
    }
}

interface MyPredicate<T> {
    boolean test(T t);
}

class FilterEmployeeByAge implements MyPredicate<Employee>{
    @Override
    public boolean test(Employee emp) {
        return emp.getAge() >= 15;
    }
}

class FilterEmployeeBySalary implements MyPredicate<Employee>{
    @Override
    public boolean test(Employee emp) {
        return emp.getSalary() >= 777;
    }
}