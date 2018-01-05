package leetcode;

/**
 * 	Given n non-negative integers a1, a2, ..., an,
 *  where each represents a point at coordinate (i, ai).
 *   n vertical lines are drawn such that the two endpoints
 *    of line i is at (i, ai) and (i, 0). 
 *    Find two lines, which together with x-axis forms a container,
 *     such that the container contains the most water.
	Note: You may not slant the container.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P011_ContainerWithMostWater.java
 * @date        2016年12月8日 下午3:29:46
 * @details     Solution 86.76% 8ms AC
 */
public class P011_ContainerWithMostWater {
	static class Solution {
		public int maxArea(int[] h) {
			if (h == null || h.length < 2) return 0;
			int l = 0, r = h.length - 1, ans = 0, k = 0;
			while (l < r) {
				ans = Math.max(Math.min(h[l], h[r]) * (r - l), ans);
				if (h[l] < h[r]) {
					k = l;
					while (k < r && h[k] <= h[l]) k ++;    //等于号保证至少执行一次
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
