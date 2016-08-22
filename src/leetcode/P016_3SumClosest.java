package leetcode;

import java.util.Arrays;

public class P016_3SumClosest {
	public static void main(String[] args) {
//		System.out.println(new Solution().threeSumClosest(new int[]{-1,2,1,-4}, 1));
//		System.out.println(new Solution().threeSumClosest(new int[]{0, 2, 1, -3}, 1));
		System.out.println(new Solution().threeSumClosest(new int[]{1,1,-1,-1,3}, -1));
	}
	/*
	 * 	12 ms
	 * 	67.05%
	 */
	static class Solution {
	    public int threeSumClosest(int[] nums, int target) {
	    	if (nums == null || nums.length < 3)
	    		return 0;
	    	int ans = nums[0] + nums[1] + nums[2];
	    	int cut = Integer.MAX_VALUE;
	    	Arrays.sort(nums);
	    	int i = 0, j = 0, k = 0;
	    	for (i = 0; i != nums.length; i ++) {
	    		j = i + 1; k = nums.length - 1;
	    		while (j < k) {
	    			int sum = nums[i] + nums[j] + nums[k];
	    			int real = sum - target;
	    			int tmp = Math.abs(real);
	    			if (real == 0) {
	    				return target;
	    			} else if (tmp < cut) {
	    				ans = sum;
	    				cut = tmp;
	    				if (real < cut)
	    					j ++;
	    				else
	    					k --;
	    			} else if (tmp == cut) {
	    				if (real < cut)
	    					j ++;
	    				else
	    					k --;
	    			} else {
	    				if (real < cut)
	    					j ++;
	    				else
	    					k --;
	    			}
	    		}
	    	}
	    	return ans;
	    }
	}
}