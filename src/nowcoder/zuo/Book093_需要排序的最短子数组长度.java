package nowcoder.zuo;

/**
 * 	给定一个无序数组arr，求出需要排序的最短子数组长度
 * 	举例：
 * 		arr=[1,5,3,4,2,6,7]，返回4，因为只有[5,3,4,2]需要排序
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book093_需要排序的最短子数组长度.java
 * @type        Book093_需要排序的最短子数组长度
 * @date        2016年12月26日 下午9:24:22
 * @details     
 */
public class Book093_需要排序的最短子数组长度 {
	static class Solution {
		public int getMinLength(int[] arr) {
			if (arr == null || arr.length < 2) return 0;
			int min = arr[arr.length - 1];
			int noMinIndex = -1;
			for (int i = arr.length - 2; i != -1; i --)
				if (arr[i] > min) noMinIndex = i;
				else min = Math.min(min, arr[i]);
			if (noMinIndex == -1) return 0;
			int max = arr[0];
			int noMaxIndex = -1;
			for (int i = 1; i != arr.length; i ++)
				if (arr[i] < max) noMaxIndex = i;
				else max = Math.max(max, arr[i]);
			return noMaxIndex - noMinIndex + 1;
		}
	}
}
