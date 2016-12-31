package nowcoder.zuo;

import java.util.HashMap;

/**
 * 	给定一个无序数组a，其中元素可正、可负、可0。
 * 	给定一个整数 k，求a所有的子数组中累加和为k的最长子数组长度。
 * 
 * 	[补充问题]
 * 	给定一个无序数组a，其中元素可正、可负、可0。
 * 	求a所有的子数组中正数与负数个数相等的最长子数组长度。
 * 
 * 	[补充问题]
 * 	给定一个无序数组a，其中元素只是0或1。
 * 	求a所有的子数组中0和1个数相等的最长子数组长度。
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book098_未排序数组中累加和为给定值的最长子数组系列问题.java
 * @type        Book098_未排序数组中累加和为给定值的最长子数组系列问题
 * @date        2016年12月31日 下午8:35:33
 * @details     Solution1: 时间O(N^2)，空间O(1)
 */
public class Book098_未排序数组中累加和为给定值的最长子数组系列问题 {
	static class Solution1 {
		public int getMaxLen(int[] a, int k) {
			if (a == null || a.length < 1) return 0;
			int maxLen = 0;
			int v = 0;
			for (int i = 0; i < a.length; i ++) {
				for (int j = i; j < a.length; j ++) {
					v = i == j ? a[j] : v + a[j];
					if (v == k)
						maxLen = Math.max(maxLen, j - i + 1);
				}
			}
			return maxLen;
		}
	}
}
