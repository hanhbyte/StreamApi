package com.company.streamApi.executors;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CachedThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        IntStream.range(0, 4).forEach(i -> {
            executor.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        });
        executor.shutdown();
        System.out.println("Pool size " +executor.getPoolSize());
        System.out.println("Queue size " +executor.getQueue().size());
        }
}
