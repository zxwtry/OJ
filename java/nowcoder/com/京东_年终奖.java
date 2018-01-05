package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        京东_年终奖.java
 * @date        2017年7月15日 上午9:38:06
 * @details     
 */
public class 京东_年终奖 {
    static public class Bonus {
        public int getMost(int[][] board) {
            // write code here
            int rn = board.length;
            int cn = board[0].length;
            int[][] ans = new int[rn][cn];
            for (int i = 0; i < rn; i ++) {
                for (int j = 0; j < cn; j ++) {
                    ans[i][j] += board[i][j];
                    ans[i][j] += Math.max(access(ans, i - 1, j), access(ans, i, j - 1));
                }
            }
            return (int) ans[rn - 1][cn - 1];
        }
        int access(int[][] ans, int i, int j) {
            if (i < 0) return 0;
            if (j < 0) return 0;
            return ans[i][j];
        }
    }
}
