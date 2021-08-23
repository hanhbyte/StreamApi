package com.company.streamApi.completablefuture;

import java.util.concurrent.*;

public class Demo1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.runAsync(() -> {
            System.out.println("Thread name " +Thread.currentThread().getName());
            System.out.println("Http call goer here ...");
        });

        TimeUnit.SECONDS.sleep(2);
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture.runAsync(() -> {
            System.out.println("Thread name " +Thread.currentThread().getName());
            System.out.println("Http call goes here...");
        },executorService);
        executorService.shutdown();
    }
}
