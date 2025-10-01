package com.algo;

import java.io.IOException;
import java.io.InputStream;

/// Мамины поручения
public class Task2 {
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int a = fs.nextInt(); //длина дороги от дома до супермаркета
        int b = fs.nextInt(); //длина дороги от дома до пункта выдачи
        int c = fs.nextInt(); //длина дороги от супермаркета до пункта выдачи

        int v0 = fs.nextInt(); //без груза
        int v1= fs.nextInt(); //с продуктами либо посылкой
        int v2 = fs.nextInt(); //с продуктами и посылкой

        a = Math.min(a, b+c);
        b = Math.min(b, a+c);
        double time = 0;
        double min = 0;
        time = (double)c/v1;
        min = Math.min((double)a/v0 + (double)b/v2, (double)a/v2 + (double)b/v0);
        time +=min;

        min = (double)a/v0 + (double)a/v1 + (double)b/v0 + (double)b/v1;
        time = Math.min(time, min);

        System.out.printf("%.15f%n", time);
    }


    static final class FastScanner {
        private final byte[] buf = new byte[1 << 16];
        private int len = 0, ptr = 0;
        private final InputStream in;
        FastScanner(InputStream in) { this.in = in; }
        int nextInt() throws IOException {
            int c, sign = 1, num = 0;
            do { c = read(); } while (c <= ' ');
            if (c == '-') { sign = -1; c = read(); }
            while (c > ' ') { num = num * 10 + (c - '0'); c = read(); }
            return num * sign;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buf); ptr = 0;
                if (len < 0) return -1;
            }
            return buf[ptr++];
        }
    }
}
