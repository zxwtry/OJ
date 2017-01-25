package leetcode;

/**
 * 	Given an integer array nums, return the number of range sums that lie in 
 * 	[lower, upper] inclusive.
 *	Range sum S(i, j) is defined as the sum of the elememnts in nums between
 *	indices i and j (i ≤ j), inclusive.
 *	
 *	Note:
 *	A naive algorithm of O(n2) is trivial. You MUST do better than that.
 *	
 *	Example:
 *	Given nums = [-2, 5, -1], lower = -2, upper = 2,
 *	Return 3.
 *	The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums 
 *	are: -2, -1, 2.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P327_CountOfRangeSum.java
 * @type        P327_CountOfRangeSum
 * @date        2017年1月10日 下午9:59:31
 * @details     Solution1: AC 时间O(N^2) 空间O(1)
 */
public class P327_CountOfRangeSum {
	static class Solution1 {
	    public int countRangeSum(int[] nums, int lower, int upper) {
	        if (null == nums || nums.length == 0)
	        	return 0;
	        int len = nums.length;
	        int ans = 0;
	        long sum = 0;
	        for (int i = 0; i < len; i ++) {
	        	sum = 0;
	        	for (int j = i; j < len; j ++) {
	        		sum += nums[j];
	        		if (sum >= lower && sum <= upper)
	        			ans ++;
	        	}
	        }
	        return ans;
	    }
	}
}
