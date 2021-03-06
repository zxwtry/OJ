package leetcode;

import java.util.Arrays;

/**
 *  Given an array of 2n integers, your task is to group these integers into n pairs of 
 *  integer, say (a1, b2), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i 
 *  from 1 to n as large as possible.

    Example 1:
    Input: [1,4,3,2]
    
    Output: 4
    Explanation: n is 2, and the maximum sum of pairs is 4.
    Note:
    n is a positive integer, which is in the range of [1, 10000].
    All the integers in the array will be in the range of [-10000, 10000].
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P561_ArrayPartitionI.java
 * @type        P561_ArrayPartitionI
 * @date        2017年4月23日 上午9:59:51
 * @details     Solution: AC 43ms 57.14%
 */
public class P561_ArrayPartitionI {
    public static void main(String[] args) {
        int[] n = {1,4,3,2};
        System.out.println(new Solution().arrayPairSum(n));
    }
    static class Solution {
        public int arrayPairSum(int[] n) {
            int nn = n == null ? 0 : n.length;
            if (nn == 0) return 0;
            Arrays.sort(n);
            int ans = 0;
            for (int i = 0; i < nn; i += 2)
                ans += n[i];
            return ans;
        }
    }
}
