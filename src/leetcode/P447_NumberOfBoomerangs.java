package leetcode;

import java.util.HashMap;
import java.util.Map.Entry;

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
		debugMapSolution();
	}
	static void debugMapSolution() {
//		int[][] points = tools.Utils.LEETCODE_int_二位数组_反序列化_("[[1,1],[2,2],[3,3],[4,4],[5,5],[6,6],[7,7],[8,8]]");
//		int[][] points = tools.Utils.LEETCODE_int_二位数组_反序列化_("[[1,1],[2,2],[3,3],[4,4]]");
		int[][] points = tools.Utils.LEETCODE_int_二位数组_反序列化_("[[0,0],[1,0],[2,0]]");
		MapSolution s = new MapSolution();
		System.out.println(s.numberOfBoomerangs(points));
	}
	static void debugSingleSolution() {
		int[][] points = tools.Utils.LEETCODE_int_二位数组_反序列化_("[[1,1],[2,2],[3,3],[4,4],[5,5],[6,6],[7,7],[8,8]]");
//		int[][] points = tools.Utils.LEETCODE_int_二位数组_反序列化_("[[1,1],[2,2],[3,3],[4,4]]");
//		int[][] points = tools.Utils.LEETCODE_int_二位数组_反序列化_("[[0,0],[1,0],[2,0]]");
		SingleSolution s = new SingleSolution();
		System.out.println(s.numberOfBoomerangs(points));
	}
	static void debugSolution() {
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
	/*
	 * 	还是TLE
	 */
	static class SingleSolution {
	    public int numberOfBoomerangs(int[][] points) {
	    	int dist1 = 0, dist2 = 0;
	    	int num = 0;
	    	for (int i = 0; i < points.length; i ++) {
	    		for (int j = 0; j < points.length; j ++) {
	    			if (i == j) {
	    				continue;
	    			}
	    			dist1 = calc(points[i], points[j]);
	    			for (int k = j + 1; k < points.length; k ++) {
	    				if (i == k) {
	    					continue;
	    				}
	    				dist2 = calc(points[i], points[k]);
	    				if (dist1 == dist2) {
	    					num += 2;
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
	/*
	 * 	用Map保存
	 * 	AC
	 * 	210 ms
	 * 	72.43%
	 */
	static class MapSolution {
	    public int numberOfBoomerangs(int[][] points) {
	    	int[][] map = new int[points.length][points.length];
	    	int num = 0;
	    	for (int i = 0; i < points.length; i ++) {
	    		for (int j = i + 1; j < points.length; j ++) {
	    			map[i][j] = calc(points[i], points[j]);
	    		}
	    	}
	    	for (int i = 0; i < points.length; i ++) {
	    		for (int j = 0; j < i; j ++) {
	    			map[i][j] = map[j][i];
	    		}
	    	}
	    	HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
	    	for (int i = 0; i < points.length; i ++) {
	    		m.clear();
	    		for (int j = 0; j < points.length; j ++) {
	    			if (i != j) {
		    			if (m.containsKey(map[i][j])) {
		    				m.put(map[i][j], m.get(map[i][j]) + 1);
		    			} else {
		    				m.put(map[i][j], 1);
		    			}
	    			}
	    		}
	    		for (Entry<Integer, Integer> e : m.entrySet()) {
	    			int val = e.getValue();
	    			if (val > 1) {
	    				num += (val - 1) * val;
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
