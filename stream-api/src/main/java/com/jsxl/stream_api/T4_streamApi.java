package com.jsxl.stream_api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

//交易员类
@Data
@AllArgsConstructor
@NoArgsConstructor
class Trader {
    private String name;
    private String city;
}

//交易类
@Data
@AllArgsConstructor
@NoArgsConstructor
class Transaction {
    private Trader trader;
    private Integer year;
    private Integer value;
}

public class T4_streamApi {
    List<Transaction> transactions = null;

    @Before
    public void before() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 100),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }
    //1.找出2011年发生的所有交易,并按交易额排序（从低到高)
    @Test
    public void test1() {
        transactions.stream()
                .filter( t -> t.getYear() == 2011)
                .sorted((t1,t2) -> Integer.compare(t1.getValue(),t2.getValue()))
                .forEach(System.out::println);
    }
    //2.交易员都在哪些不同的城市工作过?
    @Test
    public void test2() {
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
    }
    //3.查找所有来自剑桥的交易员,并按姓名排序
    @Test
    public void test3() {
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .sorted((t1,t2) -> t1.getName().compareTo(t2.getName()))
                .distinct()
                .forEach(System.out::println);
    }
    //4.返回所有交易员的姓名字符串,按字母顺序排序
    @Test
    public void test4() {
        transactions.stream()
                .map(t -> t.getTrader().getName())
                .sorted()
                .forEach(System.out::println);
        System.out.println("--------------------------");
        System.out.println(transactions.stream()
                .map(t -> t.getTrader().getName())
                .sorted()
                .reduce("", String::concat));
        System.out.println("--------------------------");
        transactions.stream()
                .map(t -> t.getTrader().getName())
                .flatMap(T4_streamApi::filterCharacter)
                .sorted()
                .forEach(System.out::print);
        System.out.println();
        System.out.println("--------------------------");
        transactions.stream()
                .map(t -> t.getTrader().getName())
                .flatMap(T4_streamApi::filterCharacterString)
                .sorted((s1,s2) -> s1.compareToIgnoreCase(s2))
                .forEach(System.out::print);
    }
    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()){
            list.add(ch);
        }
        return list.stream();
    }
    public static Stream<String> filterCharacterString(String str) {
        List<String> list = new ArrayList<>();
        for (Character ch : str.toCharArray()){
            list.add(ch.toString());
        }
        return list.stream();
    }
    //5.有没有交易员是在米兰工作的?
    @Test
    public void test5() {
        boolean flag = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println(flag);
    }
    //6.打印生活在剑桥的交易员的所有交易额
    @Test
    public void test6() {
        Optional<Integer> sum = transactions.stream()
                .filter(e -> e.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);//类::静态方法
        System.out.println(sum.get());
    }
    //7.所有交易中,最高的交易额是多少
    @Test
    public void test7() {
        System.out.println(transactions.stream()
                .map(t -> t.getValue())
                .max(Integer::max).get());
    }
    //8.找到交易额最小的交易
    @Test
    public void test8() {
        System.out.println(transactions.stream()
                .min((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue())));
    }
}
