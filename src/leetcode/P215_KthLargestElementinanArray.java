package leetcode;

import java.util.Arrays;

/**
 * 	Find the kth largest element in an unsorted array. 
 * 	Note that it is the kth largest element in the 
 * 	sorted order, not the kth distinct element.

	For example,
	Given [3,2,1,5,6,4] and k = 2, return 5.
	
	Note: 
	You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

public class P215_KthLargestElementinanArray {
	/**
	 * 	4 ms
	 * 	83.11%
	 */
	static class Solution {
	    public int findKthLargest(int[] nums, int k) {
	    	Arrays.sort(nums);
	        return nums[nums.length - k];
	    }
	}
	/**
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
	/**
	 *     时间： O(N) + O(K*logK)
	 *     空间: O(K)
	 *     AC
	 *     2ms
	 *     97.27%
	 */
	static class Solution3 {
	    public int findKthLargest(int[] nums, int k) {
	        if (k > nums.length || k <= 0) return 0; 
	        int[] heap = new int[k];
	        System.arraycopy(nums, 0, heap, 0, k);
	        //build heap
	        for (int index = (k - 1) /2; index > -1; index --)
	            heapDown(heap, index);
	        for (int index = k; index < nums.length; index ++) {
	            if (heap[0] < nums[index]) {
	                heap[0] = nums[index];
	                heapDown(heap, 0);
	            }
	        }
	        return heap[0];
	    }
	    private void heapDown(int[] heap, int index) {
	        int childIndex = 2 * index + 1;
	        while (childIndex < heap.length) {
	            if (childIndex + 1 < heap.length && heap[childIndex + 1] < heap[childIndex]) 
	                childIndex ++;
	            if (heap[childIndex] < heap[index]) {
	                swap(heap, childIndex, index);
	            }
	            index = childIndex;
	            childIndex = 2 * index + 1;
	        }
 	    }
	    private void swap(int[] heap, int i, int j) {
	        int tmp = heap[i];
	        heap[i] = heap[j];
	        heap[j] = tmp;
	    }
	}
}