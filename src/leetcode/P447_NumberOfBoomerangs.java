package leetcode;

import leetcode.P179_LargestNumber.SolutionOwnSort;

/*
 * 	Given n points in the plane that are all pairwise distinct,
 * 	a "boomerang" is a tuple of points (i, j, k) such that
 * 	the distance between i and j equals the distance 
 * 	between i and k (the order of the tuple matters).

	Find the number of boomerangs. You may assume that 
	n will be at most 500 and coordinates of points
	 are all in the range [-10000, 10000] (inclusive).
	
	Example:
	Input:
	[[0,0],[1,0],[2,0]]
	
	Output:
	2
	
	Explanation:
	The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 */

public class P447_NumberOfBoomerangs {
	public static void main(String[] args) {
		debugSolution();
	}
	private static void debugSolution() {
//		int[][] points = tools.Utils.LEETCODE_int_二位数组_反序列化_("[[1,1],[2,2],[3,3],[4,4],[5,5],[6,6],[7,7],[8,8]]");
//		int[][] points = tools.Utils.LEETCODE_int_二位数组_反序列化_("[[1,1],[2,2],[3,3],[4,4]]");
		int[][] points = tools.Utils.LEETCODE_int_二位数组_反序列化_("[[0,0],[1,0],[2,0]]");
		Solution s = new Solution();
		System.out.println(s.numberOfBoomerangs(points));
	}
	/*
	 * 	TLE
	 */
	static class Solution {
	    public int numberOfBoomerangs(int[][] points) {
	    	int dist1 = 0, dist2 = 0;
	    	int num = 0;
	    	for (int i = 0; i < points.length; i ++) {
	    		for (int j = 0; j < points.length; j ++) {
	    			if (i == j) {
	    				continue;
	    			}
	    			dist1 = calc(points[i], points[j]);
	    			for (int k = 0; k < points.length; k ++) {
	    				if (i == k || j == k) {
	    					continue;
	    				}
	    				dist2 = calc(points[i], points[k]);
	    				if (dist1 == dist2) {
	    					num += 1;
	    				}
	    			}
	    		}
	    	}
	        return num;
	    }

		private int calc(int[] a1, int[] a2) {
			return (a1[0] - a2[0]) * (a1[0] - a2[0]) + (a1[1] - a2[1]) * (a1[1] - a2[1]);
		}
	}
}
