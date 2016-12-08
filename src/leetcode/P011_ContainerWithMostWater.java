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
//		System.out.println(new Solution2().maxArea(new int[]{4,4,2,11,0,11,5,11,13,8}));	//55
//		System.out.println(new Solution2().maxArea(new int[]{2,3,10,5,7,8,9}));				//36
//		System.out.println(new Solution2().maxArea(new int[]{1, 3}));						//1
//		System.out.println(new Solution2().maxArea(new int[]{3, 1, 3}));					//6
//		System.out.println(new Solution2().maxArea(new int[]{3, 1, 1, 3}));					//9
//		System.out.println(new Solution3().maxArea(new int[]{3, 1, 4, 1, 3}));				//12
//		System.out.println(new Solution3().maxArea(new int[]{3, 1, 4, 9, 1, 3}));			//15
//		System.out.println(new Solution3().maxArea(new int[]{3, 1, 4, 0, 1, 3}));			//15
//		System.out.println(new Solution5().maxArea(new int[]{1, 2}));						//1
		System.out.println(new Solution5().maxArea(new int[]{3, 3}));						//3
		System.out.println(new Solution5().maxArea(new int[]{8, 9, 8, 7, 7, 7, 7, 7}));		//49
		System.out.println(new Solution5().maxArea(new int[]{8, 7, 7, 7, 7, 7, 8, 9, 8}));	//64
//		System.out.println(new Solution2().binarySearchUp(new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17}, 0, 4, -100));
//		System.out.println(new Solution2().binarySearchUp(new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5},  0, 8, 5));
//		System.out.println(new Solution2().binarySearchUp(new int[]{0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5},  0, 8, 5));
//		System.out.println(new Solution2().binarySearchDown(new int[]{9, 7, 5, 3, 1}, 0, 4, 10));
//		System.out.println(new Solution2().binarySearchDown(new int[]{17, 15, 13, 11, 9, 7, 5, 3, 1}, 0, 8, 4100));
//		System.out.println(new Solution2().binarySearchDown(new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5},  0, 8, 5));
//		System.out.println(new Solution2().binarySearchDown(new int[]{5, 5, 5, 5, 5, 5, 0, 0, 0},  0, 8, 5));
//		System.out.println(new Solution2().binarySearchDown(new int[]{5}, 0, 0, 5));
		
	}
	/*
	 * 	这里想复杂了，原题非常简单
	 * 	这里解决的是积水题，非常简单
	 * 	这里得到的也是O(N)
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
	/*
	 * 	时间：O(N*lgN)
	 *	空间：O(N)
	 *	时间O(N)的方法想不出来
	 */
	static class Solution2 {
		public int maxArea(int[] height) {
			int len = 0;
	    	if (height == null || (len = height.length) < 2)	return 0;
	    	int max = height[0], maxi = 0, i = 1;
	        for (i = 1; i != len; i ++) {
	        	if (height[i] > max) {
	        		maxi = i;
	        		max = height[i];
	        	}
	        }
	        int sti = maxi, eni = maxi + 1, temp = 0, step = 0;
	        int[] vai = new int[len + 1];
	        vai[maxi] = maxi;   vai[maxi + 1] = maxi;   vai[0] = 0;   temp = 0;
	        for (i = 1; i < maxi; i ++)
	        	if (height[i] > height[vai[temp]])
	        		vai[++ temp] = i;
	        step =  maxi - temp - 1;
	        sti  = step < 0 ? 0 : step;
	        if (step > 0)
	        	while (temp > -1) {
	        		vai[temp + step] = vai[temp];
	        		temp --;
	        	}
	        vai[len] = len - 1;   temp = len;
	        for (i = len - 2; i > maxi; i --) {
	        	if (height[i] > height[vai[temp]])
	        		vai[-- temp] = i;
	        }
	        step = temp - maxi - 2;
	        eni = step < 0 ? len : len - step;
	        if (step > 0)
	        	while (temp < len + 1)
	        		vai[temp - step] = vai[temp ++];
	        int maxArea = 0;
	        for (i = sti; i <= maxi; i ++) {
	        	temp = binarySearchUp(vai, maxi + 1, len, vai[i]);
	        	temp = Math.min(height[vai[i]], height[vai[temp]]) * (vai[temp] - vai[i]);
	        	maxArea = maxArea < temp ? temp : maxArea;
	        }
	        for (i = eni; i > maxi; i --) {
	        	temp = binarySearchUp(vai, 0, maxi, vai[i]);
	        	temp = Math.min(height[vai[i]], height[vai[temp]]) * (vai[i] - vai[temp]);
	        	maxArea = maxArea < temp ? temp : maxArea;
	        }
	        return maxArea;
		}
		/*
		 * 	>= v的第一个index
		 */
		private int binarySearchUp(int[] vai, int sti, int eni, int v) {
			int mid = 0;
	        while (sti < eni) {
	        	mid = (sti + eni) >>> 1;
	        	if (vai[mid] >= v)
	        		eni = mid;
	        	else
	        		sti = mid + 1;
	        }
	        return sti;
		}
		int binarySearchDown(int[] vai, int sti, int eni, int v) {
			int mid = 0;
			while (sti < eni) {
				mid = (((sti + eni) & 0x1)  == 0) ? (sti + eni) >>> 1 : ((sti + eni) >>> 1) + 1;
				if (vai[mid] >= v)
					sti = mid;
				else
					eni = mid - 1;
			}
			return sti;
		}
	}
	
	/**
	 * @author      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     leetcode
	 * @file        P011_ContainerWithMostWater.java
	 * @type        Solution3
	 * @date        2016年12月8日 下午3:13:32
	 * @details     TLE
	 */
	static class Solution3 {
		public int maxArea(int[] h) {
			if (h == null || h.length < 2) return 0;
			int max = 0;
			for (int i = 0; i < h.length; i ++) {
				for (int j = 0; j < i; j ++) {
					if (h[i] <= h[j]) {
						max = Math.max(max, h[i] * (i - j));
						break;
					} else {
						max = Math.max(max, h[j] * (i - j));
					}
				}
			}
			return max;
		}
	}
	
	static class Solution4 {
		public int maxArea(int[] h) {
			if (h == null || h.length < 2) return 0;
			int max = 0;
			for (int i = 0; i < h.length; i ++) {
				for (int j = 0; j < i; j ++) {
					if (h[i] <= h[j]) {
						max = Math.max(max, h[i] * (i - j));
						break;
					}
				}
				for (int j = h.length - 1; j > i; j --) {
					if (h[i] <= h[j]) {
						max = Math.max(max, h[i] * (j - i));
						break;
					}
				}
			}
			return max;
		}
	}
	
	
	/**
	 * @author      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     leetcode
	 * @file        P011_ContainerWithMostWater.java
	 * @type        Solution5
	 * @date        2016年12月8日 下午3:29:46
	 * @details     86.76% 8ms AC
	 */
	static class Solution5 {
		public int maxArea(int[] h) {
			if (h == null || h.length < 2) return 0;
			int l = 0, r = h.length - 1, ans = 0, k = 0;
			while (l < r) {
				ans = Math.max(Math.min(h[l], h[r]) * (r - l), ans);
				if (h[l] < h[r]) {
					k = l;
					while (k < r && h[k] <= h[l]) k ++;
					l = k;
				} else {
					k = r;
					while (k > l && h[k] <= h[r]) k --;
					r = k;
				}
			}
			return ans;
		}
	}
	
	
}

