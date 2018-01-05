package nowcoder.com;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        蘑菇街_投篮游戏.java
 * @date        2017年7月11日 下午10:21:43
 * @details     
 */
public class 蘑菇街_投篮游戏 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            sovle(sc);
        }
        sc.close();
    }

    private static void sovle(Scanner sc) {
        int p = sc.nextInt();
        int n = sc.nextInt();
        boolean[] a = new boolean[p];
        int ans = -1;
        for (int i = 0; i < n; i ++) {
            int x = sc.nextInt();
            if (ans == -1) {
                int xx = x % p;
                if (a[xx]) {
                    ans = i + 1;
                } else {
                    a[xx] = true;
                }
            }
        }
        System.out.println(ans);
    }
}
