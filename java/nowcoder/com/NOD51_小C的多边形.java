package nowcoder.com;

import java.util.Scanner;

/**
 *  大意：凸多边形和中间一点
 *  n：是输入，是所有点数
 *  凸边：1~n-1
 *  中点：1~n-1
 *  一条凸边+两条相邻中点边=一个三角形
 *  要求所有三角形的边长和相同
 *  有方案输出：先输出凸边，后输出中边
 *  无方案输出：输出0
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        NOD51_小C的多边形.java
 * @type        NOD51_小C的多边形
 * @date        2017年5月1日 下午8:53:38
 * @details     
 */
public class NOD51_小C的多边形 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        solve(scanner.nextInt()-1);
        scanner.close();
//        solve(99);
    }

    private static void solve(int n) {
        if (n % 2 == 0) {
            System.out.println(0);
            return;
        }
        int sum = ((n+1)/2) * 3 - 3;
        int[] i = new int[n], o = new int[n];
        boolean[] im = new boolean[n];
        boolean[] om = new boolean[n];
        i[0] = 0;
        im[0] = true;
        if (search(i, o, im, om, n, sum, 0)) {
            for (int j = 0; j < n; j ++) System.out.print((o[j]+1) + (j==n-1 ? "\n" : " "));
            for (int j = 0; j < n; j ++) System.out.print((i[j]+1) + (j==n-1 ? "\n" : " "));
        } else {
            System.out.println(0);
        }
    }

    private static boolean search(int[] i, int[] o, boolean[] im, boolean[] om, int n, int sum, int j) {
        int lt = sum - i[j], rt = sum - o[j == 0 ? 0 : n-j];
        if (n == 2 * j + 1) {
            for (int k = 0; k < n; k ++) {
                if (! om[k]) {
                    o[j] = k;
                    return true;
                }
            }
            return false;
        }
        for (int l1 = 0; l1 < n; l1 ++) {
            int l2 = lt-l1;
            if (l2 < 0 || l2 >= n || im[l1] || om[l2]) continue;
            im[l1] = true;
            om[l2] = true;
            i[j+1] = l1;
            o[j] = l2;
            for (int r1 = 0; r1 < n; r1 ++) {
                int r2 = rt-r1;
                if (r2 < 0 || r2 >= n || im[r1] || om[r2]) continue;
                im[r1] = true;
                om[r2] = true;
                i[n-1-j] = r1;
                o[n-1-j] = r2;
                if (search(i, o, im, om, n, sum, j+1)) return true;
                im[r1] = false;
                om[r2] = false;
            }
            im[l1] = false;
            om[l2] = false;
        }
        return false;
    }
}
