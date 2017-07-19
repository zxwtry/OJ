package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        其它_最长公共子序列.java
 * @date        2017年7月19日 下午10:11:58
 * @details     
 */
public class 其它_最长公共子序列 {
    //"cacccca",7,"aaacca",6
    public static void main(String[] args) {
        String A = "cacccca";
        String B = "aaacca";
        int n = A.length();
        int m = B.length();
        System.out.println(new LongestSubstring().findLongest(A, n, B, m));
    }
    static public class LongestSubstring {
        public int findLongest(String A, int n, String B, int m) {
            int[][] dp = new int[n + 1][m + 1];
            int max = 0;
            for (int i = 1; i <= n; i ++) {
                char a = A.charAt(i - 1);
                for (int j = 1; j <= m; j ++) {
                    char b = B.charAt(j - 1);
                    if (a == b) {
                        dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, Math.max(dp[i - 1][j], dp[i][j - 1]) + 1);
                        max = Math.max(max, dp[i][j]);
                    } else {
                        dp[i][j] = 0;
                    }
                }
            }
            tools.Utils.A_打印二维数组(dp);
            return max;
        }
    }
}
