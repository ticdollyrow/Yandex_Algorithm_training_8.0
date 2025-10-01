package com.algo;

import java.io.IOException;
import java.io.InputStream;

/// C. Кибербезопасность
public class Task3 {
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        long count = 1;
        int[] arr = new int[26];
        int c;
        do {
            c = fs.read();
        } while (c <= ' ');
        while (c > ' ') {
            arr[c - 'a'] += 1;
            c = fs.read();
        }

        for (int i = 0; i < 26; i++) {
            for (int j = i + 1; j < 26; j++) {
                if (arr[i] > 0 && arr[j] > 0) {
                    count += arr[i] * arr[j];
                }
            }
        }
        System.out.println(count);
    }

    static final class FastScanner {
        private final byte[] buf = new byte[1 << 16];
        private int len = 0, ptr = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
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