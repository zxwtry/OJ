package leetcode;

/**
    f代表能够从i飞到j
    d代表在i地方，在j星期，休息d[i][j]天
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P568_MaximumVacationDays.java
 * @type        P568_MaximumVacationDays
 * @date        2017年5月4日 下午10:26:15
 * @details
 */
public class P568_MaximumVacationDays {
    public static void main(String[] args) {
        int[][] f = {
                {0,1,1},
                {1,0,1},
                {1,1,0},
//                {0, 0, 0},
//                {0, 0, 0},
//                {0, 0, 0},
        };
        int[][] d = {
//                {1,3,1},
//                {6,0,3},
//                {3,3,3},
//                {1, 1, 1},
//                {7, 7, 7},
//                {7, 7, 7},

                {7, 0, 0},
                {0, 7, 0},
                {0, 0, 7},
        };
        System.out.println(new Solution().maxVacationDays(f, d));
    }
    
    static public class Solution {
        public int maxVacationDays(int[][] f, int[][] d) {
            int n = f == null ? 0 : f.length;
            if (n == 0) return 0;
            int k = d[0] == null ? 0 : d[0].length;
            if (k == 0) return 0;
            int[] ans = {0};
            search(f, d, n, k, 0, 0, ans, 0);
            return ans[0];
        }
        private void search(int[][] f, int[][] d, int n, int k, int ni, int ki, int[] a, int c) {
            a[0] = Math.max(a[0], c);
            if (ki == k) return;
            for(int nj = 0; nj < n; nj ++) {
                if (ni == nj || f[ni][nj] == 1) {
                    search(f, d, n, k, nj, ki+1, a, c+d[nj][ki]);
                }
            }
        }
    }
    
}
