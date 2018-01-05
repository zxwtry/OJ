package leetcode;

/*
 * 	26. Remove Duplicates from Sorted Array  QuestionEditorial Solution  My Submissions
	Total Accepted: 150942
	Total Submissions: 438212
	Difficulty: Easy
	Given a sorted array, remove the duplicates in place such that 
	each element appear only once and return the new length.

	Do not allocate extra space for another array, you must do this in place 
	with constant memory.

	For example,
	Given input array nums = [1,1,2],

	Your function should return length = 2, with the first two elements of nums
	 being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
 */

public class P026_RemoveDuplicatesFromSortedArray {
	static class Soltion1 {
	    public int removeDuplicates(int[] nums) {
	        int len = nums == null ? 0 : nums.length;
	        if (len < 2) return len;
	        int pre = nums[0], count = 1;
	        for(int i = 1; i != len; i ++) {
	        	if (pre != nums[i]) {
	        		nums[count ++] = nums[i];
	        		pre = nums[i];
	        	}
	        }
	        return count;
	    }
	}
}
