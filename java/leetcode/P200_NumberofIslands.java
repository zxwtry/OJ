package leetcode;

/*
 * 	Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
 * 	An island is surrounded by water and is formed by connecting adjacent 
 * 	lands horizontally or vertically. You may assume all four edges of 
 * 	the grid are all surrounded by water.

	Example 1:
	
	11110
	11010
	11000
	00000
	Answer: 1
	
	Example 2:
	
	11000
	11000
	00100
	00011
	Answer: 3
 */

public class P200_NumberofIslands {
	public static void main(String[] args) {
		char[][] grid = new char[][] {
//			"11110".toCharArray(),
//			"11010".toCharArray(),
//			"11000".toCharArray(),
//			"00000".toCharArray(),
			
			"11000".toCharArray(),
			"11000".toCharArray(),
			"00100".toCharArray(),
			"00011".toCharArray(),
		};
		Solution s = new Solution();
		System.out.println(s.numIslands(grid));
	}
	/*
	 * 	4 ms
	 * 	39.89%
	 */
	static class Solution {
		int count = 0;
		boolean[][] isVisited = null;
		int row = 0, col = 0;
	    public int numIslands(char[][] grid) {
	    	if (grid == null || grid.length == 0 || grid[0].length == 0) {
	    		return 0;
	    	}
	    	row = grid.length;
	    	col = grid[0].length;
	    	isVisited = new boolean[row][col];
	    	for (int i = 0; i < row; i ++) {
	    		for (int j = 0; j < col; j ++) {
	    			if (! isVisited[i][j] && grid[i][j] == '1') {
	    				search(grid, i, j);
	    				count ++;
	    			}
	    		}
	    	}
	        return count;
	    }
		private void search(char[][] grid, int i, int j) {
			if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == '0' || isVisited[i][j]) {
				return;
			}
			isVisited[i][j] = true;
			search(grid, i - 1, j);
			search(grid, i, j - 1);
			search(grid, i + 1, j);
			search(grid, i, j + 1);
		}
	}
}
