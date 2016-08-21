package leetcode;

/*
 * 	Given n non-negative integers a1, a2, ..., an,
 *  where each represents a point at coordinate (i, ai).
 *   n vertical lines are drawn such that the two endpoints
 *    of line i is at (i, ai) and (i, 0). 
 *    Find two lines, which together with x-axis forms a container,
 *     such that the container contains the most water.
	Note: You may not slant the container.
 */

public class P011_ContainerWithMostWater {
	public static void main(String[] args) {
//		System.out.println(new Solution1().maxArea(new int[]{3, 1, 3}));
//		System.out.println(new Solution1().maxArea(new int[]{3, 1, 1, 3}));
//		System.out.println(new Solution1().maxArea(new int[]{3, 1, 4, 1, 3}));
//		System.out.println(new Solution1().maxArea(new int[]{3, 1, 4, 9, 1, 3}));
		System.out.println(new Solution1().maxArea(new int[]{3, 1, 4, 0, 1, 3}));
	}
	/*
	 * 	这里想复杂了，原题非常简单
	 * 	这里解决的是积水题，非常简单
	 */
	static class Solution1 {
	    public int maxArea(int[] height) {
	    	if (height == null || height.length < 3)	return 0;
	        int max = height[0], maxi = 0, i = 1;
	        for (i = 1; i != height.length; i ++) {
	        	if (height[i] > max) {
	        		maxi = i;
	        		max = height[i];
	        	}
	        }
	        int[] partMaxIndex = new int[height.length];
	        partMaxIndex[0] = 0;
	        partMaxIndex[height.length - 1] = height.length - 1;
	        partMaxIndex[maxi] = maxi;
	        for (i = 1; i < maxi; i ++)
	        	partMaxIndex[i] = height[i] > height[partMaxIndex[i - 1]] ? i : partMaxIndex[i - 1];
	        for (i = height.length - 2; i > maxi; i --)
	        	partMaxIndex[i] = height[i] > height[partMaxIndex[i + 1]] ? i : partMaxIndex[i + 1];
	        int area = 0, maxAreaLeft = 0, maxAreaRight = 0, lastIndex = 0;
	        lastIndex = maxi - 1;
	        maxAreaLeft = area = lastIndex > -1 ? height[partMaxIndex[lastIndex]] - height[lastIndex] : 0;
	        for (i = maxi - 2; i > -1; i --) {
	        	if (partMaxIndex[i] == i) {
	        		maxAreaLeft = Math.max(area, maxAreaLeft);
	        		if (i != 0)
	        			area -= (lastIndex - i + 1) * (height[partMaxIndex[i]] - height[partMaxIndex[i] - 1]);
	        		lastIndex = i;
	        	} else {
	        		area += height[partMaxIndex[i]] - height[i];
	        	}
	        }
	        maxAreaLeft = Math.max(area, maxAreaLeft);
	        lastIndex = maxi + 1;
	        maxAreaRight = area = lastIndex < height.length ? height[partMaxIndex[lastIndex]] - height[lastIndex] : 0;
	        for (i = maxi + 2; i < height.length; i ++) {
	        	if (partMaxIndex[i] == i) {
	        		maxAreaRight = Math.max(area, maxAreaRight);
	        		if (i != height.length - 1)
	        			area -= (i - lastIndex + 1) * (height[partMaxIndex[i]] - height[partMaxIndex[i] - 1]);
	        		lastIndex = i;
	        	} else {
	        		area += height[partMaxIndex[i]] - height[i];
	        	}
	        }
	        maxAreaRight = Math.max(area, maxAreaRight);
	       	return maxAreaLeft + maxAreaRight;
	    }
	}
}

