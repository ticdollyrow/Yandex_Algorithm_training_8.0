package com.algo.taskG;

import java.io.IOException;
import java.io.InputStream;

/// G. Лесенки
public class TaskG {
    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt();
        int[][] dp = new int[n+1][n+1];

        for(int i =0; i <=n; i++){
            dp[0][i] = 1;
        }

        //каждый горизонтальный слой содержит меньше кубиков, чем слой под ним
        //если используем i кубиков, то остается i-j кубиков
        for(int i = 1; i <= n; i++){
            for( int j = 1; j <= n; j++){
                if(i >= j) {
                    dp[i][j] = dp[i][j - 1] + dp[i - j][j - 1];
                }else{
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        System.out.println(dp[n][n]);
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
