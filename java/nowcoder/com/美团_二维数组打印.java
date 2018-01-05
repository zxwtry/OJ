package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        美团_二维数组打印.java
 * @date        2017年7月15日 下午10:33:17
 * @details     
 */
public class 美团_二维数组打印 {
    static public class Printer {
        public int[] arrayPrint(int[][] arr, int n) {
            // write code here
            int[] ans = new int[n * n];
            int ai[] = {0};
            int x = 0;
            int y = n - 1;
            for (; y > -1; y --) {
                add(arr, x, y, ans, ai);
            }
            x = 1;
            y = 0;
            for (; x < n; x ++) {
                add(arr, x, y, ans, ai);
            }
            return ans;
        }
        void add(int[][] arr, int i, int j, int[] ans, int[] ai) {
            while (i > -1 && i < arr.length && j > -1 && j < arr.length) {
                ans[ai[0] ++] = arr[i][j];
                i ++;
                j ++;
            }
        }
    }
}
