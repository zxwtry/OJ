package nowcoder.com;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        百度_蘑菇阵.java
 * @date        2017年7月11日 下午9:29:52
 * @details     
 */
public class 百度_蘑菇阵 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            sovle(sc);
        }
        sc.close();
    }

    static void sovle(Scanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        boolean[][] arr = new boolean[n][m];
        for (int i = 0; i < k; i ++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            arr[x][y] = true;
        }
        double[][] dou = new double[n][m];
        dou[0][0] = 1;
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                if (i == n - 1 && j == m - 1) continue;
                int i1 = i + 1, j1 = j;
                int i2 = i, j2 = j + 1;
                int one = 0;
                if (i1 == n) {
                    i1 = -1;
                } else {
                    one ++;
                }
                if (j2 == m ) {
                    i2 = -1;
                } else {
                    one ++;
                }
                double d = dou[i][j] / one;
                if (i1 != -1 && ! arr[i1][j1]) {
                    dou[i1][j1] += d;
                }
                if (i2 != -1 && ! arr[i2][j2]) {
                    dou[i2][j2] += d;
                }
            }
        }
        System.out.printf("%.2f\n", dou[n - 1][m - 1]);
    }
}
