package hiho;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     hiho
 * @file        题库1033_交错和.java
 * @date        2017年7月28日 下午10:01:26
 * @details     AC http://hihocoder.com/problemset/problem/1033
 */
public class 题库1033_交错和 {
    static final int MOD = 1000000007;
    static long l, r, k;
    static long f[][][] = new long[25][410][2];
    static long g[][][] = new long[25][410][2];
    static long h[] = new long[25];
    static long c[] = new long[25];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc);
        }
        sc.close();
    }
    private static void solve(Scanner sc) {
        l = sc.nextLong();
        r = sc.nextLong();
        k = sc.nextLong();
        h[1] = 1;
        for (int i = 2; i <= 20; i ++) {
            h[i] = h[i - 1] * 10 % MOD;
        }
        System.out.println(((calc(r)-calc(l-1))%MOD+MOD)%MOD);
    }
    static long calc(long x) {
        if (x == -1) return 0;
        long ans = 0, p = x;
        int len = 0;
        while (p != 0) {
            c[++ len] = p % 10;
            p /= 10;
        }
        for (int st = len; st != 0; st --) {
            fillForG(f);
            fillForG(g);
            if (st == len) {
                g[st][200][1] = 1;
            } else {
                g[st][200][0] = 1;
            }
            for (int i = st; i != 0; i --) {
                int add = i - st;
                add = (add & 1) == 1 ? -1 : 1;
                for (int j = 0; j <= 400; j ++) {
                    if (f[i][j][0] != 0 || 
                        f[i][j][1] != 0 || 
                        g[i][j][0] != 0 || 
                        g[i][j][1] != 0) {
                        int v = 0;
                        v = i == st ? 1 : 0;
                        for (; v < 10; v ++) {
                            update(g[i-1][j+add*v], 0, g[i][j][0]);
                            update(f[i-1][j+add*v], 0, f[i][j][0]
                                    + g[i][j][0] * v % MOD * h[i] % MOD );
                        }
                        v = i == st ? 1 : 0;
                        for (; v <= c[i]; v ++) {
                            int type = v == c[i] ? 1 : 0;
                            update(g[i-1][j+add*v], type, g[i][j][1]);
                            update(f[i-1][j+add*v], type, f[i][j][1]
                                    + g[i][j][1] * v % MOD * h[i] % MOD );
                        }
                    }
                }
            }
            ans = (ans + f[0][(int)(k+200)][0]) % MOD;
            ans = (ans + f[0][(int)(k+200)][1]) % MOD;
        }
        return ans;
    }
    static void fillForG(long[][][] fg) {
        for (int i = 0; i < fg.length; i ++) {
            for (int j = 0; j < fg[i].length; j ++) {
                for (int v = 0; v < fg[i][j].length; v ++) {
                    fg[i][j][v] = 0;
                }
            }
        }
    }
    static void update(long[] a, int ai, long b) {
        a[ai] += b;
        a[ai] %= MOD;
    }
}
