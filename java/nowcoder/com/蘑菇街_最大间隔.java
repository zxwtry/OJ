package nowcoder.com;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        蘑菇街_最大间隔.java
 * @date        2017年7月8日 下午10:01:24
 * @details     
 */
public class 蘑菇街_最大间隔 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc);
        }
        sc.close();
    }
    static void solve(Scanner sc) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        int max = 0;
        for (int i = 0; i < n; i ++) {
            arr[i] = sc.nextInt();
            if (i != 0) {
                max = Math.max(max, arr[i] - arr[i - 1]);
            }
        }
        if (n < 3) {
            System.out.println(max);
        } else {
            int ans = Math.max(arr[2] - arr[0], max);
            for (int i = 3; i < n; i ++) {
                ans = Math.min(ans, Math.max(arr[i] - arr[i - 1], max));
            }
            System.out.println(ans);
        }
    }
}
