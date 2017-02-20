package leetcode;


/**
 *  Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 *  
 *  Example 1:
 *  Input: [0,1]
 *  Output: 2
 *  Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 *  Example 2:
 *  Input: [0,1,0]
 *  Output: 2
 *  Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 *  Note: The length of the given binary array will not exceed 50,000.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P525_ContiguousArray.java
 * @type        P525_ContiguousArray
 * @date        2017年2月19日 上午11:17:38
 * @details     
 */
public class P525_ContiguousArray {
    public static void main(String[] args) {
        double value = 234234234.1231234324;
        String s = String.format("%f", value);
        System.out.println(s);
    }
    static class Solution {
        public int findMaxLength(int[] nums) {
            int len = nums.length;
            int numOf1 = 0;
            int[] left1Count = new int[len];
            for (int numsIndex = 0; numsIndex < len; numsIndex ++) {
                numOf1 += nums[numsIndex];
                left1Count[numsIndex] = numOf1;
            }
            int[] right1Count = new int[len];
            int tmp = 0;
            for (int numsIndex = len-1; numsIndex > -1; numsIndex --) {
                tmp += nums[numsIndex];
                right1Count[numsIndex] = tmp;
            }
            int numOf0 = len - numOf1;
            int leftIndex = 0, rightIndex = nums.length - 1;
            if (len / 2 == numOf1)  {
                if (len % 2 == 0)
                    return len;
                else if (len % 2 == 1 && (nums[0] == 1 || nums[len - 1] == 1))
                    return len - 1;
            }
            
        }
    }
}
