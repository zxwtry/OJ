package leetcode;


/**
 *  There is a strange printer with the following two special requirements:

The printer can only print a sequence of the same character each time.
At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.
Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.

Example 1:
Input: "aaabbb"
Output: 2
Explanation: Print "aaa" first and then print "bbb".
Example 2:
Input: "aba"
Output: 2
Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
Hint: Length of the given string will not exceed 100.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P664_StrangePrinter.java
 * @date        2017年8月20日 上午10:03:55
 * @details     
 */
public class P664_StrangePrinter {
    
    public static void main(String[] args) {
        String s = "abcdabcdabcdabcd";
        System.out.println(new Solution().strangePrinter(s));
    }
    static class Solution {
        char base = 'a';
        final int N = 150;
        int sn;
        int[] v = new int[N];
        int[][][] dp = new int[N][N][N];
        public int strangePrinter(String s) {
            sn = s == null ? 0 : s.length();
            if (sn < 1) {
                return 0;
            }
            for (int i = 0; i < sn; i ++) {
                v[i] = s.charAt(i);
            }
            for (int i = 0; i <= sn; i ++) {
                for (int j = 0; j <= sn; j ++) {
                    for (int k = 0; k <= sn; k ++) {
                        dp[i][j][k] = -1;
                    }
                }
            }
            return f(0, sn - 1, 0);
        }
        private int f(int i, int j, int k) {
            if (i > j) {
                return 0;
            }
            int ans = dp[i][j][k];
            if (ans >= 0) {
                return ans;
            }
            ans = f(i, j-1, 0) + 1;
            for (int t = i; t < j; t ++) {
                if (v[t] == v[j]) {
                    ans = Math.min(ans, 
                            f(i, t, k+1) + f(t+1, j-1, 0));
                }
            }
            return ans;
        }
    }
}
