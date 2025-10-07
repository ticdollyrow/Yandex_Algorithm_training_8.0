package com.algo.taskC;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/// C. Расписание для взвешенных интервалов*
public class TaskC {
    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt(); //количество интервалов
        int max = 0;

        List<Node> intervals = new ArrayList<>();
        for(int i = 0; i < n; i++){
            double begda = fastScanner.nextDouble();
            double endda = fastScanner.nextDouble();
            double weight = fastScanner.nextDouble();
            intervals.add(new Node(begda, endda, weight));
        }

        intervals.sort(Comparator.comparingDouble(Node::begda)
                .thenComparingDouble(Node::endda)
                .thenComparingDouble(Node::weight));

        ArrayList<Node> list = new ArrayList<>();
        for(var node:intervals) {
            if(list.isEmpty()) {
                list.add(new Node(node, node.weight()));
                continue;
            }



        }


        System.out.println(max);
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

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buf); ptr = 0;
                if (len < 0) return -1;
            }
            return buf[ptr++];
        }

        double nextDouble() throws IOException {
            int c;
            do { c = read(); } while (c <= ' '); // пропуск пробелов

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

record Node(double begda, double endda, double weight, double maxWeight){
    public Node(double begda, double endda, double weight){
        this(begda, endda, weight, 0);
    }

    public Node(Node node, double maxWeight){
        this(node.begda(), node.endda(), node.weight(), maxWeight);
    }
}
