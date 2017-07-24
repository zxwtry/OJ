package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**

You are given n pairs of numbers. In every pair, the first number
 is always smaller than the second number.

Now, we define a pair (c, d) can follow another pair (a, b) if 
and only if b < c. Chain of pairs can be formed in this fashion.

Given a set of pairs, find the length longest chain which can be formed. 
You needn't use up all the given pairs. You can select pairs in any order.

Example 1:
Input: [[1,2], [2,3], [3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4]
Note:
The number of given pairs will be in the range [1, 1000].

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P646_MaximumLengthOfPairChain.java
 * @date        2017年7月23日 上午9:42:03
 * @details     
 */
public class P646_MaximumLengthOfPairChain {
    public static void main(String[] args) {
        //[[-10,-8],[8,9],[-5,0],[6,10],[-6,-4],[1,7],[9,10],[-4,7]]
        int[][] ps =  {{-10,-8},{8,9},{-5,0},{6,10},{-6,-4},{1,7},{9,10},{-4,7}};
        System.out.println(new Solution().findLongestChain(ps));
    }
    static public class Solution {
        public int findLongestChain(final int[][] ps) {
            int pn = ps == null ? 0 : ps.length;
            if (pn == 0) return 0;
            Arrays.sort(ps, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    int c = Integer.compare(o1[0], o2[0]);
                    if (c != 0) return c;
                    c = Integer.compare(o1[1], o2[1]);
                    return c;
                }
            });
            int[] dp = new int[pn];
            dp[0] = 1;
            int max = dp[0];
            for (int i = 1; i < pn; i ++) {
                dp[i] = 1;
                for (int j = 0; j < i; j ++) {
                    if (ps[j][1] < ps[i][0]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                max = Math.max(max, dp[i]);
            }
            return max;
        }
    }
}
