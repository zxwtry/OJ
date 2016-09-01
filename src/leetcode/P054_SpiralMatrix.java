package leetcode;

/*
 * 	Given a matrix of m x n elements (m rows, n columns), 
 * 	return all elements of the matrix in spiral order.

	For example,
	Given the following matrix:
	
	[
	 [ 1, 2, 3 ],
	 [ 4, 5, 6 ],
	 [ 7, 8, 9 ]
	]
	You should return [1,2,3,6,9,8,7,4,5].
 */


import java.util.LinkedList;
import java.util.List;

public class P054_SpiralMatrix {
	public static void main(String[] args) {
		System.out.println(new Solution().spiralOrder(new int[][]{
			{1},
			{1},
			{1},
			{1}
		}));
	}
	/*
	 * 	最重要的是代码简洁，否则就是没用
	 * 	1 ms
	 * 	2.73%
	 */
	static class Solution {
		List<Integer> ans = new LinkedList<Integer>();
	    public List<Integer> spiralOrder(int[][] matrix) {
	    	int row = 0, col = 0, eni = 0;
	    	if (matrix == null || (row = matrix.length) == 0 
	    			|| (col = matrix[0].length) == 0)
	    		return ans;
	    	eni = Math.min(row, col) >> 1;
	    	for (int i = 0; i != eni; i ++)
	    		fourEdge(i, row - 1 - i, col - 1 - i, matrix);
	    	if ((Math.min(row, col) & 0x1) == 1) {
	    		int rowi = Math.min(row, col) >> 1;
	    		int staj = rowi;
	    		int endj = Math.max(row, col) - rowi - 1;
	    		if (row < col)
	    			for (int j = staj; j <= endj; j ++)
	    				ans.add(matrix[rowi][j]);
	    		else
	    			for (int i = staj; i <= endj; i ++)
	    				ans.add(matrix[i][rowi]);
	    	}
	    	return ans;
	    }
		private void fourEdge(int i, int j, int k, int[][] matrix) {
			int t = 0;
			for (t = i; t < k; t ++)
				ans.add(matrix[i][t]);
			for (t = i; t < j; t ++)
				ans.add(matrix[t][k]);
			for (t = k; t > i; t --)
				ans.add(matrix[j][t]);
			for (t = j; t > i; t --)
				ans.add(matrix[t][i]);
		}
	}
}
