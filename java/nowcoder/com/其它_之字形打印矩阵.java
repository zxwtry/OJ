package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        其它_之字形打印矩阵.java
 * @date        2017年7月20日 下午10:09:44
 * @details     
 */
public class 其它_之字形打印矩阵 {
    public class Printer {
        public int[] printMatrix(int[][] mat, int n, int m) {
            int[] ans = new int[n * m];
            int ai = 0;
            boolean leftToRight = true;
            for (int i = 0; i < n; i ++) {
                if (leftToRight) {
                    for (int j = 0; j < m; j ++)
                        ans[ai ++] = mat[i][j];
                } else {
                    for (int j = m-1; j > -1; j --)
                        ans[ai ++] = mat[i][j];
                }
                leftToRight = ! leftToRight;
            }
            return ans;
        }
    }
}
