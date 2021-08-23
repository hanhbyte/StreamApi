package com.company.streamApi.executors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAllDemo {
    public static void main(String[] args) throws InterruptedException{
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Callable<String>> tasks = new ArrayList<>(Arrays.asList(
                () ->"tasks 1",
                () ->"tasks 2",
                () ->"tasks 3"
        ));
        List<Future<String>> futures = executorService.invokeAll(tasks);
        futures.stream().map(future ->{
            try {
                return future.get();
            }catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
            return "";
        }).forEach(System.out::println);
    }
}
