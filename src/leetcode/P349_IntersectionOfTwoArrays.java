package leetcode;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * 	Given two arrays, write a function to compute their intersection.
 *	
 *	Example:
 *	Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 *	
 *	Note:
 *	Each element in the result must be unique.
 *	The result can be in any order.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P349_IntersectionOfTwoArrays.java
 * @type        P349_IntersectionOfTwoArrays
 * @date        2017年2月3日 下午11:49:03
 * @details     Solution1: AC 7ms 26.37%
 */
public class P349_IntersectionOfTwoArrays {
	static class Solution1 {
	    public int[] intersection(int[] nums1, int[] nums2) {
	        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)
	            return new int[0];
	        if (nums1.length > nums2.length) return intersection(nums2, nums1);
	        HashSet<Integer> set1 = new HashSet<Integer>(nums1.length);
	        for (int num : nums1) set1.add(num);
	        LinkedList<Integer> list = new LinkedList<>();
	        for (int num : nums2) {
	            if (set1.contains(num)) {
	                list.add(num);
	                set1.remove(num);
	            }
	        }
	        int[] arr = new int[list.size()];
	        int arrIndex = 0;
	        for (int val : list) {
	            arr[arrIndex ++] = val;
	        }
	        return arr;
	    }
	}
}
