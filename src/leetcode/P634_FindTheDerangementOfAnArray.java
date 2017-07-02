package leetcode;

/**

In combinatorial mathematics, a derangement is a permutation of the elements of a set, 
such that no element appears in its original position.

There's originally an array consisting of n integers from 1 to n in ascending order, 
you need to find the number of derangement it can generate.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:
Input: 3
Output: 2
Explanation: The original array is [1,2,3]. The two derangements are [2,3,1] and [3,1,2].
Note:
n is in the range of [1, 106].

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P634_FindTheDerangementOfAnArray.java
 * @date        2017年7月2日 上午10:25:38
 * @details     Solution: AC
 */
public class P634_FindTheDerangementOfAnArray {
    static public class Solution {
        public int findDerangement(int n) {
            int mod = 1000000000 + 7;
            if (n < 1) return 0;
            if (n < 3) return n - 1;
            long[] a = new long[n + 1];
            a[0] = 0;
            a[1] = 0;
            a[2] = 1;
            for (int i = 3; i <= n; i ++) {
                a[i] = (a[i - 1] + a[i - 2]) * (i - 1);
                a[i] = a[i] % mod;
            }
            return (int)a[n];
        }
    }
}
