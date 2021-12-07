package com.jsxl.stream_api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;


public class TestForkJoin {
    @Test
    public void test1(){
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinCalculate task = new ForkJoinCalculate(0,10000000);
        Long sum = pool.invoke(task);
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println("耗时" + Duration.between(start,end).toMillis());
    }
    @Test
    public void test2(){
        Instant start = Instant.now();
        long sum = 0L;

        for (long i=0; i<10000000L;i++) {
            sum+=i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗时" + Duration.between(start,end).toMillis());
    }

    /*
        java8 并行流
     */
    @Test
    public void test3(){
        Instant start = Instant.now();
        LongStream.rangeClosed(1,100000000000L)
                .parallel()
                .reduce(0,Long::sum);
        Instant end = Instant.now();
        System.out.println("耗时" + Duration.between(start,end).toMillis());//耗时8524
    }


}
