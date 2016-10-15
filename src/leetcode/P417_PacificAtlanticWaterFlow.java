package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.sun.media.sound.AlawCodec;
import com.sun.xml.internal.ws.handler.HandlerProcessor.RequestOrResponse;

/*
 * 	Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, 
 * 	the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right
 * 	 and bottom edges.

	Water can only flow in four directions (up, down, left, or right) from a cell to another one 
	with height equal or lower.
	
	Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
	
	Note:
	The order of returned grid coordinates does not matter.
	Both m and n are less than 150.
	Example:
	
	Given the following 5x5 matrix:
	
	  Pacific ~   ~   ~   ~   ~ 
	       ~  1   2   2   3  (5) *
	       ~  3   2   3  (4) (4) *
	       ~  2   4  (5)  3   1  *
	       ~ (6) (7)  1   4   5  *
	       ~ (5)  1   1   2   4  *
	          *   *   *   *   * Atlantic
	
	Return:
	
	[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */

public class P417_PacificAtlanticWaterFlow {
	
	public static void main(String[] args) {
		int[][] m = new int[][] {
			{1, 2, 2, 3, 5},
			{3, 2, 3, 4, 4},
			{2, 4, 5, 3, 1},
			{6, 7, 1, 4, 5},
			{5, 1, 1, 2, 4},
		};
	}
	static class Solution {
		List<int[]> ans = new LinkedList<>();
	    public List<int[]> pacificAtlantic(int[][] m) {
	    	if (m == null || m.length == 0 || m[0].length == 0) {
	    		return ans;
	    	}
	    	int row = m.length;
	    	int col = m[0].length;
	    	boolean[][] pacific = new boolean[row][col];
	    	boolean[][] atlantic = new boolean[row][col];
	    	Arrays.fill(pacific[0], true);
	    	for (int i = 1; i < row; i ++) {
	    		pacific[i][0] = true;
	    	}
	    	for (int i = 1; i < row; i ++) {
	    		for (int j = 1; j < col; j ++) {
	    			if (pacific[i - 1][j] && m[i - 1][j] <= m[i][j]) {
	    				pacific[i][j] = true;
	    			}
	    			if (pacific[i][j - 1] && m[i][j - 1] <= m[i][j]) {
	    				pacific[i][j] = true;
	    			}
	    		}
	    	}
	    	Arrays.fill(atlantic[row - 1], true);
	    	for (int i = 0; i < row - 1; i ++) {
	    		atlantic[i][col - 1] = true;
	    	}
	    	for (int i = row - 2; i > -1; i --) {
	    		for (int j = col - 2; j > -1; j --) {
	    			if (atlantic[i + 1][j] && m[i + 1][j] <= m[i][j]) {
	    				atlantic[i][j] = true;
	    			}
	    			if (atlantic[i][j + 1] && m[i][j + 1] <= m[i][j]) {
	    				atlantic[i][j] = true;
	    			}
	    			if (pacific[i][j]) {
	    				int[] addToAns = new int[2];
	    				addToAns[0] = i;
	    				addToAns[1] = j;
	    				ans.add(addToAns);
	    			}
	    		}
	    	}
	        return ans;
	    }
	}
}
