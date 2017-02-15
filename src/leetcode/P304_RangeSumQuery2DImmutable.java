package leetcode;

/**
 * 	Given a 2D matrix matrix, find the sum of the elements inside the rectangle 
 * 	defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *	
 *	The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) 
 *	and (row2, col2) = (4, 3), which contains sum = 8.
 *	
 *	Example:
 *	Given matrix = [
 *	  [3, 0, 1, 4, 2],
 *	  [5, 6, 3, 2, 1],
 *	  [1, 2, 0, 1, 5],
 *	  [4, 1, 0, 1, 7],
 *	  [1, 0, 3, 0, 5]
 *	]
 *	
 *	sumRegion(2, 1, 4, 3) -> 8
 *	sumRegion(1, 1, 2, 2) -> 11
 *	sumRegion(1, 2, 2, 4) -> 12
 *	Note:
 *	You may assume that the matrix does not change.
 *	There are many calls to sumRegion function.
 *	You may assume that row1 ≤ row2 and col1 ≤ col2.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P304_RangeSumQuery2DImmutable.java
 * @type        P304_RangeSumQuery2DImmutable
 * @date        2016年12月28日 下午10:27:47
 * @details     NumMatrix1: AC 127ms 47.54%
 */
public class P304_RangeSumQuery2DImmutable {
	static class NumMatrix1 {
		long[][] sum = null;
	    public NumMatrix1(int[][] matrix) {
	    	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
	        sum = new long[matrix.length][matrix[0].length];
	        sum[0][0] = matrix[0][0];
	        for (int i = 1; i < matrix.length; i ++)
	        	sum[i][0] = matrix[i][0] + sum[i - 1][0];
	        for (int j = 1; j < matrix[0].length; j ++)
	        	sum[0][j] = matrix[0][j] + sum[0][j - 1];
	        for (int i = 1; i < matrix.length; i ++) {
	        	for (int j = 1; j < matrix[0].length; j ++) {
	        		sum[i][j] = sum[i - 1][ j] + sum[i][ j - 1] - sum[i - 1][ j - 1] + matrix[i][j];
	        	}
	        }
	        for (int i = 0; i < sum.length; i ++) {
	        	for (int j = 0; j < sum[i].length; j ++) {
	        		System.out.print(sum[i][j] + "\t");
	        	}
	        	System.out.println();
	        }
	    }
	    public long accessSum(int i, int j) {
	    	if (i < 0 || j < 0) return 0;
	    	return sum[i][j];
	    }
	    public int sumRegion(int row1, int col1, int row2, int col2) {
	        return (int) (sum[row2][col2] + accessSum(row1 - 1,col1 - 1) - accessSum(row2,col1 - 1) - accessSum(row1 - 1,col2));
	    }
	}
}
