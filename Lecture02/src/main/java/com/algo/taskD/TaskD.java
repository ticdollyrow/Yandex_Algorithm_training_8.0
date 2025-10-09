package com.algo.taskD;

/*
whatevercanido
5
what
whatever
can
i
do
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class TaskD {
    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        String sourceLine = fastScanner.next(); //одна строка из не более чем 100 латинских строчных букв
        int n = fastScanner.nextInt(); //количество слов в словаре
        HashSet<String> dictionary = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String tmp = fastScanner.next();
            dictionary.add(tmp);
        }

        int size = sourceLine.length();
        boolean[] dp = new boolean[size + 1];
        int[] indexWords = new int[size+1];
        Arrays.fill(indexWords, -1);
        dp[0] = true;
        for (int i = 1; i <= size; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j]) {
                    String sub = sourceLine.substring(j, i);
                    if (dictionary.contains(sub)) {
                        dp[i] = true;
                        indexWords[i] = j; // начинается слово
                        break;
                    } else {
                        dp[i] = false;
                    }
                }
            }
        }

        List<String> list = new ArrayList<>();
        int i = size;
        while(i>0){
            String sub = sourceLine.substring(indexWords[i], i);
            list.add(sub);
            i = indexWords[i];
        }


        Collections.reverse(list);
        System.out.println(String.join(" ", list));
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
