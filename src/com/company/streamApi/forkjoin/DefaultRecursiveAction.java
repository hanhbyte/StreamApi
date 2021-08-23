package com.company.streamApi.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class DefaultRecursiveAction extends RecursiveAction{
    private int workload = 0;
    public DefaultRecursiveAction(int workload){
        this.workload = workload;
    }

    @Override
    protected void  compute(){
        if (this.workload < 18){
            System.out.println("Doing workload myself in thread " +Thread.currentThread().getName() +"with workload : "+this.workload);
        }else {
            System.out.println("Splitting workload in thread "+Thread.currentThread().getName() +"with sorkload "+this.workload);
            List<DefaultRecursiveAction> subtasks = new ArrayList<>(createSubtasks());
            for (RecursiveAction subtask : subtasks){
                subtask.fork();
            }
        }
    }

    private List<DefaultRecursiveAction> createSubtasks(){
        List<DefaultRecursiveAction> subtasks = new ArrayList<>();
        DefaultRecursiveAction subtask1 = new DefaultRecursiveAction(this.workload / 2);
        DefaultRecursiveAction subtask2 = new DefaultRecursiveAction(this.workload / 2);

        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }
}
