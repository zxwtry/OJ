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
		int[] nums = new int[] {3,2,1,5,6,4};
		int k = 6;
		Solution2 s = new Solution2();
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
	/*
	 * 	写一个QuickSort
	 * 	52 ms
	 * 	25.82%
	 */
	static class Solution2 {
		int targetIndex = 0;
		int targetValue = 0;
		boolean isFindAnswer = false;
		public int findKthLargest(int[] nums, int k) {
			targetIndex = nums.length - k;
			quickSort(nums, 0, nums.length - 1);
			return isFindAnswer ? targetValue : nums[targetIndex];
		}
		
		void quickSort(int[] nums, int startIndex, int endIndex) {
			if (startIndex >= endIndex || isFindAnswer) {
				return;
			}
			int partitionIndex = partition(nums, startIndex, endIndex);
			quickSort(nums, startIndex, partitionIndex - 1);
			quickSort(nums, partitionIndex + 1, endIndex);
		}
		private int partition(int[] nums, int startIndex, int endIndex) {
			int selectPivotValue = nums[startIndex];
			while (startIndex < endIndex) {
				while (startIndex < endIndex && nums[endIndex] >= selectPivotValue) endIndex --;
				nums[startIndex] = nums[endIndex];
				while (startIndex < endIndex && nums[startIndex] <= selectPivotValue) startIndex ++;
				nums[endIndex] = nums[startIndex];
			}
			nums[startIndex] = selectPivotValue;
			if (startIndex == targetIndex) {
				isFindAnswer = true;
				targetValue = selectPivotValue;
			}
			return startIndex;
		}
	}
}