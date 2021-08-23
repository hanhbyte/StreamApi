package com.company.streamApi.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Demo4 {
    private static final int INTERATION_NUMBER = 3;
    private static final int READERS_NUMBER = 3;
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final int BUFFER_LENGTH = 5;
    private static final int PAUSE = 5;
    private static boolean stop;
    private static boolean isWittenToBuffer;
    private static volatile int readersRead = READERS_NUMBER;
    private static final Object MONITOR = new Object();
    private static class Reader extends Thread{
        @Override
        public void run(){
            while (!stop){
                if (readersRead == 0){
                    try {
                        synchronized (MONITOR){
                            read(getName());
                            if (readersRead == READERS_NUMBER){
                                isWittenToBuffer = false;
                                MONITOR.notifyAll();
                            }
                        }
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static class Writer extends Thread{
        @Override
        public void run(){
            int tact = 0;
            while (!stop){
                try {
                    synchronized (MONITOR){
                        writer();
                        while (isWittenToBuffer){
                            MONITOR.wait();
                        }
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    if (++tact == INTERATION_NUMBER){
                        stop = true;
                    }
                }
            }
        }
    }

    private static void read(String threadName) throws  InterruptedException{
        System.out.printf("Reader %s", threadName);
        for (int i = 0; i < BUFFER_LENGTH; i++) {
            Thread.sleep(PAUSE);
            System.out.println(BUFFER.charAt(i));
        }
        System.out.println();
        readersRead++;
        Thread.sleep(5);
    }

    private static void writer() throws InterruptedException{
        BUFFER.setLength(0);
        System.err.printf("Writer writes");
        Random random = new Random();
        for (int i = 0; i < BUFFER_LENGTH; i++) {
            Thread.sleep(PAUSE);
            char ch = (char) ('A' + random.nextInt(26));
            System.err.print(ch);
            BUFFER.append(ch);
        }
        System.err.println();
        Thread.sleep(5);
        isWittenToBuffer = true;
        readersRead = 0;
    }

    public static void main(String[] args) throws InterruptedException{
        Writer writer = new Writer();
        List<Thread> readers = new ArrayList<>();
        for (int i = 0; i < READERS_NUMBER; i++) {
            readers.add(new Reader());
        }
        for (Thread reader : readers){
            reader.start();
        }
        writer.start();
    }
}
