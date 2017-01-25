package leetcode;

/**
 * 	Given an unsorted array return whether an increasing subsequence of length 3 
 * 	exists or not in the array.

	Formally the function should:
	Return true if there exists i, j, k 
	such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
	Your algorithm should run in O(n) time complexity and O(1) space complexity.
	
	Examples:
	Given [1, 2, 3, 4, 5],
	return true.
	
	Given [5, 4, 3, 2, 1],
	return false.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P334_IncreasingTripletSubsequence.java
 * @type        P334_IncreasingTripletSubsequence
 * @date        2017年1月13日 下午10:21:48
 * @details     Solution1: AC 时间O(N)，空间O(N)
 */
public class P334_IncreasingTripletSubsequence {
	static class Solution1 {
	    public boolean increasingTriplet(int[] nums) {
	    	if (nums == null || nums.length < 3)
	    		return false;
	    	int len = nums.length;
	    	int[] big = new int[len];
	    	big[len - 1] = nums[len - 1];
	    	for (int i = len - 2; i >= 0; i --) {
	    		big[i] = Math.max(nums[i], big[i + 1]);
	    	}
	    	int[] small = new int[len];
	    	small[0] = nums[0];
	    	for (int i = 1; i < len; i ++) {
	    		small[i] = Math.min(nums[i], small[i - 1]);
	    	}
	    	for (int i = 0; i < len; i ++) {
	    		if (small[i] < nums[i] && big[i] > nums[i])
	    			return true;
	    	}
	    	return false;
	    }
	}
}
