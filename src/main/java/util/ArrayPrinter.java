package util;

import java.util.Arrays;

public class ArrayPrinter {
    public static void printBoolean(boolean[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] ? 1 : 0);
                System.out.print(' ');
            }
            System.out.print('\n');
        }
    }
}
