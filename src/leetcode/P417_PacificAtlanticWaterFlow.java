package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 	Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, 
 * 	the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right
 * 	 and bottom edges.
 *	
 *	Water can only flow in four directions (up, down, left, or right) from a cell to another one 
 *	with height equal or lower.
 *	
 *	Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 *	
 *	Note:
 *	The order of returned grid coordinates does not matter.
 *	Both m and n are less than 150.
 *	Example:
 *	
 *	Given the following 5x5 matrix:
 *	
 *	  Pacific ~   ~   ~   ~   ~ 
 *	       ~  1   2   2   3  (5) *
 *	       ~  3   2   3  (4) (4) *
 *	       ~  2   4  (5)  3   1  *
 *	       ~ (6) (7)  1   4   5  *
 *	       ~ (5)  1   1   2   4  *
 *	          *   *   *   *   * Atlantic
 *	
 *	Return:
 *	
 *	[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */

public class P417_PacificAtlanticWaterFlow {
	/*
	 * 	Input:
		[[1,2,3],[8,9,4],[7,6,5]]
		Output:
		[[0,2],[1,0],[1,1],[1,2],[2,0],[2,2]]
		Expected:
		[[0,2],[1,0],[1,1],[1,2],[2,0],[2,1],[2,2]]
	 */
	
	public static void main(String[] args) {
		int[][] m = new int[][] {
			{1, 2, 2, 3, 5},
			{3, 2, 3, 4, 4},
			{2, 4, 5, 3, 1},
			{6, 7, 1, 4, 5},
			{5, 1, 1, 2, 4},
//			{1,2,3},
//			{8,9,4},
//			{7,6,5},
//			{1,2},
//			{3,4},
//			{3,4},
//			{3,4},
//			{3,4},
//			{3,4},
		};
		Solution s = new Solution();
		List<int[]> ans = s.pacificAtlantic(m);
		for (int[] pAns : ans) {
			tools.Utils.printArray(pAns, 3);
		}
	}
	/*
	 * 	AC
	 * 	20 ms
	 */
	static class Solution {
		List<int[]> ans = new LinkedList<>();
		int row, col;
	    public List<int[]> pacificAtlantic(int[][] m) {
	    	if (m == null || m.length == 0 || m[0].length == 0) {
	    		return ans;
	    	}
	    	row = m.length;
	    	col = m[0].length;
	    	if (row == 1 || col == 1) {
	    		for (int i = 0; i < row; i ++) {
	    			for (int j = 0; j < col; j ++) {
	    				int[] addToAns = new int[2];
	    				addToAns[0] = i;
	    				addToAns[1] = j;
	    				ans.add(addToAns);
	    			}
	    		}
	    		return ans;
	    	}
	    	boolean[][] pacific = new boolean[row][col];
	    	boolean[][] atlantic = new boolean[row][col];
	    	Arrays.fill(pacific[0], true);
	    	for (int i = 1; i < row; i ++) {
	    		pacific[i][0] = true;
	    	}
	    	//这样的搜索方式是错误的，应该更换成向四周搜索
//	    	for (int i = 1; i < row; i ++) {
//	    		for (int j = 1; j < col; j ++) {
//	    			if (pacific[i - 1][j] && m[i - 1][j] <= m[i][j]) {
//	    				pacific[i][j] = true;
//	    			}
//	    			if (pacific[i][j - 1] && m[i][j - 1] <= m[i][j]) {
//	    				pacific[i][j] = true;
//	    			}
//	    		}
//	    	}
	    	//从已经设置为true的边缘进行搜索
	    	for (int j = 1; j < col; j ++) {
	    		if (m[1][j] >= m[0][j] && ! pacific[1][j]) {
	    			search(pacific, 1, j, m);
	    		}
	    	}
	    	for (int i = 1; i < row; i ++) {
	    		if (m[i][1] >= m[i][0] && ! pacific[i][1]) {
	    			search(pacific, i, 1, m);
	    		}
	    	}
	    	Arrays.fill(atlantic[row - 1], true);
	    	for (int i = 0; i < row - 1; i ++) {
	    		atlantic[i][col - 1] = true;
	    	}
	    	//这样的搜索方式是错误的
//	    	for (int i = row - 2; i > -1; i --) {
//	    		for (int j = col - 2; j > -1; j --) {
//	    			if (atlantic[i + 1][j] && m[i + 1][j] <= m[i][j]) {
//	    				atlantic[i][j] = true;
//	    			}
//	    			if (atlantic[i][j + 1] && m[i][j + 1] <= m[i][j]) {
//	    				atlantic[i][j] = true;
//	    			}
//	    		}
//	    	}
	    	for (int i = 0; i < row - 1; i ++) {
	    		if (m[i][col - 2] >= m[i][col - 1] && ! atlantic[i][col - 2]) {
	    			search(atlantic, i, col - 2, m);
	    		}
	    	}
	    	for (int j = 0; j < col - 1; j ++) {
	    		if (m[row - 2][j] >= m[row - 1][j] && ! atlantic[row - 2][j]) {
	    			search(atlantic, row - 2, j, m);
	    		}
	    	}
	    	for (int i = 0; i < row; i ++) {
	    		for (int j = 0; j < col; j ++) {
	    			if (atlantic[i][j] && pacific[i][j]) {
	    				int[] addToAns = new int[2];
	    				addToAns[0] = i;
	    				addToAns[1] = j;
	    				ans.add(addToAns);
	    			}
	    		}
	    	}
	    	tools.Utils.A_打印二维boolean数组(pacific);
	    	System.out.println("=======");
	    	tools.Utils.A_打印二维boolean数组(atlantic);
	        return ans;
	    }
		private void search(boolean[][] pacific, int i, int j, int[][] m) {
			pacific[i][j] = true;
			if (i + 1 < row && ! pacific[i + 1][j] && m[i + 1][j] >= m[i][j]) {
				search(pacific, i + 1, j, m);
			}
			if (i - 1 > -1 && ! pacific[i - 1][j] && m[i - 1][j] >= m[i][j]) {
				search(pacific, i - 1, j, m);
			}
			if (j + 1 < col && ! pacific[i][j + 1] && m[i][j + 1] >= m[i][j]) {
				search(pacific, i, j + 1, m);
			}
			if (j - 1 > -1 && ! pacific[i][j - 1] && m[i][j - 1] >= m[i][j]) {
				search(pacific, i, j - 1, m);
			}
		}
	}
}
