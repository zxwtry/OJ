package nowcoder.zuo;

/**
 * 	[题目]
 * 	给定一个无序整型数组arr，找到数组中未出现的最小正整数。
 * 
 * 	[举例]
 * 	arr=[-1,2,3,4]，返回1
 * 	arr=[1,2,3,4]，返回5
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book111_数组中未出现的最小正整数.java
 * @type        Book111_数组中未出现的最小正整数
 * @date        2017年1月3日 下午9:47:45
 * @details     
 */
public class Book111_数组中未出现的最小正整数 {
	static class Solution {
		public int missNum(int[] arr) {
			int left = 0, right = arr.length;
			while (left < right) {
				if (arr[left] == left + 1) {
					left ++;
				} else if (arr[left] <= left || arr[left] > right || 
						arr[arr[left] - 1] == arr[left]) {
					arr[left] = arr[-- right];
				} else {
					swap(arr, left, arr[left] - 1);
				}
			}
			return left + 1;
		}
		private void swap(int[] arr, int i, int j) {
			int t = arr[i];
			arr[i] = arr[j];
			arr[j] = t;
		}
	}
}
