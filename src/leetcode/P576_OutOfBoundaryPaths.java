package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
    There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, 
    you can move the ball to adjacent cell or cross the grid boundary in four directions 
    (up, down, left, right). However, you can at most move N times. Find out the number of paths 
    to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.

    Example 1:
    Input:m = 2, n = 2, N = 2, i = 0, j = 0
    Output: 6
    Explanation:
    
    Example 2:
    Input:m = 1, n = 3, N = 3, i = 0, j = 1
    Output: 12
    Explanation:
    
    Note:
    Once you move the ball out of boundary, you cannot move it back.
    The length and height of the grid is in range [1,50].
    N is in range [0,50].
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P576_OutOfBoundaryPaths.java
 * @type        P576_OutOfBoundaryPaths
 * @date        2017年5月8日 下午5:34:36
 * @details     
 */
public class P576_OutOfBoundaryPaths {
    
    static public class Solution {
        public int findPaths(int m, int n, int N, int i, int j) {
            int ans = 0, mod = 1000000007;
            long[][] matrix = new long[m][n];
            matrix[i][j] = 1;
            Queue<Integer> qx = new LinkedList<Integer>();
            Queue<Integer> qy = new LinkedList<Integer>();
            qx.add(i);
            qy.add(j);
            while (! qx.isEmpty() && (N -- > 1)) {
                int size = qx.size();
                while (size -- > 0) {
                    int x = qx.poll(), y = qy.poll();
                    long v = matrix[x][y];
                    if (x-1 > -1) {
                        matrix[x-1][y] = (matrix[x-1][y] + v) % mod;
                        qx.add(x-1);
                        qy.add(y);
                    }
                    if (x+1 < m) {
                        matrix[x+1][y] = (matrix[x+1][y] + v) % mod;
                        qx.add(x+1);
                        qy.add(y);
                    }
                    if (y-1 > -1) {
                        matrix[x][y-1] = (matrix[x][y-1] + v) % mod;
                        qx.add(x);
                        qy.add(y-1);
                    }
                    if (y+1 < n) {
                        matrix[x][y+1] = (matrix[x][y+1] + v) % mod;
                        qx.add(x);
                        qy.add(y+1);
                    }
                }
            }
            tools.Utils.A_打印二维数组(matrix);
            for (int x = 0; x < m; x ++) {
                ans = (int)((ans + matrix[x][0]) % mod);
                ans = (int)((ans + matrix[x][n-1]) % mod);
            }
            for (int y = 0; y < n; y ++) {
                ans = (int)((ans + matrix[0][y]) % mod);
                ans = (int)((ans + matrix[m-1][y]) % mod);
            }
            return ans;
        }
    }
}
