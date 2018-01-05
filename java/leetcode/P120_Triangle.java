package leetcode;

/*
 * 	Given a triangle, find the minimum path sum from top to bottom. 
 * 	Each step you may move to adjacent numbers on the row below.
	
	For example, given the following triangle
	[
	     [2],
	    [3,4],
	   [6,5,7],
	  [4,1,8,3]
	]
	The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
	
	Note:
	Bonus point if you are able to do this using only O(n) extra space,
	 where n is the total number of rows in the triangle.
 */

import java.util.List;

public class P120_Triangle {
	public static void main(String[] args) {
		List<List<Integer>> list = tools.Utils.C_生成List_List_Integer__从二维数组(new int[][] {
			{1},
			{-1, 1},
//			{0, 2, 3},
//			{0, 4, 5, 6},
//			{1, 0, 3, 6, 9}
		});
		Solution2 s = new Solution2();
		System.out.println(s.minimumTotal(list));
	}
	/*
	 * 	TLE
	 */
	static class Solution {
		int min = Integer.MAX_VALUE;
		int I = 0;
	    public int minimumTotal(List<List<Integer>> triangle) {
	    	I = triangle.size() - 1;
	    	if (I < 0) {
	    		return 0;
	    	} else if (I == 0) {
	    		return triangle.get(0).get(0);
	    	}
	        return search(triangle, 0, 0);
	    }
		private int search(List<List<Integer>> triangle, int i, int j) {
			if (j < 0 || j > i) {
				return Integer.MAX_VALUE;
			}
			if (i == I) {
				return triangle.get(i).get(j);
			}
			return Math.min(search(triangle, i + 1, j), 
					search(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
		}
	}
	/*
	 * 	艰难AC
	 * 	3 ms
	 */
	static class Solution2 {
	    public int minimumTotal(List<List<Integer>> triangle) {
	    	int len = 0;
	    	if (triangle == null || (len = triangle.size()) <= 0) {
	    		return 0;
	    	} else if (len == 1) {
	    		return triangle.get(0).get(0);
	    	}
	    	int[] arr = new int[len + 1];
	    	for (int list_count = len - 1; list_count > -1; list_count --) {
		    	List<Integer> list_now = triangle.get(list_count);
		    	arr[0] = list_now.get(0) + Math.min(arr[0], arr[1]);
		    	for (int i = 1; i <= list_count; i ++) {
		    		arr[i] = list_now.get(i) + Math.min(arr[i], arr[i + 1]);
		    	}
	    	}
	    	return arr[0];
	    }
	}
}