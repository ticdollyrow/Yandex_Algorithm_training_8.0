package com.algo.taskE;

/*
6 2
1 40 10 1 20 10
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/// E. Башня
public class TaskE {
    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt(); //количество столбиков в крепости
        int k = fastScanner.nextInt(); // Башней называется любая последовательность из K столбиков подряд
        int[] values = new int[n];
        long[] sumS = new long[n];
        long[] dp = new long[n];

        for (int i = 0; i < n; i++) {
            values[i] = fastScanner.nextInt();
        }

        int[] prev = new int[n];
        Arrays.fill(prev, -1);

        for (int i = k - 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int t = i - k + 1;
            int sum = 0;

            while (t <= i && t >= 0) {
                if (values[t] < min) min = values[t];
                sum += values[t];
                t++;
            }
            sumS[i] = (long) sum * min;

            long l = sumS[i] + ((i >= k) ? dp[i - k] : 0);
            long prevMax = (i - 1 >= 0) ? dp[i - 1] : 0;
            if (l > prevMax) {
                dp[i] = l;
                prev[i] = i - k + 1;
            } else {
                dp[i] = dp[i - 1];
                prev[i] = prev[i-1];
            }

        }

        int i = n-1;
        List<Integer> list = new ArrayList<>();
        while (i >= 0 && prev[i] != -1) {
            list.add(prev[i] + 1);
            i = prev[i] - 1;
        }

        Collections.reverse(list);
        System.out.println(list.size());
        for (var e:list){
            System.out.print(e + " ");
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
