package leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P643_MaximumAverageSubarrayI.java
 * @date        2017年7月16日 上午10:19:59
 * @details     
 */
public class P643_MaximumAverageSubarrayI {
    public static void main(String[] args) {
        int[] nums = {1,12,-5,-6,50,3};
        int k = 4;
        System.out.println(new Solution().findMaxAverage(nums, k));
    }
    static public class Solution {
        public double findMaxAverage(int[] nums, int k) {
            int sum = 0;
            int maxSum = 0;
            for (int i = 0; i < k; i ++) sum += nums[i];
            int fi = k;
            int bi = 0;
            maxSum = sum;
            for (; fi < nums.length; fi ++) {
                sum -= nums[bi];
                sum += nums[fi];
                maxSum = Math.max(maxSum, sum);
                bi ++;
            }
            return ((double)maxSum) / k;
        }
    }
}
