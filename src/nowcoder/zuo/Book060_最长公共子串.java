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
	
	/**
	 * @author      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book060_最长公共子串.java
	 * @type        Solution2
	 * @date        2016年12月10日 上午10:33:25
	 * @details     根据dp[i1][i2]只和dp[i1-1][i2-1]相关
	 * @details     可以将空间优化到O(1)
	 */
	static class Solution2 {
		public String longestCommonSubstring(String s1, String s2) {
			int s = 0, t = 0, n1 = s1.length(), n2 = s2.length();
			int i1 = 0, i2 = 0, nm = 0;
			for (int k = 0; k <= n1+n2; k ++) {
				i1 = k > n1 ? 0 : n1 - k;
				i2 = k > n1 ? k - n1 : 0;
				nm = 0;
				while (true) {
					i1 ++; i2 ++;
					if (i1 > n1 || i2 > n2)  break;
					if (s1.charAt(i1-1) == s2.charAt(i2-1)) {
						nm++;
						if (nm > t-s) {
							s = i1 - nm;
							t = i1;
						}
					} else {
						nm = 0;
					}
				}
			}
			return s1.substring(s, t);
		}
	}
	
}
