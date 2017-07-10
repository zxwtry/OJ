package nowcoder.com;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        美团_字符串计数.java
 * @date        2017年7月7日 下午10:30:40
 * @details     
 */
public class 美团_字符串计数 {
    private static final int MOD = 1000007;
    private static final char START = (char)('a' - 1);
    private static final int ONE = 26;
    private static String a, b;
    private static int n1, n2, an, bn;
    private static int[] w;
    public static void main(String[] args) throws Exception {
        sol2();
    }
    static void sol2() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            a = sc.next();
            b = sc.next();
            n1 = sc.nextInt();
            n2 = sc.nextInt();
            System.out.println(solve2());
        }
        sc.close();
    }
    private static int solve2() {
        an = a == null ? 0 : a.length();
        bn = b == null ? 0 : b.length();
        int ans = 0;
        for (int n = n1; n <= n2; n ++) {
            ans += calc2(n);
            ans += bn > n ? 1 : 0;
            ans = ans % MOD;
        }
        while (ans < 0) ans += MOD;
        return ans;
    }
    private static int calc2(int n) {
        w = new int[n];
        w[n - 1] = 1;
        for (int i = n - 2; i > -1; i --) {
            w[i] = (w[i + 1] * ONE) % MOD;
        }
        int v1 = cnt2(a, an, n);
        int v2 = cnt2(b, bn, n);
        int v = (v2 - v1 - 1) % MOD;
        while (v < 0) v += MOD;
        return v;
    }
    private static int cnt2(String s, int sn, int n) {
        int v = 0;
        for (int i = 0; i < n; i ++) {
            char c = i < sn ? s.charAt(i) : START;
            v += ((c - START) * w[i]) % MOD;
        }
        return v;
    }
}
