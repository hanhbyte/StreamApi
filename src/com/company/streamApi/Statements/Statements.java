package com.company.streamApi.Statements;

import java.util.Scanner;

public class Statements {
    public static void main(String[] args) {
        System.out.println("Input your age : ");
        Scanner scanner = new Scanner(System.in);
        String sol = scanner.nextLine();
        int age = Integer.parseInt(sol);

        if (age >= 18){
            System.out.println("Input your fav food");
            String food = scanner.nextLine();
            if (food.equals("thịt chó")){
                System.out.println("Mine too");
            }else {
                System.out.println("Not mine");
            }
        } else if (age >= 13){
            System.out.println("You are a teenager");
        }else {
            System.out.println("You are not a teenager or an adult");
        }
    }
}
