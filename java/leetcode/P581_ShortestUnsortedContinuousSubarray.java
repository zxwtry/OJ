package leetcode;

/**
    Given an integer array, you need to find one continuous subarray that 
    if you only sort this subarray in ascending order, then the whole array
    will be sorted in ascending order, too.

    You need to find the shortest such subarray and output its length.
    
    Example 1:
    Input: [2, 6, 4, 8, 10, 9, 15]
    Output: 5
    Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order 
    to make the whole array sorted in ascending order.
    Note:
    Then length of the input array is in range [1, 10,000].
    The input array may contain duplicates, so ascending order here means <=.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P581_ShortestUnsortedContinuousSubarray.java
 * @type        P581_ShortestUnsortedContinuousSubarray
 * @date        2017年5月14日 上午9:36:01
 * @details     Solution: AC 
 */
public class P581_ShortestUnsortedContinuousSubarray {
    static public class Solution {
        public int findUnsortedSubarray(int[] n) {
            int nn = n == null ? 0 : n.length;
            int[] left = new int[nn];
            int[] right = new int[nn];
            left[0] = n[0];
            for (int i = 1; i < nn; i ++) {
                left[i] = Math.max(left[i-1], n[i]);
            }
            right[nn-1] = n[nn-1];
            for (int i = nn-2; i > -1; i --) {
                right[i] = Math.min(right[i+1], n[i]);
            }
            int li = right[0] >= n[0] ? 0 : -1, ri = left[nn-1] <= n[nn-1] ? nn - 1 : nn;
            if (li != -1) while (li+1 < nn && right[li+1] >= n[li+1]) li ++;
            if (ri != nn) while (ri-1 > -1 && left[ri-1] <= n[ri-1]) ri --;
            return Math.max(ri-li-1, 0);
        }
    }
}
