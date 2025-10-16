package com.trueTechChampMTC;

import java.util.Scanner;

public class TaskC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // System.in is a standard input stream
        int a, b, c;
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        boolean hasTriangle = false;
        //p(p-a)(p-b)(p-c) p = 1/2*(a+b+c)

        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;

        // Возможные расстояния между центрами
        int[] ab = {a + b, Math.abs(a - b)};
        int[] bc = {b + c, Math.abs(b - c)};
        int[] ac = {a + c, Math.abs(a - c)};

        for (int i = 0; i < 2; i++) {     // ab
            for (int j = 0; j < 2; j++) { // bc
                for (int k = 0; k < 2; k++) { // ac
                    int innerCount = i + j + k; // сколько внутренних касаний (разностей)
                    if (innerCount == 1 || innerCount == 3) continue; // геометрически невозможно

                    int x = ab[i];
                    int y = bc[j];
                    int z = ac[k];

                    if (x + y > z && x + z > y && y + z > x) {
                        hasTriangle = true;
                        long s2 = 1L * (x + y + z) * (x + y - z) * (x + z - y) * (y + z - x) / 16;
                        min = Math.min(min, s2);
                        max = Math.max(max, s2);
                    }
                }
            }
        }

        if (!hasTriangle) {
            System.out.println(-1);
        } else {
            System.out.println(min + " " + max);
        }
    }
}