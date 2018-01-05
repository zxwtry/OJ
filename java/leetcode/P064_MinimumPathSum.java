package leetcode;

/*
 * 	Given a m x n grid filled with non-negative numbers, 
 * 	find a path from top left to bottom right 
 * 	which minimizes the sum of all numbers along its path.

	Note: You can only move either down or right at any point in time.
 */

public class P064_MinimumPathSum {
	public static void main(String[] args) {
		
	}
	/*
	 * 	写完就交，直接AC，好爽！！！
	 * 	4 ms
	 * 	51.74%
	 */
	static class Solution {
	    public int minPathSum(int[][] grid) {
	        int m = 0, n = 0;
	        if (grid == null || (m = grid.length) == 0 || (n = grid[0].length) == 0)
	        	return 0;
	        for (int j = 1; j < n; j ++)
	        	grid[0][j] += grid[0][j - 1];
	        for (int i = 1; i < m; i ++) {
	        	grid[i][0] += grid[i - 1][0];
	        	for (int j = 1; j < n; j ++)
	        		grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
	        }
	        return grid[m - 1][n - 1];
	    }
	}
}