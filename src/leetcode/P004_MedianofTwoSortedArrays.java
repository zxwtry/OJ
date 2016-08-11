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
//		System.out.println(new Solution1().findMedianSortedArrays(new int[] {1, 1, 3, 3}, new int[] {1, 1, 3, 3}));
//		int k = -1;
//		while (true) {
//			k ++;
//			int[] arr1, arr2;
//			System.out.print("k: " + k + "  ");
////			System.out.println(new Solution2().findKth_zxwtry(arr1 = new int[] { 0, 2, 4, 6, 8}, 0, arr1.length - 1, arr2 = new int[] {1, 3, 5, 7, 9}, 0, arr2.length - 1, k));
////			System.out.println(new Solution2().findKth_zxwtry(arr1 = new int[] { 0, 1, 2, 3, 4}, 0, arr1.length - 1, arr2 = new int[] {5, 6, 7, 8, 9}, 0, arr2.length - 1, k));
////			System.out.println(new Solution2().findKth_zxwtry(arr1 = new int[] { 0 }, 0, arr1.length - 1, arr2 = new int[] {5, 6, 7, 8, 9}, 0, arr2.length - 1, k));
//			System.out.println(new Solution2().findMedianSortedArrays(arr1 = new int[]{0, 1, 2, 3, 4}, arr2 = new int[] {5, 6, 7, 8, 9}));
//			if (k == arr1.length + arr2.length - 1) {
//				break;
//			}
//		}
//		System.out.println(new Solution2().findMedianSortedArrays(new int[]{8}, new int[] {5, 6, 7, 8, 9}));
		System.out.println(new Solution2().findMedianSortedArrays(new int[]{}, new int[] {5, 6}));
	}
	/*
	 *  9.33%
	 *  7ms
	 *  也真是够慢的。
	 *  但确实是O(log(M+N))
	 */
	static class Solution1 {
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
	/*
	 * 	第一份代码的最重要缺陷是代码实在是太长了。
	 * 	第二份代码应该会非常短，只要短代码就好
	 * 	6ms
	 * 	14.77%
	 * 	还更快。。。
	 */
	static class Solution2 {
		/*
		 * 	这里k从0开始计数
		 */
		int findKth_zxwtry(int[] nums1, int l1, int r1, int[] nums2, int l2, int r2, int k) {
			int d1 = r1 - l1, d2 = r2 - l2;
			if (d1 > d2)
				return findKth_zxwtry(nums2, l2, r2, nums1, l1, r1, k);
			if (d1 < 0)
				return nums2[l2 + k];
			if (k == 0)
				return Math.min(nums1[l1], nums2[l2]);
			int p1 = Math.min((k + 1) / 2 - 1, d1), p2 = k - p1 - 1;
			if (p1 < 0 || p2 < 0)
				return nums2[l2 + k];
			if (nums1[l1 + p1] < nums2[l2 + p2]) {
				return findKth_zxwtry(nums1, l1 + p1 + 1, r1, nums2, l2, r2, k - p1 - 1);
			} else if (nums1[l1 + p1] > nums2[l2 + p2]){
				return findKth_zxwtry(nums1, l1, r1, nums2, l2 + p2 + 1, r2, k - p2 - 1);
			} else {
				return nums1[l1 + p1];
			}
		}
		public double findMedianSortedArrays(int[] nums1, int[] nums2) {
			if (nums1 == null || nums2 == null) {
				int[] not_null_arr = nums1;
				if (not_null_arr == null)
					not_null_arr = nums2;
				if (not_null_arr == null)
					return 0;
				if (not_null_arr.length == 0)
					return 0;
				if ( (not_null_arr.length &0x1) == 1 )
					return not_null_arr[not_null_arr.length >>> 1];
				else
					return ((double) not_null_arr[not_null_arr.length >>> 1] + not_null_arr[(not_null_arr.length >>> 1) - 1]) / 2;
			}
			if (nums1.length > nums2.length)
				return findMedianSortedArrays(nums2, nums1);
			if (nums1.length == 0)
				return findMedianSortedArrays(null, nums2);
			if ( ( (nums1.length + nums2.length) & 0x1 ) == 1) 
				return findKth_zxwtry(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, (nums1.length + nums2.length) >>> 1 );
			else
				return ((double) findKth_zxwtry(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, (nums1.length + nums2.length) >>> 1 )  + 
						findKth_zxwtry(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, ( (nums1.length + nums2.length) >>> 1 )  - 1)) / 2;
		}
	}
}