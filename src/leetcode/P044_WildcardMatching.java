package leetcode;

/*
 * 	Implement wildcard pattern matching with support for '?' and '*'.
	'?' Matches any single character.
	'*' Matches any sequence of characters (including the empty sequence).
	The matching should cover the entire input string (not partial).
	The function prototype should be:
	bool isMatch(const char *s, const char *p)
	Some examples:
	isMatch("aa","a") → false
	isMatch("aa","aa") → true
	isMatch("aaa","aa") → false
	isMatch("aa", "*") → true
	isMatch("aa", "a*") → true
	isMatch("ab", "?*") → true
	isMatch("aab", "c*a*b") → false
	Subscribe to see which companies asked this question
 */

public class P044_WildcardMatching {
	public static void main(String[] args) {
//		System.out.println(new Solution().isMatch("ab", "?*"));
//		System.out.println(new Solution().isMatch("aab", "c*a*b"));
		System.out.println(new Solution().isMatch("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba", "a*******b"));
	}
	/*
	 * 	还是没有吃透10题
	 * 	这题TLE
	 */
	static class Solution {
		public boolean isMatch(String s, String p) {
			if (s == null)
				return p == null;
			if (p == null)
				return s == null;
			StringBuilder st = new StringBuilder(p);
			for (int i = p.length() - 1; i > -1; i --) {
				if (st.charAt(i) == '*')
					st.insert(i, '?');
			}
			p = st.toString();
			char[] cs = new char[s.length() + 1], cp = new char[p.length() + 1];
			cs[cs.length - 1] = '\0';
			cp[cp.length - 1] = '\0';
			System.arraycopy(s.toCharArray(), 0, cs, 0, cs.length - 1);
			System.arraycopy(p.toCharArray(), 0, cp, 0, cp.length - 1);
			return isMatch(cs, 0, cs.length, cp, 0, cp.length);
		}
		private boolean isMatch(char[] s, int i, int I, char[] p, int j, int J) {
			if (p[j] == '\0')	return s[i] == '\0';
			if (p[j + 1] == '*') {
				while (s[i] == p[j] || (p[j] == '?' && s[i] != '\0')) {
					if (isMatch(s, i ++, I, p, j + 2, J)) {
						return true;
					}
				}
				return isMatch(s, i, I, p, j + 2, J);
			} else {
				if (s[i] == p[j] || (p[j] == '?' && s[i] != '\0'))
					return isMatch(s, i + 1, I, p, j + 1, J);
				return false;
			}
		}
	}
}
