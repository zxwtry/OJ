package leetcode;

/**
 * 	Given an array of n integers where n > 1, nums, return an array output such that
 *  output[i] is equal to the product of all the elements of nums except nums[i].
 *	
 *	Solve it without division and in O(n).
 *	
 *	For example, given [1,2,3,4], return [24,12,8,6].
 *	
 *	Follow up:
 *	Could you solve it with constant space complexity? (Note: The output array
 *	 does not count as extra space for the purpose of space complexity analysis.)
 *	product不是最大公约数，是乘积乘积乘积！！！！！
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P238_ProductofArrayExceptSelf.java
 * @type        P238_ProductofArrayExceptSelf
 * @date        2016年12月10日 下午10:25:35
 * @details     Solution1: AC 3ms 10.68%
 */
public class P238_ProductofArrayExceptSelf {
	static class Solution1 {
	    public int[] productExceptSelf(int[] nums) {
	    	if (nums == null || nums.length < 1) return nums;
	    	int[] left = new int[nums.length];
	    	int[] right = new int[nums.length];
	    	int product = 1;
	    	for (int i = 0; i < nums.length; i ++) {
	    		left[i] = product;
	    		product *= nums[i];
	    	}
	    	product = 1;
	    	for (int i = nums.length - 1; i > -1; i --) {
	    		right[i] = product;
	    		product *= nums[i];
	    	}
	    	int[] ans = new int[nums.length];
	    	for (int i = 0; i < nums.length; i ++) {
	    		ans[i] = left[i] * right[i];
	    	}
	    	return ans;
	    }
	}
	
}
