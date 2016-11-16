package leetcode;

/*
	There are a number of spherical balloons spread in two-dimensional space. 
	For each balloon, provided input is the start and 
	end coordinates of the horizontal diameter. 
	Since it's horizontal, y-coordinates don't matter and 
	hence the x-coordinates of start and end of the diameter suffice. 
	Start is always smaller than end. There will be at most 104 balloons.

	An arrow can be shot up exactly vertically from different points along the x-axis. 
	A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. 
	There is no limit to the number of arrows that can be shot. 
	An arrow once shot keeps travelling up infinitely. 
	The problem is to find the minimum number of arrows 
	that must be shot to burst all balloons.
	
	Example:
	Input:
	[[10,16], [2,8], [1,6], [7,12]]
	
	Output:
	2
	
	Explanation:
	One way is to shoot one arrow for example at x = 6 
	(bursting the balloons [2,8] and [1,6]) and
	 another arrow at x = 11 (bursting the other two balloons).
	
 */

public class P452_MinimumNumberofArrowstoBurstBalloons {
	public static void main(String[] args) {
		debugSolutionSort();
	}
	static void debugSolutionSort() {
		int[][] points = new int[][] {
			{1, 2},
			{7, 10},
			{2, 20},
			{3, 6},
			{8, 9},
			{0, 9},
			{0, 9},
		};
		Solution s = new Solution();
		s.sort(points, 0, points.length - 1);
		for (int[] arr : points) {
			System.out.println(arr[0] + "   " + arr[1]);
		}
	}
	static class Solution {
	    public int findMinArrowShots(int[][] points) {
	    	sort(points, 0, points.length - 1);
	    	int[] count = new int[points.length];
	    	for (int i = 0; i < points.length; i ++) {
	    		
	    	}
	        return 0;
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
