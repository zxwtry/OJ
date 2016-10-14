package leetcode;

import java.util.Arrays;

/*
 * 	Find the kth largest element in an unsorted array. 
 * 	Note that it is the kth largest element in the 
 * 	sorted order, not the kth distinct element.

	For example,
	Given [3,2,1,5,6,4] and k = 2, return 5.
	
	Note: 
	You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

public class P215_KthLargestElementinanArray {
	public static void main(String[] args) {
		int[] nums = new int[] {1};
		int k = 1;
		Solution s = new Solution();
		System.out.println(s.findKthLargest(nums, k));
	}
	/*
	 * 	4 ms
	 * 	83.11%
	 */
	static class Solution {
	    public int findKthLargest(int[] nums, int k) {
	    	Arrays.sort(nums);
	        return nums[nums.length - k];
	    }
	}
}