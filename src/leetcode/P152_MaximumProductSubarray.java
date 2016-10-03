package leetcode;

/*
 * 	Find the contiguous subarray within an array (containing at least one number) 
 * 	which has the largest product.

	For example, given the array [2,3,-2,4],
	the contiguous subarray [2,3] has the largest product = 6.
 */

public class P152_MaximumProductSubarray {
	public static void main(String[] args) {
		int[] nums = new int[] {
			2,3,-2,4
		};
		Solution s = new Solution();
		nums = tools.Random随机生成器.A_生成一个随机数据(100, -100, -50);
		System.out.println(tools.Utils.LEETCODE_int_array_序列化_(nums));
		System.out.println(s.maxProduct(nums));
	}
	static class Solution {
	    public int maxProduct(int[] nums) {
	    	if (nums == null || nums.length == 0) {
	    		return 0;
	    	}
	    	int[] positive = new int[nums.length];		//包含当前数字，能够算出来的最大结果
	    	int[] negative = new int[nums.length];		//包含当前数字，能够算出来的最小结果
	    	positive[0] = nums[0];
	    	negative[0] = nums[0];
	    	int max = nums[0];
	    	int temp1 = 0, temp2 = 0;
	        for (int i = 1; i < nums.length; i ++) {
	        	temp1 = positive[i - 1] * nums[i];
	        	temp2 = negative[i - 1] * nums[i];
        		max = Math.max(temp1, max);
        		max = Math.max(temp2, max);
	        	//必须包含前面
        		positive[i] = temp1;
        		negative[i] = temp1;
        		positive[i] = Math.max(temp2, temp1);
        		negative[i] = Math.min(temp2, temp1);
	        	//只包含当前数字，不使用前面数字的情况
	        	if (nums[i] > positive[i]) {
	        		positive[i] = nums[i];
	        		max = Math.max(nums[i], max);
	        	}
	        	if (nums[i] < negative[i]) {
	        		negative[i] = nums[i];
	        	}
	        }
	        tools.Utils.printArray(positive, 100);
	        tools.Utils.printArray(negative, 100);
	        return max;
	    }
	}
}
