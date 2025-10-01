package com.algo;

import java.io.IOException;
import java.io.InputStream;

/// E. Табло с инкрементом
public class Task5 {
    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt();
        int k = fastScanner.nextInt();
        long t = n % 10;
        long sum = t;
        long first = t;
        long result = n;

        while (k > 0 && t !=0 ) {
            if(t == 2 && k >=4){
                long c = k / 4;
                sum += 20 * c;
                k = k % 4;
                continue;
            }
            k--;
            sum += t;
            t = sum % 10;
        }
        result += sum - first;
        System.out.println(result);
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
