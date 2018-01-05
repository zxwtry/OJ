package nowcoder.zuo;

/**
 * 	[题目]
 * 	给定一个长度不小于2的数组arr，
 * 	实现一个函数调整arr，
 * 	要么让所有的偶数下标都是偶数，
 * 	要么让所有的奇数下标都是奇数
 * 	
 * 	[要求]
 * 	如果arr的长度为N，函数要求时间O(N)，空间O(1)
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book102_奇数下标都是奇数或者偶数下标都是偶数.java
 * @type        Book102_奇数下标都是奇数或者偶数下标都是偶数
 * @date        2017年1月1日 下午7:50:42
 * @details     Solution: 时间O(N)，空间O(1)
 */
public class Book102_奇数下标都是奇数或者偶数下标都是偶数 {
	static class Solution {
		public void modify(int[] arr) {
			if (arr == null || arr.length < 2) return;
			int even = 0, odd = 1, end = arr.length - 1;
			while (even <= end && odd <= end) {
				if ((arr[end] & 1) == 0) {
					swap(arr, end, even);
					even += 2;
				} else {
					swap(arr, end, odd);
					odd += 2;
				}
			}
		}
		private void swap(int[] a, int i, int j) {
			int t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
	}
}
