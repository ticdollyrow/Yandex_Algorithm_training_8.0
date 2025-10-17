package com.algo.taskF;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TaskF {
    static int timer = 0;

    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt(); //количество вершин в дереве
        //Во второй строке находятся n чисел, i-е из которых определяет номер непосредственного родителя вершины с номером i.
        //Если это число равно нулю, то вершина является корнем дерева.
        int[] arr = new int[n+1];
        int timer = 0;
        List<Integer>[] tree = new List[n+1];
        for(int i = 1; i <= n; i++) tree[i] = new ArrayList<>();
        int root = -1;
        for (int i = 1; i <= n; i++) {
            arr[i] = fastScanner.nextInt();
            if(arr[i] == 0) root = i;
            else{
               tree[arr[i]].add(i); // дети
            }
        }
        //обход дерева время входа, время выхода
        int[] timeIn = new int[n+1];
        int[] timeOut = new int[n+1];

        traversal(timeIn, timeOut, tree, root);

        int m = fastScanner.nextInt(); //количество запросов
        for (int i = 0; i < m; i++) {
            //Для каждого из m запросов выведите на отдельной строке число 1, если вершина
            //a является одним из предков вершины b, и 0 в противном случае.
            int a = fastScanner.nextInt();
            int b = fastScanner.nextInt();
            System.out.println(timeIn[a] <= timeIn[b] && timeOut[a]>= timeOut[b] ? 1 : 0);
        }
    }

    static void traversal(int[] timeIn, int[] timeOut, List<Integer>[] tree, int root){
        List<Integer> list = tree[root];
        timer++;
        timeIn[root] = timer;

        for(Integer val:list){
            traversal(timeIn, timeOut, tree, val);
        }

        timeOut[root] = timer;
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
