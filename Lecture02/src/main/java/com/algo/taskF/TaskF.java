package com.algo.taskF;

import java.io.IOException;
import java.io.InputStream;

public class TaskF {
    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt();
        int max = 0;
        int dp[][] = new int[n][3];

        int[] coordinate = {1, 0, -1};

        for (int i = 0; i < n; i++) {
            int wallCount = 0;

            for (int j = 0; j < 3; j++) {
                int c; char sym;
                do {
                    c = fastScanner.read();
                } while (c <= ' ');
                sym = (char)c;
                if (sym == 'W') {
                    dp[i][j] = -1;  // стена
                    wallCount++;
                    continue;
                }

                if (i == 0) {
                    // первая строка
                    dp[i][j] = (sym == 'C') ? 1 : 0;
                } else {
                    int maxPrev = -1;
                    for (int dir : coordinate) {
                        int prevCol = j + dir;
                        if (prevCol >= 0 && prevCol < 3) {
                            maxPrev = Math.max(maxPrev, dp[i - 1][prevCol]);
                        }
                    }
                    if (maxPrev == -1) {
                        dp[i][j] = -1;  // недостижимая клетка
                    } else {
                        dp[i][j] = maxPrev + (sym == 'C' ? 1 : 0);
                    }
                }

                if (dp[i][j] != -1) {
                    max = Math.max(max, dp[i][j]);
                }
            }

            // если вся строка — стены, игра заканчивается
            if (wallCount == 3) {
                System.out.println(max);
                return;
            }
        }
        System.out.println(max);
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
