package com.algo.taskA;

import java.io.IOException;
import java.io.InputStream;

//A. Полка раздора
public class TaskA {

    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int a = fastScanner.nextInt();
        int b = fastScanner.nextInt();
        int S = fastScanner.nextInt();

        long au = 1L;
        long bu = -a - b;
        long cu = (long) a * b - S;

        long D = bu * bu - 4 * au * cu;
        final double sqrt = Math.sqrt(D);
        if (sqrt % 1 == 0) {
            long x1 = (long) (-bu + sqrt) / 2;
            long x2 = (long) (-bu - sqrt) / 2;
            if (x1 > 0 && x1 % 1 == 0) {
                System.out.println(x1);
                return;
            }
            if (x2 > 0 && x2 % 1 == 0) {
                System.out.println(x2);
                return;
            }
        }
        System.out.println(-1);
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
