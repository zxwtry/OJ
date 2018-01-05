package leetcode;

import java.util.Arrays;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P639_DecodeWaysII.java
 * @date        2017年7月9日 下午9:59:34
 * @details     Solution AC
 */
public class P639_DecodeWaysII {
    static class Solution {
        final int mod = 1000000007;
        public int numDecodings(String s) {
            int sn = s == null ? 0 : s.length();
            long[][] dp = new long[2][3];
            dp[1][0] = 1;
            for (int i = 0; i < sn; i ++) {
                char c = s.charAt(i);
                swap(dp);
                long[] dp0 = dp[0];
                long[] dp1 = dp[1];
                Arrays.fill(dp1, 0);
                if (c == '*') {
                    dp1[0]  = dp0[0] * 9;
                    dp1[0] += dp0[1] * 9;
                    dp1[0] += dp0[2] * 6;
                    dp1[1]  = dp0[0];
                    dp1[2]  = dp0[0];
                } else {
                    if (c != '0') dp1[0] = dp0[0];
                    if (c == '1') dp1[1] = dp0[0];
                    if (c == '2') dp1[2] = dp0[0];
                    dp1[0] += dp0[1];
                    if (c <= '6') dp1[0] += dp0[2];
                }
                dp1[0] = dp1[0] % mod;
                dp1[1] = dp1[1] % mod;
                dp1[2] = dp1[2] % mod;
            }
            return (int)dp[1][0];
        }
        void swap(long[][] dp) {
            long[] t = dp[0];
            dp[0] = dp[1];
            dp[1] = t;
        }
    }
}
