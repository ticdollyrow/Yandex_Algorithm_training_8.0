package com.trueTechChampMTC;

import java.util.Scanner;

//Задача B. Телефонные номера
public class TaskB {
    static final String firstNumber = "79";
    static final int lengthOfNumber = 11;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // System.in is a standard input stream
        int n = sc.nextInt(); //длина строки
        String number = sc.next();
        if (n % lengthOfNumber != 0) {
            System.out.println(0);
            return;
        }

        int i = 0;
        while (i < n) {
            if (!number.substring(i, i + 2)
                    .equals(firstNumber)) {
                System.out.println(0);
                return;
            }
            i+=lengthOfNumber;
        }

        System.out.println(1);
    }
}
