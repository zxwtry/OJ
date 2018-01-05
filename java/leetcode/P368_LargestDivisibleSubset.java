package leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *  Given a set of distinct positive integers, find the largest 
 * 	subset such that every pair (Si, Sj) of elements in this subset
 *  satisfies: Si % Sj = 0 or Sj % Si = 0.
 *	
 *	If there are multiple solutions, return any subset is fine.
 *	
 *	Example 1:
 *	
 *	nums: [1,2,3]
 *	
 *	Result: [1,2] (of course, [1,3] will also be ok)
 *	Example 2:
 *	
 *	nums: [1,2,4,8]
 *	
 *	Result: [1,2,4,8]
 *	Credits:
 *	Special thanks to @Stomach_ache for adding this problem and creating all test cases.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P368_LargestDivisibleSubset.java
 * @type        P368_LargestDivisibleSubset
 * @date        2016年12月8日 下午10:27:41
 * @details     Solution1: AC 36ms 51.74%
 */
public class P368_LargestDivisibleSubset {
	static class Solution1 {
	    public List<Integer> largestDivisibleSubset(int[] nums) {
	        if (nums == null || nums.length == 0) return new LinkedList<Integer>();
	        else if (nums.length == 1) return Collections.singletonList(nums[0]);
	        Arrays.sort(nums);
	        int[] dp = new int[nums.length];
	        for (int dpIndexRow = 1; dpIndexRow < nums.length; dpIndexRow ++)
	            for (int dpIndexCol = dpIndexRow - 1; dpIndexCol > -1; dpIndexCol --)
	                if (nums[dpIndexCol] != 0 && nums[dpIndexRow] % nums[dpIndexCol] == 0)
	                    dp[dpIndexRow] = Math.max(dp[dpIndexRow], dp[dpIndexCol] + 1);
	        int dpMaxIndex = 0;
	        boolean hasZero = false;
	        for (int dpIndex = 0; dpIndex < dp.length; dpIndex ++) {
	            dpMaxIndex = dp[dpMaxIndex] > dp[dpIndex] ? dpMaxIndex : dpIndex;
	            hasZero |= nums[dpIndex] == 0;
	        }
	        int dpCurrentValue = dp[dpMaxIndex];
	        int num = nums[dpMaxIndex];
	        List<Integer> answerList = new LinkedList<Integer>();
	        for (int dpIndex = dpMaxIndex; dpIndex > -1; dpIndex --) {
	            if (nums[dpIndex] != 0 && num % nums[dpIndex] == 0 && dp[dpIndex] == dpCurrentValue) {
	                answerList.add(nums[dpIndex]);
	                num = nums[dpIndex];
	                dpCurrentValue --;
	            }
	        }
	        if (hasZero) answerList.add(0);
	        return answerList;
	    }
	}
}
