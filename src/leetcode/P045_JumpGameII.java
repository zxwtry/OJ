package leetcode;

import java.util.Arrays;

/*
 * 	Given an array of non-negative integers, you are initially positioned 
 * 	at the first index of the array.

	Each element in the array represents your maximum jump length at that position.
	
	Your goal is to reach the last index in the minimum number of jumps.
	
	For example:
	Given array A = [2,3,1,1,4]
	
	The minimum number of jumps to reach the last index is 2. (Jump 1 step from 
	index 0 to 1, then 3 steps to the last index.)
	
	Note:
	You can assume that you can always reach the last index.
	
	大致意思是：
		从0开始跳，每次跳的步数 <= 当前的值
		结果就是要跳到最后一个
		求跳最少次数
 */

public class P045_JumpGameII {

	static class Solution1 {
	    public int jump(int[] nums) {
	        int nn = nums == null ? 0 : nums.length;
	        if (nn < 2) {
	            return 0;
	        }
	        int[] step = new int[nn];
	        Arrays.fill(step, Integer.MAX_VALUE);
	        step[0] = 0;
	        for (int i = 0; i < nn - 1; i ++) {
	            for (int add = 1; add <= nums[i]; add ++) {
	                int j = i + add;
	                if (j >= nn) {
	                    break;
	                }
	                step[j] = Math.min(step[j], step[i] + 1);
	            }
	        }
	        return step[nn - 1];
	    }
	}
	
	static class Solution2 {
		int jump(int[] nums) {
			if (nums == null || nums.length < 2)
				return 0;
			int ans = 0, curMax = 0, curRch = 0;
			for (int i = 0; i != nums.length; i++) {
				if (curRch < i) {
					ans ++;
					curRch = curMax;
				}
				curMax = Math.max(curMax, nums[i] + i);
			}
			return ans;
		}
	}

}
