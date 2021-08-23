package com.company.streamApi.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class FixedThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        IntStream.range(0,4).forEach(i -> {
            executor.submit(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        });
        executor.shutdown();
        System.out.println("Poll size "+executor.getPoolSize() );
        System.out.println("Queue size" +executor.getQueue().size());

        int numOfCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Avalilable processors "+numOfCores);
    }
}
