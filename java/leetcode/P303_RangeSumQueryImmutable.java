package leetcode;


/**
 * 	Given an integer array nums, find the sum of the elements between 
 * 	indices i and j (i ≤ j), inclusive.
 *	
 *	Example:
 *	Given nums = [-2, 0, 3, -5, 2, -1]
 *	
 *	sumRange(0, 2) -> 1
 *	sumRange(2, 5) -> -1
 *	sumRange(0, 5) -> -3
 *	Note:
 *	You may assume that the array does not change.
 *	There are many calls to sumRange function.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P303_RangeSumQueryImmutable.java
 * @type        P303_RangeSumQueryImmutable
 * @date        2016年12月28日 下午10:25:59
 * @details     NumArray1: AC 241ms 22.91%
 * @details     NumArray2: AC 240ms 24.04%
 */
public class P303_RangeSumQueryImmutable {
	static class NumArray2 {
		int[] nums = null;
	    public NumArray2(int[] nums) {
	    	for (int index = 1; index < nums.length; index ++) {
	    		nums[index] += nums[index - 1];
	    	}
	    	this.nums = nums;
	    }
	    public int sumRange(int i, int j) {
	    	if (i == 0) {
	    		return nums[j];
	    	}
	    	return nums[j] - nums[i - 1];
	    }
	}
	static class NumArray1 {
		long[] sums = null;
	    public NumArray1(int[] nums) {
	    	if (nums == null || nums.length == 0) return;
	        sums = new long[nums.length + 1];
	        long sum = 0;
	        for (int index = 0; index < nums.length; index ++) {
	        	sum += nums[index];
	        	sums[index + 1] = sum;
	        }
	    }
	    public int sumRange(int i, int j) {
	        if (sums == null) return 0;
	        return (int)(sums[j + 1] - sums[i]);
	    }
	}
}
