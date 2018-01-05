package leetcode;

/*
 * 	Given a 2D binary matrix filled with 0's and 1's, 
 * 	find the largest square containing only 1's and return its area.

	For example, given the following matrix:
	
	1 0 1 0 0
	1 0 1 1 1
	1 1 1 1 1
	1 0 0 1 0
	Return 4.	
 */

public class P221_MaximalSquare {
	public static void main(String[] args) {
		char[][] matrix = new char[][] {
			"0001".toCharArray(),
			"1101".toCharArray(),
			"1111".toCharArray(),
			"0111".toCharArray(),
			"0111".toCharArray(),
		};
		Solution s = new Solution();
		System.out.println(s.maximalSquare(matrix));
	}
	/*	
	 * 	10 ms
	 * 	78.20%
	 */
	static class Solution {
		final char TARGET = '1';
	    public int maximalSquare(char[][] matrix) {
	    	if (null == matrix) {
	    		return 0;
	    	}
	    	int row = matrix.length;
	    	if (row == 0) {
	    		return 0;
	    	}
	    	int col = matrix[0].length;
	    	if (col == 0) {
	    		return 0;
	    	}
	    	int[][] lengthOfSquare = new int[row][col];
	    	int maxSquare = 0;
	    	for (int i = 0; i < row; i ++) {
	    		lengthOfSquare[i][col - 1] = matrix[i][col - 1] == TARGET ? 1 : 0;
	    		maxSquare = Math.max(maxSquare, lengthOfSquare[i][col - 1] * lengthOfSquare[i][col - 1]);
	    	}
	    	for (int j = 0; j < col; j ++) {
	    		lengthOfSquare[row - 1][j] = matrix[row - 1][j] == TARGET ? 1 : 0;
	    		maxSquare = Math.max(maxSquare, lengthOfSquare[row - 1][j] * lengthOfSquare[row - 1][j]);
	    	}
	        for (int i = row - 2; i > -1; i --) {
	        	for (int j = col - 2; j > -1; j --) {
	        		if (matrix[i][j] != TARGET) {
	        			lengthOfSquare[i][j] = 0;
	        		} else {
	        			int newLength = Math.min(lengthOfSquare[i + 1][j], lengthOfSquare[i][j + 1]);
	        			newLength = Math.min(newLength, lengthOfSquare[i + 1][j + 1]) + 1;
	        			lengthOfSquare[i][j] = newLength;
	        			maxSquare = Math.max(maxSquare, newLength * newLength);
	        		}
	        	}
	        }
//	        tools.Utils.A_打印二维数组(lengthOfSquare);
	        return maxSquare;
	    }
	}
}
