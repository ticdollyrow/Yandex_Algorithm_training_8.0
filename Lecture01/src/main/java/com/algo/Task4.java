package com.algo;

/// D. Отборочный контест
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class Task4 {
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt(); //количество задач, придуманных сотрудниками
        int k = fs.nextInt(); //количество задач в контесте
        int num = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            num = fs.nextInt();
            map.merge(num,1,Integer::sum);
        }
        int size = map.size();
        if(size >= k){
            for(Integer key: map.keySet()) {
                System.out.print( key + " ");
                k--;
                if(k == 0) break;
            }
        }else{
            int diff = k - size;
            for(Integer key: map.keySet()){
                Integer value = map.get(key);
                value--;
                System.out.print( key + " ");
                while (value > 0 && diff > 0){
                    System.out.print( key + " ");
                    value--;
                    diff--;
                }
            }
        }
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
    }
}
