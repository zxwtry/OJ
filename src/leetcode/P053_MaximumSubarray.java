package leetcode;

/*
 * 	Find the contiguous subarray within an array 
 * 	(containing at least one number) which has the largest sum.

	For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
	the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 */

public class P053_MaximumSubarray {
	public static void main(String[] args) {
		System.out.println(new Solution1().maxSubArray(new int[] {
				-2,1,-3,4,-1,2,1,-5,4
		}));
	}
	/*
	 * 	2 ms
	 * 	17.12%
	 */
	static class Solution1 {
		int ans = 0;
	    public int maxSubArray(int[] nums) {
	        if (nums == null || nums.length == 0)
	        	return ans;
	        int[] help = new int[nums.length];
	        ans = help[0] = nums[0];
	        for (int i = 1; i != nums.length; i ++) {
	        	help[i] = help[i - 1] > 0 ? help[i - 1] + nums[i] : nums[i];
	        	ans = Math.max(help[i], ans);
	        }
	        return ans;
	    }
	}
}
