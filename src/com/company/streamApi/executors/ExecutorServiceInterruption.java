package com.company.streamApi.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExecutorServiceInterruption {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        IntStream.range(0, 10).forEach((i)->{
            executorService.submit(()->{
                try {
                    TimeUnit.SECONDS.sleep(i);
                    System.out.println("Task #" +i+ "is completed");
                }catch (InterruptedException e){
                    System.out.println("Task #" +i+ "is interrupted");
                }
            });
        });
        System.out.println("Shutting down");
        executorService.shutdown();
        try {
            executorService.awaitTermination(2, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
