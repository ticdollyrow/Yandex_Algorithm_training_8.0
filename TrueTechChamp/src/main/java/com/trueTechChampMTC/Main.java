package com.trueTechChampMTC;

import java.util.Scanner;

//Задача A. Максимальный кешбэк
public class Main {
    public static void main (String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in); // System.in is a standard input stream
        int a,b,x;
        a = sc.nextInt();
        b = sc.nextInt();
        x = sc.nextInt();

        System.out.println(x/a*b);
    }

}
