package leetcode;

import java.util.Arrays;

/**
 *	There are a number of spherical balloons spread in two-dimensional space. 
 *	For each balloon, provided input is the start and 
 *	end coordinates of the horizontal diameter. 
 *	Since it's horizontal, y-coordinates don't matter and 
 *	hence the x-coordinates of start and end of the diameter suffice. 
 *	Start is always smaller than end. There will be at most 104 balloons.

 *	An arrow can be shot up exactly vertically from different points along the x-axis. 
 *	A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. 
 *	There is no limit to the number of arrows that can be shot. 
 *	An arrow once shot keeps travelling up infinitely. 
 *	The problem is to find the minimum number of arrows 
 *	that must be shot to burst all balloons.
 *	
 *	Example:
 *	Input:
 *	[[10,16], [2,8], [1,6], [7,12]]
 *	
 *	Output:
 *	2
 *	
 *	Explanation:
 *	One way is to shoot one arrow for example at x = 6 
 *	(bursting the balloons [2,8] and [1,6]) and
 *	 another arrow at x = 11 (bursting the other two balloons).
 */

public class P452_MinimumNumberofArrowstoBurstBalloons {
	static void debugSolutionSort() {
//		int[][] points = new int[][] {
//			{10, 16},
//			{2,8},
//			{1,6},
//			{7,12},
//		};
//		int[][] points = tools.Utils.LEETCODE_int_二位数组_反序列化_
//				("[[0,9],[1,8],[7,8],[1,6],[9,16],[7,13],[7,10],[6,11],[6,9],[9,13]]");
//		int[][] points = tools.Utils.LEETCODE_int_二位数组_反序列化_("[[10,16],[2,8],[1,6],[7,12]]");
		int[][] points = tools.Utils.LEETCODE_int_二位数组_反序列化_("[[9,17],[4,12],[4,8],[4,8],[7,13],[3,4],[7,12],[9,15]]");
		Solution s = new Solution();
		System.out.println("answer is : " + s.findMinArrowShots(points));
	}
	/*
	 * 	必须搜索回溯
	 * 	这样的做法是错误的。
	 */
	static class Solution {
	    public int findMinArrowShots(int[][] points) {
	    	if (points == null || points.length < 1) {
	    		return 0;
	    	}
	    	sort(points, 0, points.length - 1);
	    	int[] count = new int[points.length];
	    	boolean[] isShoted = new boolean[points.length];
	    	int shotCount = 0;
	    	int maxIndex = 0;
	    	int ans = 0;
	    	do {
	    		maxIndex = -1;
	    		Arrays.fill(count, 0);
		    	for (int i = 0; i < points.length; i ++) {
		    		for (int j = i; j < points.length && points[i][1] >= points[j][0]; j ++) {
		    			if (! isShoted[j] && ! isShoted[i]) {
		    				if (maxIndex == -1)		maxIndex = j;
		    				count[j] ++;
		    				maxIndex = count[maxIndex] < count[j] ? j : maxIndex;
 		    			}
		    		}
		    	}
		    	for (int i = 0; i < points.length; i ++) {
		    		if (points[i][0] <= points[maxIndex][0] && points[i][1] >= points[maxIndex][0]) {
		    			if (! isShoted[i]) {
			    			shotCount ++;
			    			isShoted[i] = true;
		    			}
		    		}
		    	}
		    	ans ++;
	    	} while (shotCount < points.length);
	        return ans;
	    }
		void sort(int[][] points, int sti, int eni) {
			if (sti < eni) {
				int p = part(points, sti, eni);
				sort(points, sti, p - 1);
				sort(points, p + 1, eni);
			}
		}
		private int part(int[][] points, int sti, int eni) {
			int[] pivot = points[sti];
			while (sti < eni) {
				while (sti < eni && points[eni][0] >= pivot[0])		eni --;
				points[sti] = points[eni];
				while (sti < eni && points[sti][0] <= pivot[0]) 	sti ++;
				points[eni] = points[sti];
			}
			points[sti] = pivot;
			return sti;
		}
	}
	/*
	 * 	回溯的版本
	 * 	写卡了。
	 */
	static class BackTrackSolution {
		int min = Integer.MAX_VALUE;
		int[] c = null;
		int[] t = null;
	    public int findMinArrowShots(int[][] points) {
	    	if (points == null || points.length < 1) {
	    		return 0;
	    	}
	    	c = new int[points.length];
	    	t = new int[points.length];
	    	sort(points, 0, points.length - 1);
	    	int[] count = new int[points.length];
	    	boolean[] isShoted = new boolean[points.length];
	    	int shotCount = 0;
	    	int maxIndex = 0;
	    	int ans = 0;
	    	do {
	    		maxIndex = -1;
	    		Arrays.fill(count, 0);
		    	for (int i = 0; i < points.length; i ++) {
		    		for (int j = i; j < points.length && points[i][1] >= points[j][0]; j ++) {
		    			if (! isShoted[j] && ! isShoted[i]) {
		    				if (maxIndex == -1)		maxIndex = j;
		    				count[j] ++;
		    				maxIndex = count[maxIndex] < count[j] ? j : maxIndex;
 		    			}
		    		}
		    	}
		    	for (int i = 0; i < points.length; i ++) {
		    		if (points[i][0] <= points[maxIndex][0] && points[i][1] >= points[maxIndex][0]) {
		    			if (! isShoted[i]) {
			    			shotCount ++;
			    			isShoted[i] = true;
		    			}
		    		}
		    	}
		    	ans ++;
	    	} while (shotCount < points.length);
	        return ans;
	    }
	    void backTrack(int[][] points, int delIndex, boolean[] isShoted, int shotCount, int num) {
	    	for (int i = 0; i < points.length; i ++) {
	    		if (! isShoted[i] && points[i][0] <= points[delIndex][0] && points[i][1] >= points[delIndex][0]) {
	    			shotCount ++;
	    			isShoted[i] = true;
	    		}
	    	}
	    	num ++;
	    	if (shotCount < points.length) {
	    		//c是打掉的气球个数
	    		//t是一个气球可以被打掉的次数
	    		Arrays.fill(c, 0);
	    		Arrays.fill(t, 0);
	    		for (int i = 0; i < points.length; i ++) {
		    		for (int j = i; j < points.length && points[i][1] >= points[j][0]; j ++) {
		    			if (! isShoted[j] && ! isShoted[i]) {
		    				t[i] ++;
		    				c[j] ++;
 		    			}
		    		}
		    	}
	    		int numOfTrack = 0;
	    		for (int i = 0; i < points.length; i ++) {
	    			if (c[i] > 1) {
	    				numOfTrack ++;
	    			}
	    			if (t[i] == 1) {
	    				//就直接打掉
	    				isShoted[i] = true;
	    				num ++;
	    			}
	    		}
	    		int[] track = new int[numOfTrack];
	    		numOfTrack = 0;
	    		for (int i = 0; i < points.length; i ++) {
	    			if (c[i] > 1) {
	    				track[numOfTrack ++] = i;
	    			}
	    		}
	    		backTrack(points, delIndex, isShoted, shotCount, num);
	    	} else {
	    		min = Math.min(num, min);
	    	}
	    }
		void sort(int[][] points, int sti, int eni) {
			if (sti < eni) {
				int p = part(points, sti, eni);
				sort(points, sti, p - 1);
				sort(points, p + 1, eni);
			}
		}
		private int part(int[][] points, int sti, int eni) {
			int[] pivot = points[sti];
			while (sti < eni) {
				while (sti < eni && points[eni][0] >= pivot[0])		eni --;
				points[sti] = points[eni];
				while (sti < eni && points[sti][0] <= pivot[0]) 	sti ++;
				points[eni] = points[sti];
			}
			points[sti] = pivot;
			return sti;
		}
	}
}