package nowcoder.zuo;

/**
 * 	给定一个数组arr，返回子数组的最大累加和。
 * 	例如，arr=[1,-2,3,5,-2,6,-1]，
 * 	[3,5,-2,6]可以累加出最大和12，返回12
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book103_子数组的最大累加和问题.java
 * @type        Book103_子数组的最大累加和问题
 * @date        2017年1月1日 下午8:08:18
 * @details     
 */
public class Book103_子数组的最大累加和问题 {
	static class Solution {
		public int getMaxSubSum(int[] arr) {
			if (arr == null || arr.length < 1) return 0;
			int maxSubSum = Integer.MIN_VALUE;
			int sum = 0;
			for (int v : arr) {
				if (sum < 0) sum = 0;
				sum += v;
				maxSubSum = Math.max(maxSubSum, sum);
			}
			return maxSubSum;
		}
	}
}
