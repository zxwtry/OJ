package nowcoder.zuo;

/**
 * 	[题目]
 * 	给定一个长度为N的整型数组arr，
 * 	其中有N个互不相同的自然数1~N，
 * 	实现一个arr的排序，
 * 	但是不要把下标0~N-1位置上的数通过直接赋值的方式替换成1~N
 * 
 * 	[要求]
 * 	时间O(N)，空间O(1)
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book101_自然数数组的排序.java
 * @type        Book101_自然数数组的排序
 * @date        2017年1月1日 下午7:36:51
 * @details     Solution1: 时间O(N)，空间O(1)
 */
public class Book101_自然数数组的排序 {
	static class Solution1 {
		public void sort(int[] arr) {
			int tmp = 0;
			int next = 0;
			for (int i = 0; i < arr.length; i ++) {
				tmp = arr[i];
				while (arr[i] != i + 1) {
					next = arr[tmp - 1];
					arr[tmp - 1] = tmp;
					tmp = next;
				}
			}
		}
	}
	static class Solution2 {
		public void sort(int[] arr) {
			int tmp = 0;
			for (int i = 0; i < arr.length; i ++) {
				while (arr[i] != i + 1) {
					tmp = arr[arr[i] - 1];
					arr[arr[i] - 1] = arr[i];
					arr[i] = tmp;
				}
			}
		}
	}
}
