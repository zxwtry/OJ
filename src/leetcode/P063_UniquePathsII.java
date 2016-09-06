package leetcode;


/*
 * 	Follow up for "Unique Paths":

	Now consider if some obstacles are added to the grids. 
	How many unique paths would there be?
	
	An obstacle and empty space is marked as 1 and 0 respectively in the grid.
	
	For example,
	There is one obstacle in the middle of a 3x3 grid as illustrated below.
	
	[
	  [0,0,0],
	  [0,1,0],
	  [0,0,0]
	]
	The total number of unique paths is 2.
	
	Note: m and n will be at most 100.
 */

public class P063_UniquePathsII {
	public static void main(String[] args) {
		System.out.println(new Solution1().uniquePathsWithObstacles(new int[][] {
			{1},
			{0}
		}));
	}
	/*
	 * 	多次WA
	 * 	还是要注意逻辑的缜密
	 * 	1 ms
	 * 	20.72% 
	 */
	static class Solution1 {
	    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
	    	if (obstacleGrid == null || obstacleGrid[0] == null)
	    		return 0;
	    	int m = 0, n = 0;
	    	if ((m = obstacleGrid.length) < 1 || (n = obstacleGrid[0].length) < 1)
	    		return 0;
	        int[] route = new int[m];
	        route[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
	        for (int j = 1; j < m; j ++)
	        	route[j] = obstacleGrid[j][0] == 0 ? route[j - 1] : 0;
	        for (int i = 1; i < n; i ++) {
	        	route[0] = obstacleGrid[0][i] == 0 ? route[0] : 0;
	        	for (int j = 1; j < m; j ++)
	        		route[j] = obstacleGrid[j][i] == 0 ? route[j] + route[j - 1] : 0;
	        }
	        return route[m - 1];
	    }
	}
}
