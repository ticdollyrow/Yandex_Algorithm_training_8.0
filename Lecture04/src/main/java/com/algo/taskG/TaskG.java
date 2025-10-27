package com.algo.taskG;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class TaskG {
    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt();
        int[] arr;
        arr = fastScanner.nextIntArray(n);
        SortedMap<Integer, Long> map = new TreeMap<>();
        Long sum = 0L;
        for (int i = 0; i < n; i++) {
            int a = fastScanner.nextInt();
            map.merge(arr[i], (long) a, Long::sum);
            sum += a;
        }

        long middle = (sum + 1) / 2;
        sum = 0L;
        int e = 0;
        for (Map.Entry<Integer, Long> val : map.entrySet()) {
            sum += val.getValue();
            if (sum >= middle) {
                e = val.getKey();
                break;
            }
        }

        sum = 0L;
        for (Map.Entry<Integer, Long> val : map.entrySet()) {
            sum += val.getValue() * Math.abs(val.getKey() - e);
        }

        System.out.println(e + " " + sum);
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
