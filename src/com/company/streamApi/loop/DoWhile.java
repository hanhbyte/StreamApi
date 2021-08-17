package com.company.streamApi.loop;

public class DoWhile {
    public static void main(String[] args) {
        System.out.println("=========== do-while loop");
        int counter = 0;
        do {
            System.out.println("counter: " + counter++);
        } while (counter < 5);

        do {
            System.out.println("counter is five already: " + counter);
        } while (counter < 5);


    }
}
