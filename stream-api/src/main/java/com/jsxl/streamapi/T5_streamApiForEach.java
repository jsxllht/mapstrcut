package com.jsxl.streamapi;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class T5_streamApiForEach {

    //forEach方法接收一个Lambda表达式，然后在Stream的每一个元素上执行该表达式。
    public static void main(String[] args) {
        // 对一个人员集合遍历，找出男性并打印姓名。
//        roster.stream().filter(p -> p.getGender() == Person.Sex.MALE)
//                .forEach(p -> System.out.println(p.getName()));
//可以看出来，forEach是为Lambda而设计的，保持了最紧凑的风格。
// 当需要为多核系统优化时，可以parallelStream().forEach()，
// 只是此时原有元素的次序没法保证，并行的情况下将改变串行时操作的行为，
// 此时forEach本身的实现不需要调整，而Java8以前的for循环代码可能需要加入额外的多线程逻辑。但一般认为，
// forEach和常规for循环的差异不涉及到性能，它们仅仅是函数式风格与传统 Java 风格的差别。

//另外一点需要注意，forEach是terminal操作。因此，它执行后，
// Stream 的元素就被“消费”掉了，你无法对一个Stream进行两次terminal运算。下面的代码是错误的：

//        stream.forEach(element -> doOneThing(element));
//        stream.forEach(element -> doAnotherThing(element));





//相反，具有相似功能的intermediate操作peek可以达到上述目的。如下是出现在Stream api javadoc上的一个示例:

// peek 对每个元素执行操作并返回一个新的 Stream
        List<String> collect = Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e)).map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e)).collect(Collectors.toList());
        System.out.println(collect);
        //forEach 不能修改自己包含的本地变量值，也不能用break/return之类的关键字提前结束循环。
    }
}
