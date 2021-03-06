package leetcode;

/**
 * 	Given an array nums containing n + 1 integers where each integer is 
 * 	between 1 and n (inclusive), prove that at least one duplicate number
 *  must exist. Assume that there is only one duplicate number, find the 
 *  duplicate one.
 *	
 *	Note:
 *	You must not modify the array (assume the array is read only).
 *	You must use only constant, O(1) extra space.
 *	Your runtime complexity should be less than O(n2).
 *	There is only one duplicate number in the array, but it could 
 *	be repeated more than once.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P287_FindTheDuplicateNumber.java
 * @type        P287_FindTheDuplicateNumber
 * @date        2016年12月17日 下午10:26:14
 * @details     Solution1: AC 1ms 57.63%
 * @details     Solution2: AC 1ms 57.63%
 */
public class P287_FindTheDuplicateNumber {
	static class Solution1 {
	    public int findDuplicate(int[] nums) {
	        if (nums == null || nums.length < 1)
	        	return 0;
	        int[] map = new int[nums.length + 1];
	        for (int v : nums) {
	        	map[v] ++;
	        	if (map[v] > 1)
	        		return v;
	        }
	        return 0;
	    }
	}
	static class Solution2 {
		public int findDuplicate(int[] nums) {
			if (nums == null || nums.length < 1) return 0;
			int slow = nums[0];
			int fast = nums[nums[0]];
			while (slow != fast) {
				slow = nums[slow];
				fast = nums[nums[fast]];
			}
			fast = 0;
			while (slow != fast) {
				slow = nums[slow];
				fast = nums[fast];
			}
			return slow;
		}
	}
}
