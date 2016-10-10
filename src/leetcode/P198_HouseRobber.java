package leetcode;

/*
 * 	You are a professional robber planning to rob houses along a street. 
 * 	Each house has a certain amount of money stashed, the only constraint 
 * 	stopping you from robbing each of them is that adjacent houses have 
 * 	security system connected and it will automatically contact the police 
 * 	if two adjacent houses were broken into on the same night.

	Given a list of non-negative integers representing the amount of 
	money of each house, determine the maximum amount of money
	 you can rob tonight without alerting the police.
 */

public class P198_HouseRobber {
	public static void main(String[] args) {
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(100, 0, 1000);
		String st = tools.Utils.LEETCODE_int_array_序列化_(arr);
		System.out.println(st);
	}
	/*
	 * 	0 ms
	 * 	35.11%
	 */
	static class Solution {
	    public int rob(int[] nums) {
	    	if (nums == null || nums.length == 0) {
	    		return 0;
	    	}
	    	if (nums.length == 1) {
	    		return nums[0];
	    	}
	    	
	    	//h的定义是：对于h[i]表示，在[0...i]之间选择的最大值
	    	int[] h = new int[nums.length];
	    	h[0] = nums[0];
	    	h[1] = Math.max(nums[0], nums[1]);
	    	
	    	for (int i = 2; i < nums.length; i ++) {
	    		h[i] = Math.max(h[i - 1], nums[i] + h[i - 2]);
	    	}
	    	
	        return h[nums.length - 1];
	    }
	}
}
