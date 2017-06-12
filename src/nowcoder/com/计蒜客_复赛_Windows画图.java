package nowcoder.com;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        计蒜客_复赛_Windows画图.java
 * @date        2017年6月10日 下午10:14:49
 * @details     AC
 */
public class 计蒜客_复赛_Windows画图 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] ans = new int[m][m];
        for (int i = 0; i < n; i ++) {
            int x1 = scanner.nextInt() - 1;
            int y1 = scanner.nextInt() - 1;
            int x2 = scanner.nextInt() - 1;
            int y2 = scanner.nextInt() - 1;
            ans[x1][y1] = i+1;
            ans[x2][y2] = i+1;
            if (x1 == x2) {
                if (y1 > y2) {
                    for (int dy = y2 + 1; dy < y1; dy ++)
                        ans[x1][dy] = i+1;
                } else {
                    for (int dy = y1 + 1; dy < y2; dy ++)
                        ans[x1][dy] = i+1;
                }
            } else if (y1 == y2) {
                if (x1 > x2) {
                    for (int dx = x2+1; dx < x1; dx ++) {
                        ans[dx][y1] = i+1;
                    }   
                } else {
                    for (int dx = x1+1; dx < x2; dx ++) {
                        ans[dx][y1] = i+1;
                    }   
                }
            } else {
                int cutx = x2 - x1;
                int cuty = y2 - y1;
                int g = gcd(Math.abs(cutx), Math.abs(cuty));
                int stpx = cutx / g;
                int stpy = cuty / g;
                int dx = x1, dy = y1;
                while (dx != x2) {
                    dx += stpx;
                    dy += stpy;
                    ans[dx][dy] = i+1;
                }
            }
        }
        int k = scanner.nextInt();
        for (int i = 0; i < k; i ++) {
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;
            System.out.println(ans[x][y]);
        }
        scanner.close();
    }
    static int gcd(int a, int b) {
        if (a < b) return gcd(b, a);
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
