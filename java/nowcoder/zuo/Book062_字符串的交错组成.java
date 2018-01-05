package nowcoder.zuo;

/**
 * 	给定三个字符串s1  s2  和  aim，如果aim包含且仅包含来自s1和s2的所有字符，
 * 	而且在aim中属于s1的字符之间保持原来在s1中的顺序，属于s2的字符之间保持原来在s2中的顺序，
 * 	那么称aim是s1和s2的交错组成。实现一函数，判断aim是否是s1和s2交错组成。
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book062_字符串的交错组成.java
 * @type        Book062_字符串的交错组成
 * @date        2016年12月14日 下午9:46:42
 * @details     
 */
public class Book062_字符串的交错组成 {
	static class Solution1 {
		public boolean isCross(String s1, String s2, String aim) {
			if (s1 == null || s2 == null || aim == null) return false;
			char[] c1 = s1.toCharArray();
			char[] c2 = s2.toCharArray();
			char[] cm = aim.toCharArray();
			if (cm.length != c1.length + c2.length) return false;
			boolean[][] dp = new boolean[c1.length + 1][c2.length + 1];
			dp[0][0] = true;
			for (int i = 1; i <= c1.length; i ++) {
				if (c1[i - 1] != cm[i - 1]) break;
				dp[i][0] = true;
			}
			for (int j = 1; j <= c2.length; j ++) {
				if (c1[j - 1] != cm[j - 1]) break;
				dp[0][j] = true;
			}
			for (int i = 1; i <= c1.length; i ++)
				for (int j = 1; j <= c2.length; j ++)
					if ((c1[i - 1] == cm[i + j - 1] && dp[i - 1][j]) ||
							(c2[j - 1] == cm[i + j - 1] && dp[i][j - 1]))
						dp[i][j] = true;
			return dp[c1.length][c2.length];
		}
	}
	static class Solution2 {
		public boolean isCross(String s1, String s2, String aim) {
			if (s1 == null || s2 == null || aim == null) return false;
			char[] c1 = s1.toCharArray();
			char[] c2 = s2.toCharArray();
			char[] cm = aim.toCharArray();
			if (cm.length != c1.length + c2.length) return false;
			char[] longs = c1.length >= c2.length ? c1 : c2;
			char[] shorts = c1.length < c2.length ? c1 : c2;
			boolean[] dp = new boolean[shorts.length + 1];
			dp[0] = true;
			for (int i = 1; i <= shorts.length; i ++) {
				if (shorts[i - 1] != cm[i - 1]) break;
				dp[i] = true;
			}
			for (int i = 1; i <= longs.length; i ++) {
				dp[0] = dp[0] && longs[i - 1] == cm[i - 1];
				for (int j = 1; j <= shorts.length; j ++) {
					if ((longs[i - 1] == cm[i + j - 1] && dp[j]) ||
							shorts[j - 1] == cm[i + j - 1] && dp[j - 1])
						dp[j] = true;
					else 
						dp[j] = false;
				}
			}
			return dp[shorts.length];
		}
	}
}
