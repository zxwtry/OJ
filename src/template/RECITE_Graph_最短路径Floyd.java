package template;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_Graph_最短路径Floyd.java
 * @date        2017年7月26日 下午1:38:40
 * @details     AC solve  http://hihocoder.com/problemset/problem/1089
 * @details     AC solve2 http://poj.org/problem?id=1502
 */
public class RECITE_Graph_最短路径Floyd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
//            solve(sc);
            solve2(sc);
        }
        sc.close();
    }

    private static void solve2(Scanner sc) {
        n = sc.nextInt();
        for (int i = 1; i < n; i ++) {
            for (int j = 0; j < i; j ++) {
                String s = sc.next();
                if (s.charAt(0) == 'x') {
                    ws[i][j] = MAX;
                    ws[j][i] = MAX;
                } else {
                    int v = Integer.parseInt(s);
                    ws[i][j] = v;
                    ws[j][i] = v;
                }
            }
        }
        for (int k = 0; k < n; k ++) {
            for (int i = 0; i < n; i ++) {
                for (int j = 0; j < n; j ++) {
                    if (i != j && j != k) {
                        ws[i][j] = Math.min(ws[i][j], ws[i][k] + ws[k][j]);
                    }
                }
            }
        }
        int max = ws[0][1];
        for (int j = 2; j < n; j ++)
            max = Math.max(max, ws[0][j]);
        System.out.println(max);
    }

    static int MAXN = 105;
    static int[][] ws = new int[MAXN][MAXN];
    static int n;
    static int m;
    static int MAX = Integer.MAX_VALUE / 4;
    
    static void solve(Scanner sc) {
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                ws[i][j] = i == j ? 0 : MAX;
            }
        }
        for (int k = 0; k < m; k ++) {
            int i = sc.nextInt() - 1;
            int j = sc.nextInt() - 1;
            int v = sc.nextInt();
            v = Math.min(v, ws[i][j]);
            ws[i][j] = ws[j][i] = v;
        }
        for (int k = 0; k < n; k ++) {
            for (int i = 0; i < n; i ++) {
                for (int j = 0; j < n; j ++) {
                    if (i != j && j != k) {
                        ws[i][j] = Math.min(ws[i][j], ws[i][k] + ws[k][j]);
                    }
                }
            }
        }
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                System.out.print(ws[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
