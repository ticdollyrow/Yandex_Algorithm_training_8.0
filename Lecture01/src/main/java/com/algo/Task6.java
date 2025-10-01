package com.algo;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

///F. Плюсы, минусы и вопросы
public class Task6 {
    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt(); //строки
        int m = fastScanner.nextInt(); //количество символов

        int[][] columnSum = new int[m][2];
        int[][] rowSum = new int[n][2];
        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>(Comparator.reverseOrder());
        HashSet<String> questionSet = new HashSet<>();

        int rowVal;
        for(int i = 0; i < n; i++){
            int j = 0; char sym; rowVal = 0;

            int c;
            do {
                c = fastScanner.read();
            } while (c <= ' ');

            while (c > ' ') {
                sym = (char) c;
                c = fastScanner.read();

                if(sym == '+'){
                    columnSum[j][0] +=1;
                    columnSum[j][1] = j;
                    rowVal += 1;
                }else if( sym == '-'){
                    columnSum[j][0] -=1;
                    columnSum[j][1] = j;
                    rowVal -=1;
                }else{
                    columnSum[j][0] -=1;
                    columnSum[j][1] = j;
                    rowVal +=1;
                    questionSet.add(i +","+j);
                }
                j++;
            }

            rowSum[i][0] = rowVal;
            rowSum[i][1] = i;
//            if(!treeMap.isEmpty()) {
//                int maxLineVal = treeMap.firstKey();
//                if (maxLineVal - rowVal > 1) continue;
//            }
//            List<Integer> list = new ArrayList<>();
//            if(treeMap.containsKey(rowVal)){
//                list = treeMap.get(rowVal);
//                list.add(i);
//            }else{
//                list.add(i);
//                treeMap.put(rowVal, list);
//            }
        }

        Arrays.sort(columnSum, (o1, o2) -> o1[0] - o2[0]);
        Arrays.sort(rowSum, (o1, o2) -> o1[0] - o2[0]);

        int max = Integer.MIN_VALUE;
//        int maxLineVal = treeMap.firstKey();
        int maxLineVal = rowSum[0][0];
        int maxColumnVal = columnSum[0][0];
//        for(Integer val : treeMap.keySet()){
        for(int i = 0; i < n; i++){
            int val = rowSum[i][0];
            if(maxLineVal - val > 1) break;

//            List<Integer> list = treeMap.get(val);
//            int diff = 0;
//            for(int col = 0; col < m; col++){
//                if(maxColumnVal - columnSum[col][0] > 1) break;
//                for(Integer row:list){
//                    diff = val - columnSum[col][0];
//                    if(questionSet.contains(row+","+columnSum[col][1])){
//                        diff -= 2;
//                    }
//                    max = Math.max(max, diff);
//                }
//            }
            int diff = 0;
            for(int col = 0; col < m; col++){
                if(maxColumnVal - columnSum[col][0] > 1) break;
                diff = val - columnSum[col][0];
                if(questionSet.contains(rowSum[i][1]+","+columnSum[col][1])){
                        diff -= 2;
                }
                max = Math.max(max, diff);
            }
        }
        System.out.println(max);
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
