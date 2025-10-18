package com.algo.taskB;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TaskB {
    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt();//число жизненных ситуаций
        List<Integer>[] tree = new List[n + 1];
        for (int i = 1; i <= n; i++) tree[i] = new ArrayList<>();
        for (int i = 0; i < n-1; i++) {
            int a = fastScanner.nextInt();
            int b = fastScanner.nextInt();
            tree[a].add(b);
            tree[b].add(a);
        }

        List<Integer> leaves = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            if(tree[i].size() == 1){
                leaves.add(i);
            }
        }

        int[] dist = new int[n + 1];
        int[] source = new int[n + 1];
        Arrays.fill(dist, -1);
        Arrays.fill(source, -1);
        Queue<Integer> queue = new ArrayDeque<>();
        for(var leaf:leaves){
            dist[leaf] = 0;
            queue.add(leaf);
            source[leaf] = leaf;
        }
        int minDistance = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int u : tree[v]) {
                if (dist[u] == -1) {
                    dist[u] = dist[v] + 1;
                    source[u] = source[v];
                    queue.add(u);
                } else if (source[u] != source[v]) {
                    // встреча из разных листьев
                    minDistance = Math.min(minDistance, dist[u] + dist[v] + 1);
                }
            }
        }
        System.out.println(minDistance);

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
