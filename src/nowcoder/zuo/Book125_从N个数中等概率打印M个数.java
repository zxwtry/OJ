package nowcoder.zuo;

/**
 * 	[题目]
 * 	给定一个长度为N且没有重复的数组arr和一个整数n，实现函数等概率随机打印arr中的M个数
 * 	
 * 	[要求]
 * 	1,	相同的数不要重复打印
 * 	2,	时间O(M)，空间O(1)
 * 	3,	可以改变arr数组
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book125_从N个数中等概率打印M个数.java
 * @type        Book125_从N个数中等概率打印M个数
 * @date        2017年1月8日 下午10:08:19
 * @details     
 */
public class Book125_从N个数中等概率打印M个数 {
	static class Solution {
		public void printRandM(int[] arr, int m) {
			if (arr == null || arr.length == 0 || m < 0)
				return;
			m = Math.min(arr.length, m);
			int count = 0;
			int i = 0;
			while (count < m) {
				i = (int) (Math.random() * (arr.length - count));
				System.out.println(arr[i]);
				swap(arr, arr.length - count++ - 1, i);
			}
		}
		private void swap(int[] arr, int i, int j) {
			int t = arr[i];
			arr[i] = arr[j];
			arr[j] = t;
		}
	}
}
