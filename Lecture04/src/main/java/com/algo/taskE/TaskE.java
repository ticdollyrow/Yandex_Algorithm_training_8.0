package com.algo.taskE;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


public class TaskE {
    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt(); // количество участков трассы
        int m = fastScanner.nextInt(); //маршрутов
        long k = fastScanner.nextLong(); //количество выбоин, которые можно починить
        int[] arr;
        arr = fastScanner.nextIntArray(n); //количество выбоин на участках трассы

        long[] prefix = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + arr[i - 1];
        }

        int[][] popular = new int[n][2];
        int[] diff = new int[n + 1];
        long sum = 0; //дискомфорт до ремонта
        for (int i = 1; i <= m; i++) {
            int l = fastScanner.nextInt();
            int r = fastScanner.nextInt();

            sum += prefix[r] - prefix[l - 1];

            diff[l] += 1;
            if (r + 1 <= n) {
                diff[r + 1] -= 1;
            }
        }

        int popularity = 0;
        for (int i = 1; i <= n; i++) {
            popularity += diff[i];
            popular[i - 1][0] = popularity;
            popular[i - 1][1] = i - 1;
        }

        Arrays.sort(popular, ((o1, o2) -> {
            return o2[0] - o1[0];
        }));
        int i = 0;
        while (i < n && k > 0) {
            int index = popular[i][1];
            if (arr[index] > 0) {
                long count = arr[index] >= k ? k : arr[index];
                sum -= count * popular[i][0];
                k -= count;
            }
            i++;
        }

        System.out.println(sum);
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

        long nextLong() throws IOException {
            int c, sign = 1;
            long num = 0;
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
