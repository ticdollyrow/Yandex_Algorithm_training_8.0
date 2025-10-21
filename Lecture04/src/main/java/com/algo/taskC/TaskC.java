package com.algo.taskC;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TaskC {
    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt(); //начальное количество кандидатов в очереди
        int x = fastScanner.nextInt(); //нижняя граница профессионализма

        //профессионализм людей в очереди
        ArrayList<Long> prefix = new ArrayList<>(n+1);
        prefix.add(0L);
        for(int i = 1; i <= n; i++){
            int a = fastScanner.nextInt();
            if(a >= x) {
                prefix.add(prefix.get(i - 1) + 1);
            }else{
                prefix.add(prefix.get(i-1));
            }
        }

        int m = fastScanner.nextInt(); // количество событий, которые происходили с очередью
        int size = prefix.size();
        int first = 0;
        for(int i = 0; i < m; i++){
            int event = fastScanner.nextInt();
            switch (event){
                case 1 ->{
                    int a = fastScanner.nextInt();
                    if(a >= x){
                        prefix.add(prefix.get(size-1)+1);
                    }else{
                        prefix.add(prefix.get(size-1));
                    }
                    size++;
                }
                case 2->{
                    first++;
                }
                case 3->{
                    int k = fastScanner.nextInt();
                    long count = prefix.get(first+k) - prefix.get(first);
                    System.out.println(count);
                }
            }
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
