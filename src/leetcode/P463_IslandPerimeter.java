package leetcode;

import java.util.ArrayList;

/*
 * 	You are given a map in form of a two-dimensional integer grid 
 * 	where 1 represents land and 0 represents water. Grid cells are 
 * 	connected horizontally/vertically (not diagonally). 
 * 	The grid is completely surrounded by water, and 
 * 	there is exactly one island (i.e., one or more connected land cells). 
 * 	The island doesn't have "lakes" (water inside that isn't connected to 
 * 	the water around the island). One cell is a square with side length 1. 
 * 	The grid is rectangular, width and height don't exceed 100. 
 * 	Determine the perimeter of the island.

    Example:
    
    [[0,1,0,0],
     [1,1,1,0],
     [0,1,0,0],
     [1,1,0,0]]
    
    Answer: 16
    Explanation: The perimeter is the 16 yellow stripes in the image below:
 */

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P463_IslandPerimeter.java
 * @type        P463_IslandPerimeter
 * @date        2016年11月20日 下午3:13:12
 * @details     
 */
public class P463_IslandPerimeter {
	public static void main(String[] args) {
		
	}
	
	/**
	 * @method      debugSolution
	 * @parameter
	 * @return      void
	 * @details
	 */
	static void debugSolution() {
		Solution s = new Solution();
		int[][] arr = tools.FileUtils.C_读取int二维数组_LEETCODE("D:/file/data/P463_IslandPerimeter.txt", 2);
		System.out.println(s.islandPerimeter(arr));
	}

	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     leetcode
	 * @file        P463_IslandPerimeter.java
	 * @type        Solution
	 * @date        2016年11月20日 下午3:15:39
	 * @details     会一直WA，对于2这种数据是没法高效解决的。
	 */
	static class Solution {
		
	    /**
	     * @method      islandPerimeter
	     * @parameter   int[][] grid		---	输入
	     * @return      int
	     * @details     Solution的入口
	     */
	    public int islandPerimeter(int[][] grid) {
	    	ArrayList<Integer> list = new ArrayList<Integer>();
	    	int minJ = Integer.MAX_VALUE, maxJ = Integer.MIN_VALUE;
	    	int ans = 0;
	    	for (int i = 0; i < grid.length; i ++) {
	    		int listI = 0;
	    		for (int j = 0; j < grid[i].length; j ++) {
	    			if (grid[i][j] == 1) {
	    				listI += 1;
	    				minJ = Math.min(minJ, j);
	    				maxJ = Math.max(maxJ, j);
	    			}
	    		}
	    		if (listI != 0) {
	    			list.add(listI);
	    		}
	    	}
	    	tools.Utils.B_打印List_Integer(list);
	    	ans += (maxJ - minJ + 1 + list.size()) * 2;
			for (int index = 1; index < list.size() - 1; index ++) {
				int sti = index - 1, eni = index + 1;
				int val = list.get(index);
				while (sti > -1) {
					if (list.get(sti) > val) {
						break;
					} else {
						sti --;
					}
				}
				while (eni < list.size()) {
					if (list.get(eni) > val) {
						break;
					} else {
						eni ++;
					}
				}
				if (sti != -1 && eni != list.size()) {
					ans += Math.min(list.get(sti), list.get(eni));
				}
			}
	    	return ans;
	    }
	}
}
