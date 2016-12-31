package nowcoder.zuo;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 	先给出可整合数组的定义。如果一个数组在排序之后，每相邻两个数之差的绝对值
 * 	都为1，则该数组为可整合数组。
 * 	例如：	[5,3,4,6,2]，排序之后为[2,3,4,5,6]，符合。
 * 	给定一个整型数组，请返回其中最大可整合子数组的长度。
 * 	例如：	[5,5,3,2,6,4,3]，的最大可整合子数组为[5,3,2,6,4]，所以返回5
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book096_最长的可整合子数组的长度.java
 * @type        Book096_最长的可整合子数组的长度
 * @date        2016年12月26日 下午9:59:02
 * @details     
 */
public class Book096_最长的可整合子数组的长度 {
	static class Solution1 {
		public int getLIT1(int[] arr) {
			if (arr == null || arr.length == 0) return 0;
			int len = 0;
			for (int i = 0; i < arr.length; i ++) {
				for (int j = i; j < arr.length; j ++) {
					if (isIntegrated(arr, i, j))
						len = Math.max(len, j-i+1);
				}
			}
			return len;
		}
		private boolean isIntegrated(int[] arr, int left, int right) {
			int[] newArr = Arrays.copyOfRange(arr, left, right + 1);
			Arrays.sort(newArr);
			for (int i = 0; i < newArr.length; i ++)
				if (newArr[i - 1] != newArr[i] - 1)
					return false;
			return true;
		}
	}
	static class Soltuion2 {
		public int getLIL2(int[] arr) {
			if (arr == null || arr.length == 0) return 0;
			int len = 0;
			int max = 0;
			int min = 0;
			HashSet<Integer> set = new HashSet<Integer>();
			for (int i = 0; i < arr.length; i ++) {
				max = Integer.MIN_VALUE;
				min = Integer.MAX_VALUE;
				for (int j = i; i < arr.length; j ++) {
					if (set.contains(arr[j])) {
						break;
					}
					set.add(arr[j]);
					max = Math.max(max, arr[j]);
					min = Math.min(min, arr[j]);
					if (max - min == j - i) {
						len = Math.max(len, j - i + 1);
					}
				}
				set.clear();
			}
			return len;
		}
	}
}
