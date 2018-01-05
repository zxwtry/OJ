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

	static class Solution1 {
	    public boolean isMatch(String s, String p) {
	        int sn = s == null ? 0 : s.length();
	        int pn = p == null ? 0 : p.length();
	        if (sn == 0 || pn == 0) {
	            return sn == 0 && pn == 0;
	        }
	        boolean[][] dp = new boolean[sn + 1][pn + 1];
	        /**
	         * si == 0情况：
	         * 应该pi == 0时候，为true
	         * pi >= 1的时候，如果p[pi] == '*'，且dp[0][pi - 1] == true
	         */
	        int si = 0, pi = 0;
	        dp[0][0] = true;
	        for (si = -1, pi = 0; pi < pn; pi ++) {
	            if (dp[si + 1][pi - 1 + 1] && p.charAt(pi) == '*') {
	                dp[si + 1][pi + 1] = true;
	            } else {
	                break;
	            }
	        }
	        for (si = 0; si < sn; si ++) {
	            char sc = s.charAt(si);
	            for (pi = 0; pi < pn; pi ++) {
	                char pc = p.charAt(pi);
	                if (pc == '*') {
	                    //匹配0次
	                    if (! dp[si + 1][pi + 1]) {
	                        //匹配[0, si] 和 [0, pi - 1]
	                        dp[si + 1][pi + 1] = dp[si + 1][pi - 1 + 1];
	                    }
	                    //匹配1次
	                    if (! dp[si + 1][pi + 1]) {
	                        //匹配[0, si-1] 和[0, pi-1]
	                        dp[si + 1][pi + 1] = dp[si - 1 + 1][pi - 1 + 1];
	                    }
	                    //匹配多次
	                    if (! dp[si + 1][pi + 1]) {
	                        //匹配[0, si-1] 和 [0, pi]
	                        dp[si + 1][pi + 1] = dp[si - 1 + 1][pi + 1];
	                    }
	                } else if (pc == '?') {
	                    //匹配[0, si-1] 和[0, pi-1]
                        dp[si + 1][pi + 1] = dp[si - 1 + 1][pi - 1 + 1];
	                } else {
	                    if (sc == pc) {
	                        //匹配[0, si-1] 和[0, pi-1]
	                        dp[si + 1][pi + 1] = dp[si - 1 + 1][pi - 1 + 1];
	                    } else {
	                        dp[si + 1][pi + 1] = false;
	                    }
	                }
	            }
	        }
	        return dp[sn][pn];
	    }
	}
	
	/*
	 * 	35 ms
	 * 	54.88%
	 */
	static class Solution2 {
		public boolean isMatch(String s, String p) {
			if (p == null || p.length() == 0)
				return s.length() == 0;
			boolean[] res = new boolean[s.length() + 1];
			res[0] = true;
			for (int j = 0; j < p.length(); j++) {
				if (p.charAt(j) != '*') {
					for (int i = s.length() - 1; i >= 0; i--) {
						res[i + 1] = res[i] && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j));
					}
				} else {
					int i = 0;
					while (i <= s.length() && !res[i])
						i++;
					for (; i <= s.length(); i++) {
						res[i] = true;
					}
				}
				res[0] = res[0] && p.charAt(j) == '*';
			}
			return res[s.length()];
		}
	}
}
