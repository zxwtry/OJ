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
 * @details     
 */
public class P329_LongestIncreasingPathInAMatrix {
	static class Solution1 {
	    public int longestIncreasingPath(int[][] matrix) {
	        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
	            return 0;
	        int row = matrix.length - 1;
	        int col = matrix[0].length - 1;
	        int[][] dp = new int[row + 1][col + 1];
	        
	        
	        
	    }
	}
}
