package leetcode;

/*
 * 	Given two sorted integer arrays nums1 and nums2, 
 * 	merge nums2 into nums1 as one sorted array.

	Note:
	You may assume that nums1 has enough space 
	(size that is greater or equal to m + n) 
	to hold additional elements from nums2.
	 The number of elements initialized in nums1 and nums2 
	 are m and n respectively.
 */

public class P088_MergeSortedArray {
	public static void main(String[] args) {
		
	}
	/*
	 * 	1 ms
	 * 	7.98%
	 */
	static class Solution {
	    public void merge(int[] nums1, int m, int[] nums2, int n) {
	    	if (nums1 == null || nums2 == null || nums2.length == 0 || n == 0)
	    		return;
	        int eni1 = m - 1, eni2 = n - 1, eni3 = m + n - 1;
	        while (eni1 != -1 && eni2 != -1)
	        	nums1[eni3 --] = nums1[eni1] < nums2[eni2] ? nums2[eni2 --] : nums1[eni1 --];
	        if (eni1 != -1 && eni3 != eni1) {
	        	while (eni1 != -1)
	        		nums1[eni3 --] = nums1[eni1 --];
	        }
	        if (eni2 != -1) {
	        	while (eni2 != -1)
	        		nums1[eni3 --] = nums2[eni2 --];
	        }
	    }
	}
}
