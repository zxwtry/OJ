package leetcode;

/*
 * 	Given an array and a value, remove all instances of that value in place and
 *  return the new length.

	Do not allocate extra space for another array, you must do this in place with
	 constant memory.

	The order of elements can be changed. It doesn't matter what you leave beyond
	 the new length.

	Example:
	Given input array nums = [3,2,2,3], val = 3

	Your function should return length = 2, with the first two elements of nums being 2.
 */


public class P027_RemoveElement {
	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4, 3, 2, 1, 2, 3, 4, 3};
		int ans = new Solution1().removeElement(nums, 3);
		for (int i = 0; i != ans; i ++)
			System.out.println(nums[i]);
	}
	/*
	 * 	1 ms
	 * 	3.96%
	 */
	static class Solution1 {
		public int removeElement(int[] nums, int val) {
	        if (nums == null || nums.length == 0)
	        	return 0;
	        int step = 0;
	        for (int i = 0; i != nums.length; i ++) {
	        	if (nums[i] == val) {
	        		step ++;
	        	} else {
	        		nums[i - step] = nums[i];
	        	}
	        }
	        return nums.length - step;
	    }
	}
}
