package nowcoder.zuo;

/**
 * 	[题目]
 * 	给定一个长度为N(N>1)的整型数组arr，可以划分成左右两个部分，
 * 	左部分为arr[0...k]，右部分为arr[k+1...N-1]，k的范围[0,N-2]
 * 	求这么多划分方案中，左部分中的最大值减去右部分最大值的绝对值中，最大是多少。
 * 	
 * 	[举例]
 * 	arr=[2,7,1,1]，返回6
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book118_最大的leftMax与rightMax之差的绝对值.java
 * @type        Book118_最大的leftMax与rightMax之差的绝对值
 * @date        2017年1月5日 下午9:38:19
 * @details     
 */
public class Book118_最大的leftMax与rightMax之差的绝对值 {
	static class Solution1 {
		public int getMaxAbs(int[] arr) {
			if (arr == null || arr.length < 2) return 0;
			int maxAbs = Integer.MIN_VALUE;
			int maxLeft = 0, maxRight = 0;
			for (int i = 0; i < arr.length - 1; i ++) {
				maxLeft = Integer.MIN_VALUE;
				for (int j = 0; j <= i; j ++)
					maxLeft = Math.max(maxLeft, arr[j]);
				maxRight = Integer.MIN_VALUE;
				for (int j = i + 1; j < arr.length; j ++)
					maxRight = Math.max(maxRight, arr[j]);
				maxAbs = Math.max(maxAbs, Math.abs(maxLeft - maxRight));
			}
			return maxAbs;
		}
	}
}
