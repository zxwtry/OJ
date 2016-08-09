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
		System.out.println(new Solution().findMedianSortedArrays(null, new int[] {2, 3}));
	}
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
			int d1 = r1 - l1, d2 = r2 - l2, dd = d1 - d2, offset = Math.abs((dd) >>> 1);
			if (nums1[r1] <= nums2[l2]) {
				if (dd < -1) {
					return ((double)nums2[l2 + offset] + nums2[l2 + offset - 1]) / 2;
				} else if (dd > 1) {
					return ((double)nums1[r1 - offset] + nums1[r1 - offset + 1]) / 2;
				} else {
					return ((double)nums1[r1] + nums2[l2]) / 2;
				}
			} else if (nums2[r2] <= nums1[l1]) {
				if (dd < -1) {
					return ((double)nums2[r2 - offset] + nums2[r2 - offset + 1]) / 2;
				} else if (dd > 1) {
					return ((double)nums1[l1 + offset] + nums1[l1 + offset - 1]) / 2;
				} else {
					return ((double)nums1[l1] + nums2[r2]) / 2;
				}
			}
			int m1 = (l1 + r1) >>> 1, m2 = (l2 + r2) >>> 1;
			if (nums1[m1] <= nums2[m2]) {
				
			}
			return 0;
		}
	    /*
	     * 	总共奇数个
	     * 	返回位于中间位置的数
	     */
		double findMedianSortedArraysOdd(int[] nums1, int l1, int r1, int[] nums2, int l2, int r2) {
			int d1 = r1 - l1, d2 = r2 - l2, offset = Math.abs((d2 - d1) >>> 1);
			if (nums1[r1] <= nums2[l2]) {
				return d1 < d2 ? nums2[l2 + offset] : nums1[r1 - offset];
			} else if (nums2[r2] <= nums1[l1]) {
				return d1 < d2 ? nums2[r2 - offset] : nums1[l1 + offset];
			}
			int m1 = (l1 + r1) >>> 1, m2 = (l2 + r2) >>> 1;
			if (r1 == m1) {
				return nums1[m1] < nums2[m2 + 1] ? nums1[m1] : nums2[m2 + 1];
			} else if (r2 == m2) {
				return nums2[m2] < nums1[m1 + 1] ? nums2[m2] : nums1[m1 + 1];
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
