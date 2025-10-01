package com.algo;

import java.io.IOException;
import java.io.InputStream;

///А. Делёж грибов
public class Task1 {
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int[] arr = fs.nextIntArray(n);

        int min = arr[0];
        int max = arr[1];
        int sum = 0;
        for(int i = 0; i < n; i++){
            if(i % 2 == 0){
                sum+= arr[i];
                if(min > arr[i]) min = arr[i];
            }else{
                sum -= arr[i];
                if(max < arr[i]) max = arr[i];
            }
        }
        if(min < max){
            sum += (max - min) * 2;
        }

        System.out.println(sum);
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

        int[] nextIntArray(int n) throws IOException {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
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
