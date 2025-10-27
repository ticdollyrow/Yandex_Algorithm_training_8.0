package com.algo.taskA;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TaskA {
    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt(); //количество рейсов из первого офиса во второй
        String[] office1From = new String[n];
        ArrayList<String> office1To = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            String time = fastScanner.next();
            final String[] split = time.split("-");
            office1From[i] = split[0];
            office1To.add(split[1]);
        }
        Arrays.sort(office1From);
        Collections.sort(office1To);

        int m = fastScanner.nextInt(); //количество рейсов из второго офиса
        String[] office2From = new String[m];
        ArrayList<String> office2To = new ArrayList<>();
        for(int i = 0; i < m; i ++){
            String time = fastScanner.next();
            final String[] split = time.split("-");
            office2From[i] = split[0];
            office2To.add(split[1]);
        }
        int bus = 0;
        Arrays.sort(office2From);
        Collections.sort(office2To);

        for(var time:office1From){
           if(!office2To.isEmpty() && office2To.getFirst().compareTo(time) <= 0){
               office2To.removeFirst();
           }else{
               bus++;
           }
        }

        for(var time:office2From){
            if( !office1To.isEmpty() && office1To.getFirst().compareTo(time) <= 0){
                office1To.removeFirst();
            }else{
                bus++;
            }
        }

        System.out.println(bus);
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
