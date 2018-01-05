package nowcoder.zuo;

/**
 * 	[题目]
 * 	给定一个double类型的数组arr，
 * 	其中的元素可正、可负、可0，
 * 	返回子数组累乘的最大乘积。
 * 
 * 	[举例]
 * 	arr=[-2.5, 4, 0, 3, 0.5, 8, -1]
 * 	子数组[3, 0.5, 8]可以得到最大乘积12，返回12
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book106_数组中子数组的最大累乘积.java
 * @type        Book106_数组中子数组的最大累乘积
 * @date        2017年1月2日 上午10:34:32
 * @details     Solution1: 时间O(N^3)，空间 O(1)
 */
public class Book106_数组中子数组的最大累乘积 {
	static class Solution1 {
		public double getMaxSubMul(double[] arr) {
			if (arr == null ||  arr.length == 0) return 0.0;
			double maxSubMul = Double.MIN_VALUE;
			for (int i = 0; i < arr.length; i ++) {
				for (int j = i; j < arr.length; j ++) {
					double mul = 1.0;
					for (int k = i; k <= j; k ++)
						mul *= arr[k];
					maxSubMul = Math.max(maxSubMul, mul);
				}
			}
			return maxSubMul;
		}
	}
	static class Solution2 {
		public double getMaxSubMul(double[] arr) {
			if (arr == null ||  arr.length == 0) return 0.0;
			double maxSubMul = Double.MIN_VALUE;
			double p = 1;
			double n = 1;
			for (double v : arr) {
				p *= v;
				n *= v;
				p = Math.max(Math.max(p, n), v);
				n = Math.min(Math.min(p, n), v);
				maxSubMul = Math.max(maxSubMul, p);
			}
 			return maxSubMul;
		}
	}
}
