package leetcode;

/**
 * 	Given a binary array, find the maximum number of 
 * 	consecutive 1s in this array.
 *	
 *	Example 1:
 *	Input: [1,1,0,1,1,1]
 *	Output: 3
 *	Explanation: The first two digits or the last three digits are consecutive 1s.
 *	    The maximum number of consecutive 1s is 3.
 *	Note:
 *	
 *	The input array will only contain 0 and 1.
 *	The length of input array is a positive integer and will not exceed 10,000
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P485_MaxConsecutiveOnes.java
 * @type        P485_MaxConsecutiveOnes
 * @date        2017年2月5日 下午2:20:24
 * @details     Solution1: 14ms 18.99%
 */
public class P485_MaxConsecutiveOnes {
	static class Solution1 {
	    public int findMaxConsecutiveOnes(int[] nums) {
	        int max = 0;
	        int count = 0;
	        for (int i = nums.length - 1; i >= (max - count) && i > -1; i --) {
	            if (nums[i] == 1) count ++;
	            else {
	                max = Math.max(max, count);
	                count = 0;
	            }
	        }
	        max = Math.max(max, count);
	        return max;
	    }
	}
}
