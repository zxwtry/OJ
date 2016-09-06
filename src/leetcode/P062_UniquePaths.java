package leetcode;

import java.util.Arrays;

/*
 * 	A robot is located at the top-left corner of a m x n grid 
 * 	(marked 'Start' in the diagram below).

	The robot can only move either down or right at any point in time. 
	The robot is trying to reach the bottom-right corner of the grid 
	(marked 'Finish' in the diagram below).
	
	How many possible unique paths are there?
	
	
	Above is a 3 x 7 grid. How many possible unique paths are there?
	
	Note: m and n will be at most 100.
 */

public class P062_UniquePaths {
	public static void main(String[] args) {
		System.out.println(new Solution().uniquePaths(3, 3));
	}
	/*
	 * 	先写一份空间O(m * n)，时间O(m * n)的版本
	 * 	1 ms
	 * 	5.22%
	 */
	static class Solution {
	    public int uniquePaths(int m, int n) {
	    	if (m < 1 || n < 1)
	    		return 0;
	        int[][] route = new int[m][n];
	        Arrays.fill(route[0], 1);
	        for (int i = 1; i < m; i ++)
	        	route[i][0] = 1;
	        for (int i = 1; i < m; i ++) {
	        	for (int j = 1; j < n; j ++) {
	        		route[i][j] = route[i - 1][j] + route[i][ j - 1];
	        	}
	        }
	        return route[m - 1][n - 1];
	    }
	}
}
