package leetcode;


/*

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]
The median is (2 + 3)/2 = 2.5

 */
public class P004_MedianofTwoSortedArrays {
	public static void main(String[] args) {
		System.out.println(new Solution().findMedianSortedArrays(new int[] {1, 1, 3, 3}, new int[] {1, 1, 3, 3}));
	}
	/*
	 *  9.33%
	 *  7ms
	 *  也真是够慢的。
	 */
	static class Solution {
	    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
	        if (nums1 == null || nums2 == null) {
	        	int[] nullArr = nums1;
	        	if (nullArr == null)
	        		nullArr = nums2;
	        	if (nullArr == null)
	        		return 0;
	        	if ((nullArr.length & 0x1) == 1)
	        		return nullArr[nullArr.length >>> 1];
	        	else
	        		return ((double)nullArr[nullArr.length >>> 1] + nullArr[(nullArr.length >>> 1) - 1]) / 2;
	        }
	        if (nums1.length == 0) {
	        	return findMedianSortedArrays(null, nums2);
	        } else if (nums2.length == 0) {
	        	return findMedianSortedArrays(nums1, null);
	        }
	        if (((nums1.length+nums2.length) & 0x1) == 1) {
	        	return findMedianSortedArraysOdd(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1);
	        } else {
	        	return findMedianSortedArraysEven(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1);
	        }
	    }
	    /*
	     * 	总共偶数个
	     * 	返回两个数的平均
	     */
		double findMedianSortedArraysEven(int[] nums1, int l1, int r1, int[] nums2, int l2, int r2) {
			int d1 = r1 - l1, d2 = r2 - l2, dd = d1 - d2, offset = Math.abs(dd) >>> 1;
			if (d1 > d2)
				return findMedianSortedArraysEven(nums2, l2, r2, nums1, l1, r1);
			if (nums1[r1] <= nums2[l2]) {
				if (dd == 0) {
					return ((double)nums1[r1] + nums2[l2]) / 2;
				} else {
					return ((double)nums2[l2 + offset] + nums2[l2 + offset - 1]) / 2;
				}
			} else if (nums2[r2] <= nums1[l1]) {
				if (dd == 0) {
					return ((double)nums1[l1] + nums2[r2]) / 2;
				} else {
					return ((double)nums2[r2 - offset] + nums2[r2 - offset + 1]) / 2;
				}
			}
			int m1 = (l1 + r1) >>> 1, m2 = (l2 + r2) >>> 1;
			if (r1 == m1) {
				if (nums2[m2] > nums1[m1]) {
					return nums2[m2 - 1] < nums1[m1] ? ((double)nums2[m2] + nums1[m1]) / 2 : ((double)nums2[m2] + nums2[m2 - 1]) / 2;
				} else {
					return nums2[m2 + 1] > nums1[m1] ? ((double)nums2[m2] + nums1[m1]) / 2 : ((double)nums2[m2] + nums2[m2 + 1]) / 2;
				}
			} 
			if (l2 == m2)
				return ((double) Math.max(nums1[l1], nums2[m2]) + Math.min(nums1[r1], nums2[m2+1])) / 2;
			if (l1 == m1) {
				if (nums1[r1] < nums2[m2 - 1])
					return ((double)nums2[m2 - 1] + nums2[m2]) / 2;
				else if (nums1[l1] > nums2[m2 + 1])
					return ((double)nums2[m2 + 1] + Math.min(nums2[m2 + 2], nums1[l1])) / 2;
				else 
					return ((double) Math.min(nums1[r1], nums2[m2 +1]) + Math.max(nums1[l1], nums2[m2])) / 2;
			}
			if (nums1[m1] <= nums2[m2]) {
				int cut = Math.min(m1 - l1, r2 - m2);
				nums1[m1 + 1] = Math.min(nums1[m1 + 1], nums2[m2 + 1]);
				return findMedianSortedArraysEven(nums1, l1 + cut, r1, nums2, l2, r2 - cut);
			} else {
				int cut = Math.min(r1 - m1, m2 - l2);
				nums2[m2 + 1] = Math.min(nums1[m1 + 1], nums2[m2 + 1]);
				return findMedianSortedArraysEven(nums1, l1, r1 - cut, nums2, l2 + cut, r2);
			}
		}
	    /*
	     * 	总共奇数个
	     * 	返回位于中间位置的数
	     */
		double findMedianSortedArraysOdd(int[] nums1, int l1, int r1, int[] nums2, int l2, int r2) {
			int d1 = r1 - l1, d2 = r2 - l2, offset = Math.abs(d2 - d1) >>> 1;
			if (d1 > d2)
				return findMedianSortedArraysOdd(nums2, l2, r2, nums1, l1, r1);
			if (nums1[r1] <= nums2[l2]) {
				return nums2[l2 + offset];
			} else if (nums2[r2] <= nums1[l1]) {
				return nums2[r2 - offset];
			}
			int m1 = (l1 + r1) >>> 1, m2 = (l2 + r2) >>> 1;
			if (r1 == m1) {
				if (nums1[m1] < nums2[m2])
					return nums2[m2];
				else if (nums1[m1] > nums2[m2 + 1])
					return nums2[m2 + 1];
				else
					return nums1[m1];
			}
			if (nums1[m1] <= nums2[m2]) {
				int cut = Math.min(m1 - l1 + 1, r2 - m2);
				return findMedianSortedArraysOdd(nums1, l1 + cut, r1, nums2, l2, r2 - cut);
			} else {
				int cut = Math.min(r1 - m1, m2 - l2 + 1);
				return findMedianSortedArraysOdd(nums1, l1, r1 - cut, nums2, l2 + cut, r2);
			}
		}
	}
}
