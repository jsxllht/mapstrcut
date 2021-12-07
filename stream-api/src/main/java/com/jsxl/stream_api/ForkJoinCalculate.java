package com.jsxl.stream_api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.RecursiveTask;

@AllArgsConstructor
public class ForkJoinCalculate extends RecursiveTask<Long> {
    private static final long serialVersionUID =  134656970987L;

    private long start;
    private long end;
    private static final long THRESHOLD = 10000;
    @Override
    protected Long compute() {
        long length = end - start;

        if(length <= THRESHOLD){
            long sum = 0;

            for (long i = start; i<= end ; i++) {
                sum += i;
            }
            return sum;
        }else {
            long middle = (start + end)/2;

            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork();//拆分子任务，同时压入线程队列
            ForkJoinCalculate right = new ForkJoinCalculate(middle+1, end);
            right.fork();
            return left.join()+right.join();
        }
    }
}
