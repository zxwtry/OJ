package leetcode;

/**
 *  Given a 01 matrix M, find the longest line of consecutive one in the matrix. 
 *  The line could be horizontal, vertical, diagonal or anti-diagonal.

    Example:
    Input:
    [[0,1,1,0],
     [0,1,1,0],
     [0,0,0,1]]
    Output: 3
    Hint: The number of elements in the given matrix will not exceed 10,000.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P562_LongestLineOfConsecutiveOneInMatrix.java
 * @type        P562_LongestLineOfConsecutiveOneInMatrix
 * @date        2017年4月23日 上午10:06:20
 * @details     Solution: AC 35ms 50.00%
 */
public class P562_LongestLineOfConsecutiveOneInMatrix {
    public static void main(String[] args) {
        int[][] M = {
                {1,1,1,1},
//                {0,1,0,0},
//                {0,1,0,0},
        };
        System.out.println(new Solution().longestLine(M));
    }
    static class Solution {
        public int longestLine(int[][] M) {
            int rn = M == null ? 0 : M.length;
            if (rn == 0) return 0;
            int cn = M[0] == null ? 0 : M[0].length;
            if (cn == 0) return 0;
            int ans = 0, cnt = 0;
            for (int i = 0; i < rn; i ++) {
                cnt = 0;
                for (int j = 0; j < cn; j ++) {
                    if (M[i][j] == 0) cnt = 0;
                    else cnt ++;
                    ans = Math.max(ans, cnt);
                }
            }
            for (int j = 0; j < cn; j ++) {
                cnt = 0;
                for (int i = 0; i < rn; i ++) {
                    if (M[i][j] == 0) cnt = 0;
                    else cnt ++;
                    ans = Math.max(ans, cnt);
                }
            }
            for (int i = 0; i < rn+cn-1; i ++) {
                int ri = i < rn ? i : rn-1;
                int ci = i < rn ? 0 : i-rn+1;
                cnt = 0;
                while (ri > -1 && ci < cn) {
                    if (M[ri][ci] == 0) cnt = 0;
                    else cnt ++;
                    ans = Math.max(ans, cnt);
                    ri --;
                    ci ++;
                }
            }
            for (int i = 0; i < rn+cn-1; i ++) {
                int ri = i < rn ? rn-1-i : 0;
                int ci = i < rn ? 0 : i-rn+1;
                cnt = 0;
                while (ri < rn && ci < cn) {
                    if (M[ri][ci] == 0) cnt = 0;
                    else cnt ++;
                    ans = Math.max(ans, cnt);
                    ri ++;
                    ci ++;
                }
            }
            return ans;
        }
    }
}
