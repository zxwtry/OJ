package leetcode;

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
	public static void main(String[] args) {
		System.out.println(new Solution1().jump(new int[] {2,3,1,1,4}));
		System.out.println(new Solution1().jump(new int[] {1,2,3}));
		System.out.println(new Solution1().jump(new int[] {5,6,4,4,6,9,4,4,7,4,4,8,2,6,8,1,5,9,6,5,2,7,9,7,9,6,9,4,1,6,8,8,4,4,2,0,3,8,5}));
	}
	/*
	 * 	最低效的解法，搜索
	 * 	TLE
	 * 	程序没有进入死循环，还是有解的，就是效率过低
	 */
	static class Solution1 {
		int count = Integer.MAX_VALUE;
	    public int jump(int[] nums) {
	    	if (nums == null || nums.length < 2)
	    		return 0;
	    	zxwtry(nums, 0, 0);
	        return count;
	    }
		private void zxwtry(int[] nums, int nowIndex, int nowStep) {
			if (nowIndex + nums[nowIndex] > nums.length - 2) {
				count = Math.min(count, nowStep + 1);
				return;
			}
			for (int i = 1; i <= nums[nowIndex]; i ++) {
				zxwtry(nums, nowIndex + i, nowStep + 1);
			}
		}
	}
	static class Solution2 {
	    public int jump(int[] nums) {
	    	
	        return 0;
	    }
	}
}
