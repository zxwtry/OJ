package leetcode;

import java.util.Arrays;

/**
 * 	Given an unsorted array of integers, find the length of longest increasing subsequence.
 *	
 *	For example,
 *	Given [10, 9, 2, 5, 3, 7, 101, 18],
 *	The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. 
 *	Note that there may be more than one LIS combination, it is only necessary for 
 *	you to return the length.
 *	
 *	Your algorithm should run in O(n2) complexity.
 *	
 *	Follow up: Could you improve it to O(n log n) time complexity?
 *	
 *	Credits:
 *	Special thanks to @pbrother for adding this problem and creating all test cases.
 * 	
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P300_LongestIncreasingSubsequence.java
 * @type        P300_LongestIncreasingSubsequence
 * @date        2016年12月28日 下午10:18:57
 * @details     Solution1: AC 34ms 12.19%
 * @details     Solution2: AC  1ms 87.95%
 */
public class P300_LongestIncreasingSubsequence {
	static class Solution1 {
	    public int lengthOfLIS(int[] nums) {
	        if (nums == null || nums.length < 1) return 0;
	        int[] leftSmallCount = new int[nums.length];
	        Arrays.fill(leftSmallCount, 1);
	        int maxLength = 1;
	        for (int index = 1; index < nums.length; index ++) {
	        	for (int leftIndex = index - 1; leftIndex > -1; leftIndex --) {
	        		if (nums[leftIndex] == nums[index]) {
	        			leftSmallCount[index] = Math.max(leftSmallCount[index], leftSmallCount[leftIndex]);
	        			break;
	        		} else if (nums[leftIndex] < nums[index]) {
	        			leftSmallCount[index] = Math.max(leftSmallCount[index], leftSmallCount[leftIndex] + 1);
	        			maxLength = Math.max(maxLength, leftSmallCount[index]);
	        		}
	        	}
	        }
	        return maxLength;
	    }
	}
	static class Solution2 {
		public int lengthOfLIS(int[] nums) {
			if (nums == null || nums.length < 1) return 0;
			int[] dp = new int[nums.length];
			int dpIndex = 0;
			for (int num : nums) {
				int insertIndex = Arrays.binarySearch(dp, 0, dpIndex, num);
				if (insertIndex < 0) insertIndex = -(insertIndex + 1);
				dp[insertIndex] = num;
				if (insertIndex == dpIndex) dpIndex ++; 
			}
			return dpIndex;
		}
	}
}
