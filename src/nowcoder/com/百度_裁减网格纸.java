package nowcoder.com;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        百度_裁减网格纸.java
 * @date        2017年7月11日 下午8:50:12
 * @details     
 */
public class 百度_裁减网格纸 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext())
        solve1(sc);
        sc.close();
    }

    static void solve1(Scanner sc) {
        int n = sc.nextInt();
        long xmax = Long.MIN_VALUE;
        long xmin = Long.MAX_VALUE;
        long ymax = Long.MIN_VALUE;
        long ymin = Long.MAX_VALUE;
        for (int i = 0; i < n; i ++) {
            long x = sc.nextLong();
            long y = sc.nextLong();
            xmax = Math.max(xmax, x);
            ymax = Math.max(ymax, y);
            xmin = Math.min(xmin, x);
            ymin = Math.min(ymin, y);
        }
        long max = Math.max(ymax - ymin, xmax - xmin);
        System.out.println(max * max);
    }
}
