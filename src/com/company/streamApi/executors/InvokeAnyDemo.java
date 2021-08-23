package com.company.streamApi.executors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAnyDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Callable<String>> tasks = new ArrayList<>(Arrays.asList(
                () -> {
                    TimeUnit.MILLISECONDS.sleep(500);
                    return "tasks 1";
                },
                () -> {
                    TimeUnit.MILLISECONDS.sleep(100);
                    return "tasks 2";
                },
                ()->{
                    TimeUnit.MILLISECONDS.sleep(300);
                    return "tasks 3";
                }
        ));
        String result = executorService.invokeAny(tasks);
        System.out.println(result);
    }
}
