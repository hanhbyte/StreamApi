package com.company.streamApi.multithreading;


import java.util.concurrent.TimeUnit;

public class Demo1 implements Runnable{
    public int counter1 = 0;
    public int counter2 = 0;

    @Override
    public void run(){
        try {
            while (true){
                System.out.println(counter1+"=="+counter2);
                counter1 ++;
                TimeUnit.MILLISECONDS.sleep(10);
                counter2 ++;
                TimeUnit.MILLISECONDS.sleep(10);
            }
        }catch (InterruptedException e){
            System.out.println("Thread was interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException{
    Demo1 obj = new Demo1();
    Thread t1 = new Thread(obj);
    Thread t2 = new Thread(obj);
    t1.start();
    t2.start();
    TimeUnit.SECONDS.sleep(3);
    t1.interrupt();
    t2.interrupt();
    }
}
