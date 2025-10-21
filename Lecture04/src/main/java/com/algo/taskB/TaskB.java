package com.algo.taskB;

import java.io.IOException;
import java.io.InputStream;

public class TaskB {
    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt(); //число строк в таблице налоговых ставок
        long[][] taxes = new long[n][2];
        for (int i = 0; i < n; i++) {
            //мощность и соответствующая диапазону налоговая ставка
            //Гарантируется, что b1 = 0, b(i) < b(i+1)
            taxes[i][0] = fastScanner.nextLong();
            taxes[i][1] = fastScanner.nextLong();
        }
        int m = fastScanner.nextInt(); //число автомобилей
        for (int i = 0; i < m; i++) {
            long q = fastScanner.nextLong();
            System.out.println(binarySearch(0, n - 1, q, taxes));
        }

    }

    static long binarySearch(int first, int last, long power, long[][] taxes) {
        if (taxes[last][0] < power) {
            return taxes[last][1] * power;
        } else if (taxes[last][0] == power) {
            return taxes[last - 1][1] * power;
        }

        int middle = (first + last) / 2;
        if (taxes[middle][0] == power) {
            return power * taxes[middle - 1][1];
        } else if (taxes[middle][0] > power) {
            if(taxes[middle-1][0] < power){
                return power * taxes[middle-1][1];
            }
                return binarySearch(first, middle, power, taxes);
        } else {
            if(taxes[middle+1][0] >= power){
                return taxes[middle][1] * power;
            }
            return binarySearch(middle, last, power, taxes);
        }
    }

    static final class FastScanner {
        private final byte[] buf = new byte[1 << 16];
        private int len = 0, ptr = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        int nextInt() throws IOException {
            int c, sign = 1, num = 0;
            do {
                c = read();
            } while (c <= ' ');
            if (c == '-') {
                sign = -1;
                c = read();
            }
            while (c > ' ') {
                num = num * 10 + (c - '0');
                c = read();
            }
            return num * sign;
        }

        long nextLong() throws IOException {
            int c, sign = 1;
            long num = 0;
            do {
                c = read();
            } while (c <= ' ');
            if (c == '-') {
                sign = -1;
                c = read();
            }
            while (c > ' ') {
                num = num * 10 + (c - '0');
                c = read();
            }
            return num * sign;
        }


        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buf);
                ptr = 0;
                if (len < 0) return -1;
            }
            return buf[ptr++];
        }
    }
}
