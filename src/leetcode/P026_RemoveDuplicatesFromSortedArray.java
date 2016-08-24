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
	public static void main(String[] args) {
		System.out.println(new Soltion1().removeDuplicates(new int[] {1, 1, 2}));
	}
	/*
	 * 	修改原数组
	 * 	返回"长度"
	 * 	1 ms
	 * 	54.77%
	*/
	static class Soltion1 {
	    public int removeDuplicates(int[] nums) {
	        if (nums == null || nums.length < 2)
	        	return nums == null ? 0 : nums.length;
	        int pre = nums[0], count = 1;
	        for(int i = 1; i != nums.length; i ++) {
	        	if (pre != nums[i]) {
	        		nums[count ++] = nums[i];
	        		pre = nums[i];
	        	}
	        }
	        return count;
	    }
	}
}
