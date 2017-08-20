package leetcode;

/**
 * Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.

Example 1:
Input:
[[1,1,1],
 [1,0,1],
 [1,1,1]]
Output:
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]
Explanation:
For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
For the point (1,1): floor(8/9) = floor(0.88888889) = 0
Note:
The value in the given matrix is in the range of [0, 255].
The length and width of the given matrix are in the range of [1, 150].
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P661_ImageSmoother.java
 * @date        2017年8月20日 上午9:31:09
 * @details     
 */
public class P661_ImageSmoother {
    
    public static void main(String[] args) {
        int[][] m = {{1,1,1},
                {1,0,1},
                {1,1,1}};
        tools.Utils.A_打印二维数组(new Solution().imageSmoother(m));
    }
    
    static class Solution {
        public int[][] imageSmoother(int[][] m) {
            int rn = m == null ? 0 : m.length;
            if (rn == 0) {
                return null;
            }
            int cn = m[0] == null ? 0 : m[0].length;
            if (cn == 0) {
                return null;
            }
            int[][] ans = new int[rn][cn];
            for (int i = 0; i < rn; i ++) {
                for (int j = 0; j < cn; j ++) {
                    int[] cnt = calc(m, i, j, rn, cn);
                    ans[i][j] = (int)(cnt[1] / cnt[0]);
                }
            }
            return ans;
        }

        static final int[] xs = {-1,  0,  1, -1, 0, 1, -1, 0, 1};
        static final int[] ys = {-1, -1, -1,  0, 0, 0,  1, 1, 1};
        
        private int[] calc(int[][] m, int i, int j, int rn, int cn) {
            int cnt = 0;
            int sum = 0;
            for (int k = 0; k < xs.length; k ++) {
                int xadd = xs[k], yadd = ys[k];
                int x = i + xadd;
                int y = j + yadd;
                if (x > -1 && x < rn && y > -1 && y < cn) {
                    cnt ++;
                    sum += m[x][y];
                }
            }
            return new int[] {cnt, sum};
        }
    }
}
