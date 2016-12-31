package nowcoder.zuo;

/**
 * 	[题目]
 * 	给定一个无序数组a，其中元素可正、可负、可0。
 * 	给定一个整数k，求a的所有子数组累加和小于或等于k的最长子数组长度。
 * 
 * 	[举例]
 * 	a=[3,-2,-4,0,6]，k=-2，相加和小于或等于-2的最长子数组
 * 	为[3,-2,4,0]，所以返回4
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book099_未排序数组中累加和小于或等于给定值的最长子数组长度.java
 * @type        Book099_未排序数组中累加和小于或等于给定值的最长子数组长度
 * @date        2016年12月31日 下午9:36:00
 * @details     Solution1: 时间O(N^2)，空间O(1)
 */
public class Book099_未排序数组中累加和小于或等于给定值的最长子数组长度 {
	static class Solution1 {
		public int getMaxLen(int[] a, int k) {
			if (a == null || a.length < 1) return 0;
			int maxLen = 0;
			int v = 0;
			for (int i = 0; i < a.length; i ++) {
				for (int j = i; j < a.length; j ++) {
					v = i == j ? a[i] : v + a[j];
					if (v <= k) {
						maxLen = Math.max(maxLen, j - i + 1);
					}
				}
			}
			return maxLen;
		}
	}
}
