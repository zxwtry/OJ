package leetcode;

/**
 * 	You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. 
 * 	Now you have 2 symbols + and -. For each integer, you should choose one from + and - 
 * 	as its new symbol.
 *	
 *	Find out how many ways to assign symbols to make sum of integers equal to target S.
 *	
 *	Example 1:
 *	Input: nums is [1, 1, 1, 1, 1], S is 3. 
 *	Output: 5
 *	Explanation: 
 *	
 *	-1+1+1+1+1 = 3
 *	+1-1+1+1+1 = 3
 *	+1+1-1+1+1 = 3
 *	+1+1+1-1+1 = 3
 *	+1+1+1+1-1 = 3
 *	
 *	There are 5 ways to assign symbols to make the sum of nums be target 3.
 *	Note:
 *	The length of the given array is positive and will not exceed 20.
 *	The sum of elements in the given array will not exceed 1000.
 *	Your output answer is guaranteed to be fitted in a 32-bit integer.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode    
 * @file        P494_TargetSum.java
 * @type        P494_TargetSum
 * @date        2017年2月6日 下午11:31:12
 * @details     Solution1: AC  20ms 79.13%
 * @details     Solution2: AC  22ms 75.51%
 * @details     Solution3: AC 303ms 42.15%
 */
public class P494_TargetSum {
	static class Solution1 {
	    public int findTargetSumWays(int[] nums, int s) {
	        int sum = 0;
	        for (int n : nums)
	            sum += n;
	        return sum < s || (s + sum) % 2 > 0 ? 0 : subsetSum(nums, (s + sum) >>> 1); 
	    }   
	    public int subsetSum(int[] nums, int s) {
	        int[] dp = new int[s + 1]; 
	        dp[0] = 1;
	        for (int n : nums)
	            for (int i = s; i >= n; i--)
	                dp[i] += dp[i - n]; 
	        return dp[s];
	    } 
	}
	static class Solution2 {
	    public int findTargetSumWays(int[] nums, int s) {
	        int sum = 0; 
	        for(int i: nums) sum+=i;
	        if(s>sum || s<-sum) return 0;
	        int[] dp = new int[2*sum+1];
	        dp[0+sum] = 1;
	        for(int i = 0; i<nums.length; i++){
	            int[] next = new int[2*sum+1];
	            for(int k = 0; k<2*sum+1; k++){
	                if(dp[k]!=0){
	                    next[k + nums[i]] += dp[k];
	                    next[k - nums[i]] += dp[k];
	                }
	            }
	            dp = next;
	        }
	        return dp[sum+s];
	    }
	}
	static class Solution3 {
	    int result = 0;
	    public int findTargetSumWays(int[] nums, int S) {
	        if(nums == null || nums.length == 0) return result;
	        int n = nums.length;
	        int[] sums = new int[n];
	        sums[n - 1] = nums[n - 1];
	        for (int i = n - 2; i >= 0; i--)
	            sums[i] = sums[i + 1] + nums[i];
	        helper(nums, sums, S, 0);
	        return result;
	    }
	    public void helper(int[] nums, int[] sums, int target, int pos){
	        if(pos == nums.length){
	            if(target == 0) result++;
	            return;
	        }
	        if (sums[pos] < Math.abs(target)) return;
	        helper(nums, sums, target + nums[pos], pos + 1);
	        helper(nums, sums, target - nums[pos], pos + 1);
	    }
	}
}