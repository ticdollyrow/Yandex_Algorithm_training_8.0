package com.algo.taskF;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TaskF {
    final static double IN = -1.0;
    final static double OUT = 1.0;
    final static double CAR = 0.0;

    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt(); // количество поездов
        int m = fastScanner.nextInt(); //количество машин
        int x = fastScanner.nextInt(); //точка, в которой находится переезд

        //если поезд подъезжает к перекрестку одновременно с машиной, то переезд считается занятым
        double[][] events = new double[2 * n + m][2];
        int[] cars = new int[m];
        double timeOut;
        double timeIn;
        for (int i = 0, j = 0; j < n; i += 2, j++) {
            int a = fastScanner.nextInt();
            int b = fastScanner.nextInt();
            int v = fastScanner.nextInt();

            if (a < b) {
                timeIn = (double) (x - b) / v;
                timeOut = (double) (x - a) / v;
                if (x > a) {
                    timeOut = Math.abs(timeOut);
                }
                if (x > b) {
                    timeIn = Math.abs(timeIn);
                }
                events[i][1] = IN;
                events[i][0] = timeIn;

                events[i + 1][1] = OUT;
                events[i + 1][0] = timeOut;
            } else {
                timeIn = (double) (b - x) / v;
                timeOut = (double) (a - x) / v;
                if (x < b) {
                    timeIn = Math.abs(timeIn);
                }
                if (x < a) {
                    timeOut = Math.abs(timeOut);
                }
                events[i][1] = IN;
                events[i][0] = timeIn;
                events[i + 1][1] = OUT;
                events[i + 1][0] = timeOut;
            }
        }

        for (int i = 2 * n, j = 0; i < 2 * n + m; i++, j++) {
            int t = fastScanner.nextInt();
            events[i][0] = (double) t;
            events[i][1] = CAR;
            cars[j] = t;
        }

        Arrays.sort(events, (Comparator.comparingDouble((double[] o) -> o[0])
                .thenComparingDouble(o -> o[1])));

        ArrayList<Integer> carsIn = new ArrayList<>();
        HashMap<Integer, Double> carsOut = new HashMap<>();
        int zug = 0;
        for (int i = 0; i < 2 * n + m; i++) {
            if (events[i][1] == IN) zug++;
            else if (events[i][1] == OUT) {
                zug--;
                if (zug == 0 && !carsIn.isEmpty()) {
                    timeOut = events[i][0];
                   // System.out.printf("%.15f%n", timeOut);
                    for(var carIn:carsIn){
                       carsOut.put(carIn, timeOut);
                    }
                    carsIn.clear();
                }
            } else if (events[i][1] == CAR) {
                timeOut = events[i][0];
                if (zug == 0) {
                    carsOut.put((int)timeOut, timeOut);
                } else {
                    carsIn.add((int) timeOut);
                }
            }
        }

        for(var car:cars){
            timeOut = carsOut.get(car);
            System.out.printf("%.15f%n", timeOut);
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
