package template;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_DP_01背包.java
 * @date        2017年7月25日 下午3:10:48
 * @details     http://hihocoder.com/problemset/problem/1038
 */
public class RECITE_DP_01背包 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc);
        }
        sc.close();
    }

    private static void solve(Scanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] ns = new int[n];
        int[] vs = new int[n];
        for (int i = 0; i < n; i ++) {
            ns[i] = sc.nextInt();
            vs[i] = sc.nextInt();
        }
        int[] dp = new int[m + 1];
        for (int i = 0; i < n;i ++) {
            for (int j = m; j >= ns[i]; j --) {
                dp[j] = Math.max(dp[j], dp[j - ns[i]] + vs[i]);
            }
        }
        System.out.println(dp[m]);
    }
}
