package leetcode;

import java.util.HashMap;

/**
 * 	You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. 
 * 	Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
 * 	
 * 	The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. 
 * 	If it does not exist, output -1 for this number.
 * 	
 * 	Example 1:
 * 	Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 	Output: [-1,3,-1]
 * 	Explanation:
 * 	    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
 * 	    For number 1 in the first array, the next greater number for it in the second array is 3.
 * 	    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
 * 	Example 2:
 * 	Input: nums1 = [2,4], nums2 = [1,2,3,4].
 * 	Output: [3,-1]
 * 	Explanation:
 * 	    For number 2 in the first array, the next greater number for it in the second array is 3.
 * 	    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
 * 	Note:
 * 	All elements in nums1 and nums2 are unique.
 * 	The length of both nums1 and nums2 would not exceed 1000.
 */


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P496_NextGreaterElementI.java
 * @type        P496_NextGreaterElementI
 * @date        2017年2月8日 下午11:22:26
 * @details     Solution1: AC 8ms 89.13%
 */
public class P496_NextGreaterElementI {
    static class Solution1 {
        public int[] nextGreaterElement(int[] findNums, int[] nums) {
            int max = Integer.MIN_VALUE;
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = nums.length - 1; i > -1; i --) {
                if (max < nums[i]) {
                    map.put(nums[i], -1);
                } else {
                    for (int j = i + 1; j < nums.length; j ++) {
                        if (nums[j] > nums[i]) {
                            map.put(nums[i], nums[j]);
                            break;
                        }
                    }
                }
                max = Math.max(max, nums[i]);
            }
            int[] answer = new int[findNums.length];
            for (int i = 0; i < answer.length; i ++) {
                answer[i] = map.get(findNums[i]);
            }
            return answer;
        }
    }
}
