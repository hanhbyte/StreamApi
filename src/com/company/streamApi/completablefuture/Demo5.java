package com.company.streamApi.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Demo5 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture future = CompletableFuture.supplyAsync(() -> "First")
                .thenAcceptBoth(CompletableFuture
                        .supplyAsync(() -> " Second"), (s1, s2) -> System.out.println(s1 + s2));
        System.out.println(future.get());
    }
}
