package leetcode;

/**
 * 	Given a positive integer n, break it into the sum of at least two positive integers
 *  and maximize the product of those integers. Return the maximum product you can get.
 *	
 *	For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 *	
 *	Note: You may assume that n is not less than 2 and not larger than 58.
 *	
 *	Hint:
 *	
 *	There is a simple O(n) solution to this problem.
 *	You may check the breaking results of n ranging from 7 to 10 to discover the regularities.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P343_IntegerBreak.java
 * @type        P343_IntegerBreak
 * @date        2017年2月2日 下午9:17:23
 * @details     Solution1: AC 2ms 16.91%
 * @details     Solution1: AC 0ms 49.43%
 */
public class P343_IntegerBreak {
	static class Solution1 {
	    public int integerBreak(int n) {
	        int[] dp = new int[n + 1];
	        dp[1] = 1;
	        for (int i = 2; i <= n; i ++) {
	            dp[i] = i == n ? 0 : i;
	            for (int j = 1; j < i; j ++) {
	                dp[i] = Math.max(dp[j] * dp[i - j], dp[i]);
	            }
	        }
	        return dp[n];
	    }
	}
}
