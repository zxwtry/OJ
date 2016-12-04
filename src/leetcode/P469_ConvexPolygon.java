package leetcode;

import java.util.List;


/**
 * 	Given a list of points that form a polygon when joined sequentially, 
 * 	find if this polygon is convex (Convex polygon definition).

	Note:
	
	There are at least 3 and at most 10,000 points.
	Coordinates are in the range -10,000 to 10,000.
	You may assume the polygon formed by given points is always a simple polygon 
	(Simple polygon definition). In other words, we ensure that exactly
	 two edges intersect at each vertex, and that edges otherwise don't intersect each other.
	Example 1:
	
	[[0,0],[0,1],[1,1],[1,0]]
	
	Answer: True
	
	Explanation:
	Example 2:
	
	[[0,0],[0,10],[10,10],[10,0],[5,5]]
	
	Answer: False
	
	Explanation:
 */


/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P469_ConvexPolygon.java
 * @type        P469_ConvexPolygon
 * @date        2016年12月4日 上午11:31:24
 * @details     
 */
public class P469_ConvexPolygon {
	public static void main(String[] args) {
	}
	
	static class Solution {
	    public boolean isConvex(List<List<Integer>> p) {
	    	int[][] arr = new int [p.size()][2];
	    	int min0 = Integer.MAX_VALUE, min1 = Integer.MAX_VALUE;
	    	for (int i = 0; i < p.size(); i ++) {
	    		arr[i][0] = p.get(i).get(0);
	    		arr[i][1] = p.get(i).get(1);
	    		min0 = Math.min(min0, arr[i][0]);
	    		min1 = Math.min(min1, arr[i][1]);
	    	}
	    	for (int i = 0; i < arr.length; i ++) {
	    		arr[i][0] -= min0;
	    		arr[i][1] -= min1;
	    	}
	    	
	        for (int i = 0; i < p.size(); i ++) {
	        	for (int j = 0; j < i; j ++) {
	        		int sign = 0;
	        		for (int k = 0; k < p.size(); k ++) {
	        			if (k == i || k == j) continue;
	        			int c = cal(p.get(i).get(0), p.get(i).get(1), p.get(j).get(0),
	        					p.get(j).get(1), p.get(k).get(0), p.get(k).get(1));
	        			if (sign == 0) {
	        				if (c > 0) sign = 1;
	        				if (c < 0) sign = -1;
	        			} else {
	        				if (sign > 0 && c < 0) {
	        					return false;
	        				}
	        				if (sign < 0 && c > 0) {
	        					return false;
	        				}
	        			}
	        		}
	        	}
	        }
	        return true;
	    }
	    
	    public int cal(int x1, int y1, int x2, int y2, int x, int y) {
	    	return (x2 - x1) * (y - y1) - (y2 - y1) * (x - x1);
	    }
	}
	
}
