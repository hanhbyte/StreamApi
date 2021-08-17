package com.company.streamApi.loop;

public class NestedLoop {
    public static void main(String[] args) {
        System.out.println("=========== nested loop");
        for (int i = 0; i < 5; i++) {
            System.out.println("*** i = " + i + " ***");
            for (int j = 0; j < 3; j++) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }

    }
}
