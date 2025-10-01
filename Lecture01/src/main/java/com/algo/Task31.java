package com.algo;

import java.io.IOException;
import java.io.InputStream;

public class Task31 {
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        String password = fs.next();
        long count = 1;
        for(int i = 0; i < password.length(); i++){
            for(int j = i+1; j < password.length(); j++){
                if(password.charAt(i) != password.charAt(j)){
                    count++;
                }
            }
        }
        System.out.println(count);
    }


    static final class FastScanner {
        private final byte[] buf = new byte[1 << 16];
        private int len = 0, ptr = 0;
        private final InputStream in;
        FastScanner(InputStream in) { this.in = in; }

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
                len = in.read(buf); ptr = 0;
                if (len < 0) return -1;
            }
            return buf[ptr++];
        }
    }
}
