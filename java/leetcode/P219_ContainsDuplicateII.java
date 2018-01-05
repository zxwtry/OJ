package leetcode;

/*
 * 	Given an array of integers and an integer k, find out whether there are
 * 	two distinct indices i and j in the array such that nums[i] = nums[j] 
 * 	and the difference between i and j is at most k.
 */

public class P219_ContainsDuplicateII {
	public static void main(String[] args) {
		System.out.println(new Solution().containsNearbyDuplicate(new int[] {1, 2, 1}, 1));
	}
	/*
	 * 	4 ms
	 * 	98.27%
	 */
	static class Solution {
	    public boolean containsNearbyDuplicate(int[] nums, int k) {
	    	if (null == nums || nums.length == 0) {
	    		return false;
	    	}
	    	int minIndex = -1, maxIndex = -1;
	    	for (int i = 0; i < nums.length; i ++) {
	    		if (minIndex == -1 || nums[i] < nums[minIndex]) {
	    			minIndex = i;
	    		} else if (nums[i] == nums[minIndex] && i - minIndex <= k) {
	    			return true;
	    		}
	    		if (maxIndex == -1 || nums[i] > nums[maxIndex]) {
	    			maxIndex = i;
	    		} else if (nums[i] == nums[maxIndex] && i - maxIndex <= k) {
	    			return true;
	    		}
	    	}
	    	int[] isAppeared = new int[nums[maxIndex] - nums[minIndex] + 1];
	    	for (int i = 0; i < nums.length; i ++) {
	    		if (isAppeared[nums[i] - nums[minIndex]] != 0 && i + 1 - isAppeared[nums[i] - nums[minIndex]] <= k) {
	    			return true;
//	    		}
//	    		else if (isAppeared[nums[i] - nums[minIndex]] == 0) {
//	    			isAppeared[nums[i] - nums[minIndex]] = i + 1;
	    		} else {
	    			isAppeared[nums[i] - nums[minIndex]] = i + 1;
	    		}
	    	}
	    	return false;
	    }
	}
}
