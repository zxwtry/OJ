package leetcode;

import java.util.Arrays;

/*
 * 	Given a sorted array and a target value, 
 * 	return the index if the target is found. 
 * 	If not, return the index where it would be if it were inserted in order.

	You may assume no duplicates in the array.

	Here are few examples.
	[1,3,5,6], 5 → 2
	[1,3,5,6], 2 → 1
	[1,3,5,6], 7 → 4
	[1,3,5,6], 0 → 0
 */
public class P035_SearchInsertPosition {
	public static void main(String[] args) {
		System.out.println(new Solution().searchInsert(new int[] {1,3,5,6,69}, 69));
		System.out.println(new Solution2().searchInsert(new int[] {1,3,5,6,69}, 69));
	}
	/*
	 * 	0 ms
	 * 	16.95%
	 * 	又是试错型AC，今天怎么了
	 */
	static class Solution {
	    public int searchInsert(int[] nums, int target) {
	        if (nums == null || nums.length == 0)
	        	return 0;
	        if (target > nums[nums.length - 1])
	        	return nums.length;
	        return getIndex(nums, 0, nums.length - 1, target);
	    }
	    private int getIndex(int[] nums, int sti, int eni, int target) {
	    	while (sti < eni) {
	    		int mid = (sti + eni) >> 1;
//	    		mid = (mid & 0x1) == 1 ? (mid >> 1) + 1 : mid >> 1;
	    		if (nums[mid] > target)
	    			eni = mid;
	    		else if (nums[mid] < target)
	    			sti = mid + 1;
	    		else
	    			return mid;
	    	}
	    	return sti;
	    }
	}
	static class Solution2 {
	    public int searchInsert(int[] nums, int target) {
	        int nn = nums == null ? 0 : nums.length;
	        if (nn == 0) {
	            return 0;
	        }
	        int index = Arrays.binarySearch(nums, target);
	        if (index < 0) {
	            index = - (index + 1);
	        }
	        return index;
	    }
	}
}
