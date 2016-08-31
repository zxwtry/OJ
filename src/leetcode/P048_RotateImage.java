package leetcode;

/*
 * 	You are given an n x n 2D matrix representing an image.
	Rotate the image by 90 degrees (clockwise).
	Follow up:
	Could you do this in-place?
 */

public class P048_RotateImage {
	public static void main(String[] args) {
		int[][] matrix = null;
		matrix = new int[][] {
			{1, 2},
			{3, 4}
		};
		matrix = new int[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		};
		matrix = new int[][] {
			{1, 2, 3, 4},
			{1, 2, 3, 4},
			{1, 2, 3, 4},
			{1, 2, 3, 4},
		};
		matrix = new int[][] {
			 {2,29,20,26,16,28},
			 {12,27,9,25,13,21},
			 {32,33,32,2,28,14},
			 {13,14,32,27,22,26},
			 {33,1,20,7,21,7},
			 {4,24,1,6,32,34}
		};
		matrix = new int[][] {
			{1, 2, 3, 4, 5},
			{1, 2, 3, 4, 5},
			{1, 2, 3, 4, 5},
			{1, 2, 3, 4, 5},
			{1, 2, 3, 4, 5},
		};
//		matrix = new int[][] {
//			{1, 2, 3, 4, 5, 6},
//			{1, 2, 3, 4, 5, 6},
//			{1, 2, 3, 4, 5, 6},
//			{1, 2, 3, 4, 5, 6},
//			{1, 2, 3, 4, 5, 6},
//			{1, 2, 3, 4, 5, 6},
//		};
		new Solution1().rotate(matrix);
		tools.Utils.A_打印二维数组(matrix);
	}
	/*
	 * 	0 ms
	 * 	26.00%
	 */
	static class Solution1 {
	    public void rotate(int[][] matrix) {
	        if (matrix == null || matrix.length < 2)
	        	return;
	        int i = 0, j = matrix.length - 1;
	        while (i < j) {
	        	for (int k = 0; k < j - i; k ++) {
	        		int temp = matrix[i][i + k];
	        		matrix[i][i + k] = matrix[j - k][i];
	        		matrix[j - k][i] = matrix[j][j - k];
	        		matrix[j][j - k] = matrix[i + k][j];
	        		matrix[i + k][j] = temp;
	        	}
	        	i ++;
	        	j --;
	        }
	    }
	}
}