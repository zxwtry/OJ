package leetcode;

/**
 * 	Given n balloons, indexed from 0 to n-1. Each balloon is painted with 
 * 	a number on it represented by array nums. You are asked to burst all 
 * 	the balloons. If the you burst balloon i you will get nums[left] * 
 * 	nums[i] * nums[right] coins. Here left and right are adjacent indices of i. 
 * 	After the burst, the left and right then becomes adjacent.

 *	Find the maximum coins you can collect by bursting the balloons wisely.
 *	
 *	Note: 
 *	(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore 
 *	you can not burst them.
 *	(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *	
 *	Example:
 *	
 *	Given [3, 1, 5, 8]
 *	
 *	Return 167
 *	
 *	    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *	   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P312_BurstBalloons.java
 * @type        P312_BurstBalloons
 * @date        2016年12月29日 下午8:56:36
 * @details     Solution: AC 15ms 44.19%
 */
public class P312_BurstBalloons {
	static class Solution {
		public int maxCoins(int[] iNums) {
		    int[] nums = new int[iNums.length + 2];
		    int n = 1;
		    for (int x : iNums) if (x > 0) nums[n++] = x;
		    nums[0] = nums[n++] = 1;


		    int[][] memo = new int[n][n];
		    return burst(memo, nums, 0, n - 1);
		}

		public int burst(int[][] memo, int[] nums, int left, int right) {
		    if (left + 1 == right) return 0;
		    if (memo[left][right] > 0) return memo[left][right];
		    int ans = 0;
		    for (int i = left + 1; i < right; ++i)
		        ans = Math.max(ans, nums[left] * nums[i] * nums[right] 
		        + burst(memo, nums, left, i) + burst(memo, nums, i, right));
		    memo[left][right] = ans;
		    return ans;
		}
	}
}
