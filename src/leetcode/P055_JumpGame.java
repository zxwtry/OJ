package leetcode;

/*
 * 	Given an array of non-negative integers, 
 * 	you are initially positioned at the first index of the array.
	Each element in the array represents your maximum jump length
	 at that position.
	Determine if you are able to reach the last index.
	For example:
	A = [2,3,1,1,4], return true.
	A = [3,2,1,0,4], return false.
 */

public class P055_JumpGame {
	public static void main(String[] args) {
		System.out.println(new Solution1().canJump(new int[]{2,3,1,1,4}));
		System.out.println(new Solution1().canJump(new int[]{3,2,1,0,4}));
	}
	/*
	 * 	4 ms
	 * 	3.78%
	 */
	static class Solution1 {
	    public boolean canJump(int[] nums) {
	    	if (nums == null || nums.length == 0)
	    		return true;
	    	int max = nums[0], i = 1;
	    	while (true) {
	    		if (max >= nums.length - 1)
	    			return true;
	    		if (max < i)
	    			return false;
	    		if (i == nums.length)
	    			break;
	    		max = Math.max(max, nums[i] + i);
	    		i ++;
	    	}
	        return false;
	    }
	}
	static class Solution2 {
	    public boolean canJump(int[] nums) {
	        return false;
	    }
	}
}
