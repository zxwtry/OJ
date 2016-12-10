package nowcoder.zuo;

/**
 * 	给两个字符串，s1和s2
 * 	返回最长公共子串
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book060_最长公共子串.java
 * @type        Book060_最长公共子串
 * @date        2016年12月10日 上午10:15:17
 * @details     test case 1: s1 "1AB2345CD" s2 "12345EF"
 */
public class Book060_最长公共子串 {
	
	public static void main(String[] args) {
	}
	
	/**
	 * @author      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book060_最长公共子串.java
	 * @type        Solution1
	 * @date        2016年12月10日 上午10:17:30
	 * @details     普通DP 时间O(M*N) 空间O(M*N)
	 */
	static class Solution1 {
		public String longestCommonSubstring(String s1, String s2) {
			int[][] dp = getDP(s1, s2);
			int s = 0, t = 0;
			for (int i1 = 1; i1 <= s1.length(); i1 ++) {
				for (int i2 = 1; i2 <= s2.length(); i2 ++) {
					if (dp[i1][i2] > (t-s)) {
						t = i1;
						s = i1 - dp[i1][i2];
					}
				}
			}
			return s1.substring(s, t);
 		}
		private int[][] getDP(String s1, String s2) {
			int[][] dp = new int[s1.length() + 1][s2.length() + 1];
			for (int i1 = 1; i1 <= s1.length(); i1 ++)
				for (int i2 = 1; i2 <= s2.length(); i2 ++)
					if (s1.charAt(i1-1) == s2.charAt(i2-1))
						dp[i1][i2] = dp[i1-1][i2-1] + 1;
			return dp;
		}
	}
	
}
