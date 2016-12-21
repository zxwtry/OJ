package nowcoder.zuo;

/**
 * 	给定一个字符串s，判断是不是整体有效的括号字符串
 * 	举例：
 * 		s="()"，返回true
 * 		s="(()())"，返回true
 * 		s="(())"，返回true
 * 		s="())"，返回false
 * 		s="(()"，返回false
 * 		s="()a()"，返回false
 * 	补充问题：
 * 		给定一个括号字符串s，返回最长的有效括号子串
 * 		s="(()())"，返回6
 * 		s="())"，返回2
 * 		s="()(()()("，返回4
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book077_括号字符串的有效性和最长有效长度.java
 * @type        Book077_括号字符串的有效性和最长有效长度
 * @date        2016年12月21日 上午9:50:51
 * @details     
 */
public class Book078_括号字符串的有效性和最长有效长度 {
	static class Solution1 {
		final char l = '(', r = ')';
		public boolean isValid(String s) {
			if (s == null || s.length() == 0) return false;
			int n = 0;
			char c = 0;
			for (int i = 0; i < s.length(); i ++) {
				c = s.charAt(i);
				if (c != r && c != l) return false; 
				if (c == r && -- n < 0) return false;
				if (c == l) ++ n;
			}
			return n == 0;
		}
	}
	static class Solution2 {
		final char l = '(', r = ')';
		public int maxLength(String s) {
			if (s == null || s.length() == 0) return 0;
			int[] dp = new int[s.length()];
			int pre = 0, ans = 0;
			for (int i = 1; i < s.length(); i ++) {
				if (s.charAt(i) == r) {
					pre = i - dp[i-1] - 1;
					if (pre >= 0 && s.charAt(pre) == l)
						dp[i] = dp[i-1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
				}
				ans = Math.max(ans, dp[i]);
			}
			return ans;
		}
	}
}
