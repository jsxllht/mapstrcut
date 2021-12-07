package com.jsxl.repetitionannotation;

import org.junit.Test;

import java.lang.reflect.Method;



/*
    重复注解和注解类型
 */
public class RepetitionAnnotation {

//    checker framework
//    private /*NonNull*/ Object obj = null;

    @Test
    public void test() throws Exception{
        Class<RepetitionAnnotation> clazz = RepetitionAnnotation.class;
        Method method = clazz.getMethod("show");
        MyAnnotation[] mas = method.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation ma : mas) {
            System.out.println(ma.value());
        }
    }

    @MyAnnotation("Hello")
    @MyAnnotation("World")
//    public void show(@MyAnnotation("abc") String str){//注解类型
    public void show(){
    }
}
