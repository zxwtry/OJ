package leetcode;

/**
 * 	Given an integer matrix, find the length of the longest increasing path.
 *  
 *  From each cell, you can either move to four directions: left, right, up or down.
 *  You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is
 *  not allowed).
 *  
 *  Example 1:
 *  
 *  nums = [
 *    [9,9,4],
 *    [6,6,8],
 *    [2,1,1]
 *  ]
 *  Return 4
 *  The longest increasing path is [1, 2, 6, 9].
 *  
 *  Example 2:
 *  
 *  nums = [
 *    [3,4,5],
 *    [3,2,6],
 *    [2,2,1]
 *  ]
 *  Return 4
 *  The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P329_LongestIncreasingPathInAMatrix.java
 * @type        P329_LongestIncreasingPathInAMatrix
 * @date        2017年1月10日 下午10:02:26
 * @details     Solution1: AC 20ms 38.61% *
 * @details     Solution3: AC 16ms 62.52% *
 */
public class P329_LongestIncreasingPathInAMatrix {
	static class Solution1 {
	    int row = 0, col = 0;
	    int[][] record = null;
	    boolean[][] traveled = null;
	    int[] xs = new int[] {0,   1,  0,  -1};
	    int[] ys = new int[] {-1,  0,  1,  0};
	    int max = 0;
	    public int longestIncreasingPath(int[][] matrix) {
	        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
	            return 0;
	        row = matrix.length;
	        col = matrix[0].length;
	        record = new int[row][col]; 
	        traveled = new boolean[row][col];
	        for (int xi = 0; xi < row; xi ++) {
	            for (int yi = 0; yi < col; yi ++) {
	                visit(matrix, xi, yi);
	            }
	        }
	        return max;
	    }
        private void visit(int[][] matrix, int xi, int yi) {
            if (record[xi][yi] != 0) return;
            record[xi][yi] = 1;
            traveled[xi][yi] = true;
            for (int xsIndex = 0; xsIndex < xs.length; xsIndex ++) {
                int newX = xi + xs[xsIndex];
                if (newX < 0 || newX >= row) continue;
                int newY = yi + ys[xsIndex];
                if (newY < 0 || newY >= col) continue;
                if (matrix[newX][newY] <= matrix[xi][yi]) continue;
                if (record[newX][newY] != 0) {
                    record[xi][yi] = Math.max(record[xi][yi], 1 + record[newX][newY]);
                    continue;
                }
                if (traveled[newX][newY]) continue;
                visit(matrix, newX, newY);
                record[xi][yi] = Math.max(record[xi][yi], 1 + record[newX][newY]);
            }
            max = Math.max(max, record[xi][yi]);
            traveled[xi][yi] = false;
        }
	}
	static class Solution3 {
	    public static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	    public int longestIncreasingPath(int[][] matrix) {
	        if(matrix.length == 0) return 0;
	        int m = matrix.length, n = matrix[0].length;
	        int[][] cache = new int[m][n];
	        int max = 1;
	        for(int i = 0; i < m; i++) {
	            for(int j = 0; j < n; j++) {
	                int len = dfs(matrix, i, j, m, n, cache);
	                max = Math.max(max, len);
	            }
	        }   
	        return max;
	    }
	    public int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
	        if(cache[i][j] != 0) return cache[i][j];
	        int max = 1;
	        for(int[] dir: dirs) {
	            int x = i + dir[0], y = j + dir[1];
	            if(x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) continue;
	            int len = 1 + dfs(matrix, x, y, m, n, cache);
	            max = Math.max(max, len);
	        }
	        cache[i][j] = max;
	        return max;
	    }
	}
}
