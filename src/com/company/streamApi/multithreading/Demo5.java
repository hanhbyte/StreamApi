package com.company.streamApi.multithreading;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo5 {
    private static final int INTERATION_NUMBER = 3;
    private static final int READERS_NUMBER = 3;
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final int BUFFER_LENGTH = 5;
    private static final int PAUSE = 5;
    private static boolean stop;
    private static volatile int readersRead;
    private static Lock lock = new ReentrantLock();
    private static Condition notEmpty = lock.newCondition();
    private static Condition isRead = lock.newCondition();

    private static class Reader extends Thread {

        @Override
        public void run() {
            while (!stop) {
                try {
                    lock.lock();
                    read(getName());
                    if (++readersRead == READERS_NUMBER) {
                        notEmpty.signalAll();
                    }
                    isRead.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private static class Writer extends Thread {
        @Override
        public void run() {
            int tact = 0;
            while (!stop) {
                try {
                    lock.lock();
                    write();
                    readersRead = 0;
                    isRead.signalAll();
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (++tact == INTERATION_NUMBER) {
                        isRead.signalAll();
                        stop = true;
                    }
                    lock.unlock();
                }
            }
        }
    }

    private static void read(String threadName) throws InterruptedException {
        System.out.printf("Read %s: ", threadName);
        for (int j = 0; j < BUFFER_LENGTH; j++) {
            Thread.sleep(PAUSE);
            System.out.println(BUFFER.charAt(j));
        }
        System.out.println();
        Thread.sleep(5);
    }

    private static void write() throws InterruptedException {
        BUFFER.setLength(0);
        System.out.print("Writer writes : ");
        Random random = new Random();
        for (int j = 0; j < BUFFER_LENGTH; j++) {
            Thread.sleep(PAUSE);
            char ch = (char) ('A' + random.nextInt(26));
            System.err.print(ch);
            BUFFER.append(ch);
        }
        System.err.println();
        Thread.sleep(5);
    }

    public static void main(String[] args) throws InterruptedException {
        Writer writer = new Writer();
        List<Thread> readers = new ArrayList<>();
        for (int j = 0; j < READERS_NUMBER; j++) {
            readers.add(new Reader());
        }
        writer.start();
        for (Thread reader : readers){
            reader.start();
        }
    }
}
