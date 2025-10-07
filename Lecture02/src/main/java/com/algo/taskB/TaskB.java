package com.algo.taskB;

import java.io.IOException;
import java.io.InputStream;

/// B. Поход
public class TaskB {

    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int c; char sym;
        do {
            c = fastScanner.read();
        } while (c <= ' ');

        int left = 0;
        int right = 1;
        while (c > ' ') {
            sym = (char) c;
            c = fastScanner.read();
            switch (sym){
                case 'L' ->{
                    left = Math.min(left+1, right+1);
                    right = Math.min(right, left+1);
                }
                case 'R' ->{
                    left = Math.min(left, right+1);
                    right = Math.min(right+1, left+1);
                }
                case 'B' ->{
                    left +=1;
                    right +=1;
                }
            }
        }

        var count = Math.min(left+1, right);
        System.out.println(count);
    }

    static final class FastScanner {
        private final byte[] buf = new byte[1 << 16];
        private int len = 0, ptr = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
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
