package nowcoder.zuo;

/**
 * 	[题目]
 * 	定义局部最小的概念。
 * 	arr长度为1时，arr[0]是局部最小。
 * 	arr长度为N(N>1)时，	arr[0]<arr[1]，则arr[0]是局部最小。
 * 						arr[N-1]<arr[N-2]，则arr[N-1]是局部最小。
 * 						0<i<N-1，如果arr[i]<arr[i+1] && arr[i]<arr[i-1]，则arr[i]是局部最小。
 * 	给定无序数组arr，已知arr中任意两个相邻的数都不相邻，
 * 	返回arr中任意一个局部最小的位置。
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book105_在数组中找到一个局部最小的位置.java
 * @type        Book105_在数组中找到一个局部最小的位置
 * @date        2017年1月2日 上午9:32:38
 * @details     Solution1: 时间O(N)，空间O(1)
 * @details     Solution2: 时间O(logN)，空间O(1)
 */
public class Book105_在数组中找到一个局部最小的位置 {
	static class Solution1 {
		public int getLocMinIndex(int[] arr) {
			if (arr == null || arr.length < 2) 
				return arr == null ? -1 : arr.length - 1;
			int n = arr.length;
			if (arr[0] < arr[1]) return 0;
			if (arr[n-2] > arr[n-1]) return n-1;
			for (int i = 1; i < n-1; i ++) {
				if (arr[i-1] > arr[i] && arr[i+1] > arr[i])
					return i;
			}
			return -1;
		}
	}
	static class Solution2 {
		public int getLocMinIndex(int[] arr) {
			if (arr == null || arr.length < 2) 
				return arr == null ? -1 : arr.length - 1;
			int n = arr.length;
			if (arr[0] < arr[1]) return 0;
			if (arr[n-2] > arr[n-1]) return n-1;
			int l = 1, r = arr.length - 2, m = 0;
			while (l < r) {
				m = (l + r) / 2;
				if (arr[m] > arr[m - 1]) {
					r = m - 1;
				} else if (arr[m] > arr[m + 1]) {
					l = m + 1;
				} else {
					return m;
				}
			}
			return l;
		}
	}
}
