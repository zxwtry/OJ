package leetcode;

/**
 * 	Given an integer array nums, find the sum of the elements between indices 
 * 	i and j (i ≤ j), inclusive.

 *	The update(i, val) function modifies nums by updating the element at 
 *	index i to val.
 *	Example:
 *	Given nums = [1, 3, 5]
 *	
 *	sumRange(0, 2) -> 9
 *	update(1, 2)
 *	sumRange(0, 2) -> 8
 *	Note:
 *	The array is only modifiable by the update function.
 *	You may assume the number of calls to update and sumRange function 
 *	is distributed evenly.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P307_RangeSumQueryMutable.java
 * @type        P307_RangeSumQueryMutable
 * @date        2016年12月29日 下午8:52:11
 * @details     NumArray1: TLE
 */
public class P307_RangeSumQueryMutable {
	static class NumArray1 {
		long[] sum = null;
		int[] nums = null;
	    public NumArray1(int[] nums) {
	        if (nums == null || nums.length == 0) return;
	        sum = new long[nums.length];
	        sum[0] = nums[0];
	        for (int index = 1; index < nums.length; index ++) {
	        	sum[index] = sum[index - 1] + nums[index];
	        }
	        this.nums = nums;
	    }

	    void update(int i, int val) {
	        long add = -(long)nums[i] + val;
	        for (int index = i; index < sum.length; index ++) {
	        	sum[index] += add;
	        }
	        nums[i] = val;
	    }

	    public int sumRange(int i, int j) {
	        if (i == 0) return (int)sum[j];
	        return (int)(sum[j] - sum[i - 1]);
	    }
	}


	// Your NumArray object will be instantiated and called as such:
	// NumArray numArray = new NumArray(nums);
	// numArray.sumRange(0, 1);
	// numArray.update(1, 10);
	// numArray.sumRange(1, 2);
}
