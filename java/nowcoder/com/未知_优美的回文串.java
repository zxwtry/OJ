package nowcoder.com;

import java.util.Scanner;

/**
 * 牛牛在书上看到一种字符串叫做回文串,当一个字符串从左到右和从右到左读都是一样的,
 * 就称这个字符串为回文串。牛牛又从好朋友羊羊那里了解到一种被称为优美的回文串的字符串,
 * 考虑一个长度为N只包含大写字母的字符串,写出它所有长度为M的连续子串
 * (包含所有可能的起始位置的子串,相同的子串也要计入),
 * 如果这个字符串至少有K个子串都是回文串,我们就叫这个字符串为优美的回文串。
 * 现在给出一个N,牛牛希望你能帮他计算出长度为N的字符串有多少个是优美的回文串
 * (每个位置都可以是'A'~'Z'的一个。)
 * 
 *  2 <= N <= 11
 *  0 <= M <= N
 *  0 <= K <= 11 
 *  当 N=2 M=2 K=1，输出26
 *  即："AA", "BB", "CC", "DD" ... "ZZ"
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        未知_优美的回文串.java
 * @type        未知_优美的回文串
 * @date        2017年3月24日 下午2:10:34
 * @details     AC
 */
public class 未知_优美的回文串 {
    static long fac[] = new long[27], res = 0;
    static int n, m, k , a[] = new int[12];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        k = scanner.nextInt();
        fac[0] = 1;
        for (int i = 1; i < 26; i ++) {
            fac[i] = fac[i] * (27 - i);
        }
        dfs(0, 0);
        System.out.println(res);
        scanner.close();
    }
    private static void dfs(int pos, int num) {
        if (pos == n) {
            if (ok())
                res += fac[num];
        } else {
            for (int i = 0; i < num; i ++) {
                a[pos] = i;
                dfs(pos + 1, num);
            }
            a[pos] = num;
            dfs(pos + 1, num + 1);
        }
    }
    private static boolean ok() {
        int cnt = 0;
        for (int i = 0; i <= n - m; i ++) {
            if (ok(i))
                ++ cnt;
        }
        return cnt >= k;
    }
    private static boolean ok(int from) {
        for (int i = 0; i < m / 2; i ++) {
            if (a[from + 1] != a[from + m - i - 1])
                return false;
        }
        return true;
    }
}
