package leetcode;

import java.util.Arrays;


/*
 * 	Given an unsorted array, find the maximum difference between 
 * 	the successive elements in its sorted form.

	nums:未排序
	
	假设nums已经排序，肯定有相邻数字的最大差值。
	
	现在只给没有排序的nums，要求给出那个最大差值
	
	Try to solve it in linear time/space.
	
	Return 0 if the array contains less than 2 elements.
	
	You may assume all elements in the array are non-negative
	 integers and fit in the 32-bit signed integer range.
	
	Credits:
	Special thanks to @porker2008 for adding this problem
	 and creating all test cases.
 */

public class P164_MaximumGap {
	public static void main(String[] args) {
		int len = 1000;
		int count = 0;
		for (; len < 1000 * 30; len ++) {
			int[] nums = tools.Random随机生成器.A_生成一个不重复随机数据(len, 0, len * 10);
			Solution s = new Solution();
			int standard = standardAnswer(nums);
			int solution = s.maximumGap(nums);
			if (standard != solution) {
				count ++;
			}
		}
		System.out.println(count);
	}
	/*
	 * 	5 ms
	 * 	57.25%
	 */
	static int standardAnswer(int[] nums_origin) {
		int[] nums = nums_origin.clone();
		Arrays.sort(nums);
		int ans = 0;
		for (int i = 1; i < nums.length; i ++) {
			ans = Math.max(nums[i] - nums[i - 1], ans);
		}
		return ans;
	}
	static class Solution {
	    public int maximumGap(int[] nums) {
	    	if (null == nums || nums.length < 2) {
	    		return 0;
	    	}
	    	if (nums.length == 2) {
	    		return Math.abs(nums[0] - nums[1]);
	    	}
	    	int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	    	for (int val : nums) {
	    		min = Math.min(min, val);
	    		max = Math.max(max, val);
	    	}
	    	int dockLen = nums.length + 1;
	    	int[] dockMin = new int[dockLen];
	    	int[] dockMax = new int[dockLen];
	    	dockMin[0] = min;	dockMin[dockLen - 1] = max;
	    	dockMax[0] = min;	dockMax[dockLen - 1] = max;
	    	
	    	if (min + 1 > max - 1) {
	    		return max - min;
	    	}
	    	
	    	double range = (double) (max - min + 1) / (dockLen - 2);
	    	
	    	for (int val : nums) {
	    		if (val == max || val == min) {
	    			continue;
	    		}
	    		int index = (int) ((val - min) / range) + 1;
	    		if (dockMax[index] == 0) {
	    			dockMax[index] = val;
	    		} else {
	    			dockMax[index] = Math.max(dockMax[index], val);
	    		}
	    		if (dockMin[index] == 0) {
	    			dockMin[index] = val;
	    		} else {
	    			dockMin[index] = Math.min(dockMin[index], val);
	    		}
	    	}
	    	
	    	int preIndex = 0, index = 1, ans = 0;
	    	for (; index < dockLen; index ++) {
	    		if (dockMin[index] == 0 && dockMax[index] == 0) {
	    			continue;
	    		}
	    		ans = Math.max(ans, dockMin[index] - dockMax[preIndex]);
	    		preIndex = index;
	    	}
	    	
	        return ans;
	    }
	}
}
