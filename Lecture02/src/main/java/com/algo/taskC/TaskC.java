package com.algo.taskC;

/*
6
0 1 1
0.1 1 1
0.2 1 1
1 1.1 7
0.3 0.5 4
0.1 0.2 1
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/// C. Расписание для взвешенных интервалов*
public class TaskC {
    static final double EPS = 1e-9;// точность сравнения

    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt(); //количество интервалов
        if (n == 0) {
            System.out.println(0);
            return;
        }

        List<Node> intervals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double begda = fastScanner.nextDouble();
            double endda = fastScanner.nextDouble();
            double weight = fastScanner.nextDouble();
            intervals.add(new Node(begda, endda, weight));
        }

        intervals.sort(Comparator.comparingDouble(Node::endda)
                .thenComparingDouble(Node::begda));

        double[] dp = new double[n];
        dp[0] = intervals.get(0)
                .weight();
        for (int i = 1; i < n; i++) {
            final Node node = intervals.get(i);
            int index = search(intervals, i - 1, node.begda());
            dp[i] = Math.max(node.weight(), dp[i - 1]);
            if (index >= 0) {
                dp[i] = Math.max(dp[index] + node.weight(), dp[i]);
            }
        }
        System.out.printf("%.15f%n", dp[n - 1]);
    }

    public static int search(List<Node> intervals, int end, double begda) {
        int index = -1;
        int left = 0;
        int right = end;

        while (left <= right) {
            int mid = (left + right) / 2;
            double endda = intervals.get(mid)
                    .endda();
            if (begda >= endda - EPS) {
                index = mid;
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        return index;
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

        double nextDouble() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' '); // пропуск пробелов

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            double num = 0;
            while (c >= '0' && c <= '9') { // целая часть
                num = num * 10 + (c - '0');
                c = read();
            }

            if (c == '.') { // дробная часть
                double frac = 0, base = 0.1;
                c = read();
                while (c >= '0' && c <= '9') {
                    frac += (c - '0') * base;
                    base *= 0.1;
                    c = read();
                }
                num += frac;
            }

            return num * sign;
        }
    }
}


record Node(double begda, double endda, double weight) {
}
