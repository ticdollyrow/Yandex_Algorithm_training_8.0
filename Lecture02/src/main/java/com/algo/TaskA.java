package com.algo;

import java.io.IOException;
import java.io.InputStream;

/// A. Мячик на лесенке
public class TaskA {
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int[] arr = new int[n];
        for(int i = 1; i <= n; i++){
            switch (i){
                case 1-> arr[i-1] = 1;
                case 2-> arr[i-1] = 2;
                case 3-> arr[i-1] = 4;
                default -> arr[i-1] = arr[i-1-1] + arr[i-2-1] + arr[i-3-1];
            }
        }

        System.out.println(arr[n-1]);
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

        int[] nextIntArray(int n) throws IOException {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
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
