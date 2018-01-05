package leetcode;

/**
    Given two words word1 and word2, find the minimum number of steps required to 
    make word1 and word2 the same, where in each step you can delete one character in either string.
    
    Example 1:
    Input: "sea", "eat"
    Output: 2
    Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
    Note:
    The length of given words won't exceed 500.
    Characters in given words can only be lower-case letters.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P583_DeleteOperationForTwoStrings.java
 * @type        P583_DeleteOperationForTwoStrings
 * @date        2017年5月14日 上午10:14:54
 * @details     Solution: AC 76ms 23.53%
 */
public class P583_DeleteOperationForTwoStrings {
    
    static public class Solution {
        public int minDistance(String s1, String s2) {
            int n1 = s1 == null ? 0 : s1.length();
            int n2 = s2 == null ? 0 : s2.length();
            if (n1 == 0 || n2 == 0) return n1+n2;
            int delete = 1;
            int insert = 1;
            int replace = 99999;
            return editDistance(s1.toCharArray(), s2.toCharArray(), delete, insert, replace);
        }
        public int editDistance(char[] c1, char[] c2, int delete, int insert, int replace) {
            int n1 = c1 == null ? 0 : c1.length;
            int n2 = c2 == null ? 0 : c2.length;
            if (n1 < n2) return editDistance(c2, c1, delete, insert, replace);
            int[][] d = new int[2][n2+1];
            int INF = Integer.MAX_VALUE / 3;
            for (int i1 = 0; i1 <= n1; i1 ++) {
                for (int i2 = 0; i2 <= n2; i2 ++) {
                    if (i1 == 0 && i2 == 0) continue;
                    int min = INF;
                    if (i1-1 >= 0) min = Math.min(min, d[0][i2] + delete);
                    if (i2-1 >= 0) min = Math.min(min, d[1][i2-1] + insert);
                    if (i1-1 >= 0 && i2-1 >= 0) min = Math.min(min, 
                            d[0][i2-1] + (c1[i1-1] == c2[i2-1] ? 0 : replace));
                    d[1][i2] = min;
                }
                swap(d);
            }
            return d[0][n2];
        }
        public void swap(int[][] d) {
            int[] t = d[0];
            d[0] = d[1];
            d[1] = t;
        }
    }
}
