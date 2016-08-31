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
//		System.out.println(new Solution3().isMatch("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba", "a*******b"));
//		System.out.println(new Solution3().isMatch("abcba", "ab*ba"));
//		System.out.println(new Solution3().isMatch("aa", "*"));
//		System.out.println(new Solution3().isMatch("a", "a*"));
//		System.out.println(new Solution3().isMatch("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba", "a*******b"));
//		System.out.println(new Solution3().isMatch("abcba", "ab*ba"));
//		System.out.println(new Solution3().isMatch("aa", "*"));
//		System.out.println(new Solution3().isMatch("a", "a*"));
//		System.out.println(new Solution3().isMatch("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba", "a*******a"));
		System.out.println(new Solution3().isMatch("aaabbba", "a***dfdfdfdfdfdf****a"));
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
	/*
	 * 	自己按照自己的理解去写
	 * 	还是会TLE
	 */
	static class Solution2 {
		public boolean isMatch(String s, String p) {
			if (s == null || p == null)
				return false;
			if (s.length() == 0 && (p.length() == 0 || p.equals("*")))
				return true;
			return isMatch(s.toCharArray(), 0, p.toCharArray(), 0);
		}
		public boolean isMatch(char[] s, int si, char[] p, int pi) {
			if (si >= s.length || pi >= p.length) {
				boolean isAllStar = true;
				for (int i = pi; i != p.length; i ++)
					isAllStar &= '*' == p[i];
				if (isAllStar)
					return si == s.length;
				return s.length  - si == 0 && p.length - pi == 0;
			}
			if (p[pi] == '?') {
				return isMatch(s, si + 1, p, pi + 1);
			} else if (p[pi] == '*') {
				boolean matchSince = false;
				for (int i = si; i <= s.length; i ++)
					matchSince |= isMatch(s, i, p, pi + 1);
				return matchSince;
			} else {
				if (s[si] == p[pi])
					return isMatch(s,  si + 1, p, pi + 1);
				else
					return false;
			}
		}
	}
	/*
	 *	处理*的方法太差了，肯定有更加好的方法
	 *	其实可以先预先处理非*的匹配 
	 */
	static class Solution3 {
		private boolean isTwoStar = false;
		public boolean isMatch(String s, String p) {
			if (s == null || p == null)
				return false;
			if (s.length() == 0 && (p.length() == 0 || p.equals("*")))
				return true;
			return isMatchBackward(s.toCharArray(), 0, s.length() - 1, p.toCharArray(), 0, p.length() - 1);
		}
		public boolean isMatchForward(char[] s, int si, int se, char[] p, int pi, int pe) {
			if (si > se || pi > pe) {
				boolean isAllStar = true;
				for (int i = pi; i != pe + 1; i ++)
					isAllStar &= '*' == p[i];
				if (isAllStar)
					return 1 == si - se ;
				return 1 == si - se && 1 == pi - pe;
			}
			if (p[pi] == '?') {
				isTwoStar = false;
				return isMatchForward(s, si + 1, se, p, pi + 1, pe);
			} else if (p[pi] == '*') {
				/*
				 * 	这里的复杂度太高了
				 */
				if (isTwoStar)
					return isMatchTwoStar(s, si, se, p, pi, pe);
				isTwoStar = true;
				return isMatchBackward(s, si, se, p, pi, pe);
//				boolean matchSince = false;
//				for (int i = si; i <= se + 1; i ++)
//					matchSince |= isMatchForward(s, i, se, p, pi + 1, pe);
//				return matchSince;
			} else {
				isTwoStar = false;
				if (s[si] == p[pi])
					return isMatchForward(s,  si + 1, se, p, pi + 1, pe);
				else
					return false;
			}
		}
		public boolean isMatchBackward(char[] s, int si, int se, char[] p, int pi, int pe) {
			if (si > se || pi > pe) {
				boolean isAllStar = true;
				for (int i = pe; i != -1; i --)
					isAllStar &= '*' == p[i];
				if (isAllStar)
					return 1 == si - se ;
				return 1 == si - se && 1 == pi - pe;
			}
			if (p[pe] == '?') {
				isTwoStar = false;
				return isMatchBackward(s, si, se - 1, p, pi, pe - 1);
			} else if (p[pe] == '*') {
				if (isTwoStar)
					return isMatchTwoStar(s, si, se, p, pi, pe);
				isTwoStar = true;
				return isMatchForward(s, si, se, p, pi, pe);
//				boolean matchSince = false;
//				for (int i = se; i >= si -1; i --)
//					matchSince |= isMatchBackward(s, si, i, p, pi, pe - 1);
//				return matchSince;
			} else {
				isTwoStar = false;
				if (s[se] == p[pe])
					return isMatchBackward(s, si, se - 1, p, pi, pe - 1);
				else
					return false;
			}
		}
		private boolean isMatchTwoStar(char[] s, int si, int se, char[] p, int pi, int pe) {
			int countNotQNotS = 0;
			for (int i = pi; i <= pe; i ++)
				countNotQNotS += (p[i] == '?' || p[i] == '*') ? 0 : 1;
			if (countNotQNotS == 0)
				return true;
			else if (countNotQNotS > se - si + 1)
				return false;
			/*
			 * 	这里的逻辑过于复杂，放弃
			 */
			return false;
		}
	}
}
