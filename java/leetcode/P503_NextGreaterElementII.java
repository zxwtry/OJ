package leetcode;

/**
 *  Given a circular array (the next element of the last element is the first element of the array),
 *  print the Next Greater Number for every element. The Next Greater Number of a number x is 
 *  the first greater number to its traversing-order next in the array, which means you could 
 *  search circularly to find its next greater number. If it doesn't exist, output -1 for this number.
 *  
 *  Example 1:
 *  Input: [1,2,1]
 *  Output: [2,-1,2]
 *  Explanation: The first 1's next greater number is 2; 
 *  The number 2 can't find next greater number; 
 *  The second 1's next greater number needs to search circularly, which is also 2.
 *  Note: The length of given array won't exceed 10000.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P503_NextGreaterElementII.java
 * @type        P503_NextGreaterElementII
 * @date        2017年3月14日 下午9:53:26
 * @details     Solution1: AC 279ms 9.11%
 */
public class P503_NextGreaterElementII {
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        int[] nums = new int[] {1,2,1};
        int[] ans = solution1.nextGreaterElements(nums);
        tools.Utils.printArray(ans, ans.length, 5);
    }
    static class Solution1 {
        public int[] nextGreaterElements(int[] nums) {
            if (nums == null || nums.length == 0) return nums;
            int max = Integer.MIN_VALUE;
            for (int num : nums) max = Math.max(max, num);
            int len = nums.length;
            int[] answer = new int[len];
            for (int i = 0; i < len; i ++) {
                if (nums[i] == max) {
                    answer[i] = -1;
                } else {
                    for (int j = 1; j < len; j ++) {
                        if (nums[(i + j + len) % len] > nums[i]) {
                            answer[i] = nums[(i + j + len) % len];
                            break;
                        }
                    }
                }
            }
            return answer;
        }
    }
}
