package leetcode;

/*
 * 	Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 */

public class P073_SetMatrixZeroes {
	public static void main(String[] args) {
		int[][] m = new int[][]{
			{1, 2, 0, 4},
			{1, 2, 3, 4},
			{1, 0, 3, 0},
			{1, 2, 3, 4},
		};
		new Solution().setZeroes(m);
		tools.Utils.A_打印二维数组(m);
	}
	/*
	 * 	一次AC
	 * 	3 ms
	 */
	static class Solution {
	    public void setZeroes(int[][] matrix) {
	        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
	        	return;
	        int i_save = 0, j_save = 0;
	        boolean isDone = false;
	        for (i_save = 0; i_save != matrix.length; i_save ++) {
	        	for (j_save = 0; j_save != matrix[0].length; j_save ++)
	        		if (matrix[i_save][j_save] == 0) {
	        			isDone = true;
	        			break;
	        		}
	        	if (isDone)	break;
	        }
	        if (! isDone)
	        	return;
	        for (int i = i_save; i != matrix.length; i ++)
	        	for (int j = 0; j != matrix[0].length; j ++)
	        		if (matrix[i][j] == 0) {
	        			matrix[i_save][j] = 0;
	        			matrix[i][j_save] = 0;
	        		}
	        for (int i = matrix.length - 1; i > -1; i --) {
	        	if (i == i_save)	continue;
	        	for (int j = matrix[0].length - 1; j > - 1; j --) {
	        		if (j == j_save)	continue;
	        		if (matrix[i_save][j] == 0 || matrix[i][j_save] == 0)
	        			matrix[i][j] = 0;
	        	}
	        }
	        for (int i = 0; i != matrix.length; i ++)
	        	matrix[i][j_save] = 0;
	        for (int j = 0; j != matrix[0].length; j ++)
	        	matrix[i_save][j] = 0;
	    }
	}
}
