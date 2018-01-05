package leetcode;

/*
 * 	Note: This is an extension of House Robber.

	对于数组array，index相邻的不能取
	
	跟HouseRobber不一样的就是：将array弄成环
	
	After robbing those houses on that street, the thief has found himself 
	a new place for his thievery so that he will not get too much attention. 
	This time, all houses at this place are arranged in a circle. That means 
	the first house is the neighbor of the last one. Meanwhile, the security 
	system for these houses remain the same as for those in the previous street.
	
	Given a list of non-negative integers representing the amount of money 
	of each house, determine the maximum amount of money you can rob tonight
	 without alerting the police.
 */

public class P213_HouseRobberII {
	public static void main(String[] args) {
		
	}
	/*
	 * 	1 ms
	 * 	5.60%
	 */
	static class Solution {
	    public int rob(int[] nums) {

	    	if (nums == null || nums.length == 0) {
	    		return 0;
	    	}
	    	if (nums.length == 1) {
	    		return nums[0];
	    	}

	    	if (nums.length == 2) {
	    		return Math.max(nums[0], nums[1]);
	    	}
	    	
	        return Math.max(rob(nums, 1, nums.length - 1), rob(nums, 0, nums.length - 2));
	    
	    }
	    
	    public int rob(int[] nums, int startIndex, int endIndex) {
	    	int[] h = new int[nums.length];
	    	h[startIndex] = nums[startIndex];
	    	h[startIndex + 1] = Math.max(nums[startIndex], nums[startIndex + 1]);
	    	
	    	for (int i = startIndex + 2; i <= endIndex; i ++) {
	    		h[i] = Math.max(h[i - 1], nums[i] + h[i - 2]);
	    	}
	    	
	        return h[endIndex];
	    }
	    
	}
}
