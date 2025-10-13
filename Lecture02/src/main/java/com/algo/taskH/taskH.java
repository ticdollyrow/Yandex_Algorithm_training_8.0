package com.algo.taskH;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


public class taskH {
    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt();
        if (n <= 3) {
            System.out.println(1);
            return;
        }

        boolean[] simple = new boolean[n + 1];
        Arrays.fill(simple, true);
        simple[1] = false;
        simple[2] = true;
        int limit = (int) Math.sqrt(n);
        for (int i = 2; i <= limit; i++) {
            if (simple[i]) {
                for (int j = i * i; j <= n; j += i) {
                    simple[j] = false;
                }
            }
        }

        boolean[] dp = new boolean[n+1];
        dp[1] = true;
        dp[2] = true;
        dp[3] = true;

        for(int i = 4; i <= n; i++){
            dp[i] = (!simple[i-1] && !dp[i-1]) || (!simple[i-2] && !dp[i-2]) || (!simple[i-3] && !dp[i-3]);
        }

        System.out.println(dp[n]?1:2);
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
