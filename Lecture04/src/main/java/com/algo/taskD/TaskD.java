package com.algo.taskD;

import java.io.IOException;
import java.io.InputStream;

public class TaskD {
    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt(); // количество столов с сырками
        int[] arr;
        arr = fastScanner.nextIntArray(n); //количество сырков на i-м столе
        long min = calcMin(n, arr);

        if (min > 0) {
            int i = 0;
            int j = n - 1;
            long sumL = arr[i];
            long sumR = arr[j];
            long diff = Math.abs(sumL - sumR);

            while (i < j) {
                if (diff == min) {
                    System.out.println(min + " " + (i + 1) + " " + (j + 1));
                    return;
                }

                if (sumL > sumR) {
                    if (i < --j)
                        sumR += arr[j];
                } else if( ++i < j) sumL += arr[i];

                diff = Math.abs(sumL - sumR);
            }
        }
    }

    static long calcMin(int n, int[] arr) {
        int i = 0, j = n - 1;
        long sumL = arr[i];
        long sumR = arr[j];
        long diff = Math.abs(sumL - sumR);
        long min = diff;

        while (i < j) {
            if (diff == 0) {
                System.out.println(min + " " + (i + 1) + " " + (j + 1));
                return min;
            }

            if (sumL > sumR) {
                if (i < --j)
                    sumR += arr[j];
            } else if( ++i < j) sumL += arr[i];

            diff = Math.abs(sumL - sumR);
            min = Math.min(min, diff);
        }

        return min;
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

        int[] nextIntArray(int n) throws IOException {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
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
