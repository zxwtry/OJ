package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 	Given two arrays, write a function to compute their intersection.
 * 	
 *	Example:
 *	Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 *	
 *	Note:
 *	Each element in the result should appear as many times as it shows in both arrays.
 *	The result can be in any order.
 *	Follow up:
 *	What if the given array is already sorted? How would you optimize your algorithm?
 *	What if nums1's size is small compared to nums2's size? Which algorithm is better?
 *	What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P350_IntersectionOfTwoArraysII.java
 * @type        P350_IntersectionOfTwoArraysII
 * @date        2017年2月4日 下午7:24:33
 * @details     Solution1: AC 8ms 28.28%
 */
public class P350_IntersectionOfTwoArraysII {
	static class Solution1 {
	    public int[] intersect(int[] nums1, int[] nums2) {
	        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)
                return new int[0];
	        if (nums1.length > nums2.length) return intersect(nums2, nums1);
	        HashMap<Integer, Integer> map1 = new HashMap<Integer, Integer>(nums1.length);
	        for (int num : nums1) {
	            Integer val = map1.get(num);
	            map1.put(num, val == null ? 1 : val + 1);
	        }
            HashMap<Integer, Integer> map2 = new HashMap<Integer, Integer>(Math.min(nums2.length, map1.size() + 1));
            int size = 0;
            for (int num : nums2) {
                Integer val1 = map1.get(num);
                Integer val2 = map2.get(num);
                if (val1 != null && (val2 == null || val1 > val2)) {
                    map2.put(num, val2 == null ? 1 : val2 + 1);
                    size += 1;
                }
            }
            int[] answerArray = new int[size];
	        int answerArrayIndex = 0;
	        for (Map.Entry<Integer, Integer> entry : map2.entrySet()) {
	            int key = entry.getKey(), val = entry.getValue();
	            while (val -- > 0) {
	                answerArray[answerArrayIndex ++] = key;
	            }
	        }
	        return answerArray;
	    }
	}
}
