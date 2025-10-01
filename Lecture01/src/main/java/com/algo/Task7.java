package com.algo;


import java.io.IOException;
import java.io.InputStream;

/// Пять подряд
public class Task7 {
    private final static int FIVE = 5;
    private final static String YES = "Yes";
    private final static String NO = "No";
    private final static char DOT = '.';
    private final static char SIGN_X = 'X';
    private final static char SIGN_O = 'O';

    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt(); //строки
        int m = fastScanner.nextInt();
        if (n < FIVE && m < FIVE) {
            System.out.println(NO);
            return;
        }

        char[][] coordinate = new char[n][m];
        int count = 1;
        for (int i = 0; i < n; i++) {
            int c;
            char sym;
            int col = 0;

            do {
                c = fastScanner.read();
            } while (c <= ' ');

            while (c > ' ') {
                sym = (char) c;
                c = fastScanner.read();
                if (sym == (char) c && sym != DOT) {
                    count++;
                    if (count >= FIVE) {
                        System.out.println(YES);
                        return;
                    }
                } else {
                    count = 1;
                }

                coordinate[i][col] = sym;
                col++;
            }
        }

        if (count >= FIVE) {
            System.out.println(YES);
            return;
        }

        //по горизонтали проверили при считывании
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (coordinate[i][j] == DOT) continue;
                char sign = coordinate[i][j];
                //вертикаль
                if (i + 4 < n) {
                    if (coordinate[i + 1][j] == sign && coordinate[i + 2][j] == sign
                            && coordinate[i + 3][j] == sign && coordinate[i + 4][j] == sign) {
                        System.out.println(YES);
                        return;
                    }
                }

                if (j + 4 < m && i + 4 < n) {
                    if (coordinate[i + 1][j + 1] == sign && coordinate[i + 2][j + 2] == sign
                            && coordinate[i + 3][j + 3] == sign && coordinate[i + 4][j + 4] == sign) {
                        System.out.println(YES);
                        return;
                    }
                }

                if (j + 4 < m && i - 4 >= 0) {
                    if (coordinate[i - 1][j + 1] == sign && coordinate[i - 2][j + 2] == sign
                            && coordinate[i - 3][j + 3] == sign && coordinate[i - 4][j + 4] == sign) {
                        System.out.println(YES);
                        return;
                    }
                }
            }
        }

        System.out.println(NO);
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
