package com.algo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/// H. Разрезанная строка
public class Task8 {
    public static void main(String[] args) throws IOException{
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt(); //длина строки
        int m = fastScanner.nextInt(); //количество кусков
        String s = fastScanner.next(); //строка, которую необходимо получить

        int index;
        int[] order = new int[m];
        int length = n / m;
        HashMap<String, List<Integer>> indexMap = new HashMap<>();
        for(int i = 0; i < m; i++){
            index = i * length;
            String sub = s.substring(index, index+length);
            List<Integer> list = new ArrayList<>();
            if(indexMap.containsKey(sub)){
                list = indexMap.get(sub);
                list.add(index);
            }else{
                list.add(index);
                indexMap.put(sub, list);
            }
        }

        for(int i = 0; i < m; i++){
            String substring = fastScanner.next();
            List<Integer> list = indexMap.get(substring);
            Integer i1 = list.removeFirst();
            order[i1 / length] = i+1;
        }

        for(int i = 0; i < m; i++){
            System.out.print(order[i] + " ");
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

        String next() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');
            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
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
