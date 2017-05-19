package nowcoder.com;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        牛客模拟_1.java
 * @type        牛客模拟_1
 * @date        2017年5月19日 下午10:32:39
 * @details     
 */
public class 牛客模拟_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {//注意while处理多个case
//            int a = in.nextInt();
//            int b = in.nextInt();
//            System.out.println(a + b);
//        }
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i ++)
            a[i] = in.nextInt();
        
        int[] dp = new int[n+1];
        int[] save = new int[n];
        for (int i = 0; i < n; i ++)
            save[i] = i;
//        boolean[][] m = k(a);
        for (int i = 0; i < n; i ++) {
            if (i == 0 || (save[i-1] == 0 && )j(a, 0, i)) {
//            if (m[0][i]) {
                dp[i+1] = 0;
            } else {
                dp[i+1] = Integer.MAX_VALUE / 2;
                boolean find = false;
                for (int k = 0; k < i; k ++) {
                    if ( find || save[i] <= k || j(a, k, i)) {
                        find = true;
                        dp[i+1] = Math.min(dp[k] + 1, dp[i+1]);
                        save[i] = Math.min(save[i], k);
                    }
//                    if (! j(a, k, i)) continue;
////                    if (! m[k][i]) continue;
//                    dp[i+1] = Math.min(dp[k] + 1, dp[i+1]);
                }
            }
        }
        System.out.println(dp[n] + 1);
    }
    
    static boolean[][] k(int[] a) {
        int n = a.length;
        boolean[] [] m = new boolean[n][];
        for (int i = 0; i < n; i ++) {
            m[i] = new boolean[i+1];
            m[i][i] = true;
        }
        for (int i = 0; i < n; i ++) {
            for (int j = i+1; j < n; j++) {
                if (m[i][j-1] && a[j] >= a[j-1]) {
                    m[i][j] = true;
                }
            }
        }
        boolean pre = true;
        for (int i = 0; i < n; i ++) {
            pre = true;
            for (int j = i+1; j < n; j++) {
                if (pre && a[j] <= a[j-1]) {
                    m[i][j] = true;
                } else {
                    pre = false;
                }
            }
        }
//        for (int i = 0; i < n; i ++) {
//            for (int j = i+1; j < n; j++) {
//                if ( m2[i][j]) {
//                    m[i][j] = true;
//                }
//            }
//            }
        return m;
    }
    
    static boolean j(int[] a, int i, int j) {
        if (i == j) return true;
        int sign = 0;
        for (int k = i+1; k <= j; k ++) {
            if (a[k] == a[k-1]) {
                
            } else if (a[k] >= a[k-1]) {
                if (sign == 1 || sign == 0)
                    sign = 1;
                else return false;
            } else {
                if (sign == -1 || sign == 0)
                    sign = -1;
                else return false;
            }
        }
        return true;
    }
}
