package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 	Given two arrays of length m and n with digits 0-9 representing 
 * 	two numbers. Create the maximum number of length k <= m + n from digits of the two. 
 * 	The relative order of the digits from the same array must be preserved. 
 * 	Return an array of the k digits. You should try to optimize your time and space complexity.
 *	
 *	Example 1:
 *	nums1 = [3, 4, 6, 5]
 *	nums2 = [9, 1, 2, 5, 8, 3]
 *	k = 5
 *	return [9, 8, 6, 5, 3]
 *	
 *	Example 2:
 *	nums1 = [6, 7]
 *	nums2 = [6, 0, 4]
 *	k = 5
 *	return [6, 7, 6, 0, 4]
 *	
 *	Example 3:
 *	nums1 = [3, 9]
 *	nums2 = [8, 9]
 *	k = 3
 *	return [9, 8, 9]
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P321_CreateMaximumNumber.java
 * @type        P321_CreateMaximumNumber
 * @date        2017年1月9日 下午9:07:53
 * @details     
 */
public class P321_CreateMaximumNumber {
    public static void main(String[] args) {
        int[] nums1 = {3, 4, 6, 5};
        int[] nums2 = {9, 1, 2, 5, 8, 3};
        Solution1 solution1 = new Solution1();
        for (int k = 1; k <= nums1.length + nums2.length; k ++)
            tools.Utils.printArray(solution1.maxNumber(nums1, nums2, k), k);
    }
	static class Solution1 {
	    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
	        if (nums1 == null || nums2 == null || k < 0 || k > nums1.length + nums2.length)
	        	return new int[0];
	        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
	        for (int num : nums1) priorityQueue.add(num);
	        for (int num : nums2) priorityQueue.add(num);
	        int[] maxNumber = new int[k];
	        for (int maxNumberIndex = 0; maxNumberIndex < maxNumber.length; maxNumberIndex ++)
	            maxNumber[maxNumberIndex] = priorityQueue.poll();
	        return maxNumber;
	    }
	}
}
