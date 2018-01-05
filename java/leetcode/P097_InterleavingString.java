package leetcode;

/*
 * 	Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

	For example,
	Given:
	s1 = "aabcc",
	s2 = "dbbca",
	
	When s3 = "aadbbcbcac", return true.
	When s3 = "aadbbbaccc", return false.
 */

public class P097_InterleavingString {
	public static void main(String[] args) {
		System.out.println(new Solution2().isInterleave("aabcc", "dbbca", "aadbbcbcac"));
		System.out.println(new Solution2().isInterleave("aabcc", "dbbca", "aadbbbaccc"));
		System.out.println(new Solution2().isInterleave(
				"aaaaaaaaaaaaaaaaaaa", 
				"bbbbbbbbbbbbbbbbbbb", 
				"aaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbb"));
		System.out.println(new Solution2().isInterleave(
				"bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa", 
				"babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab", 
				"babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab"));
	}
	/*
	 * 	已有的测试数据都通过，提交TLE
	 */
	static class Solution {
		int count = 0;
	    public boolean isInterleave(String s1, String s2, String s3) {
	    	if (s3 == null) {
	    		return null == s2 && s1 == null;
	    	}
	    	if (s1 == null || s1.length() == 0) {
	    		return s3.equals(s2);
	    	} else if (s2 == null || s2.length() == 0) {
	    		return s3.equals(s1);
	    	}
	    	if (s1.length() + s2.length() != s3.length()) {
	    		return false;
	    	}
	    	boolean ans = solve(s1.toCharArray(), 0, s2.toCharArray(), 0, s3.toCharArray(), 0);
	        System.out.println(count);
	    	return ans;
	    }
		private boolean solve(char[] ch1, int i1, char[] ch2, int i2, char[] ch3, int i3) {
			count ++;
			if (i1 == ch1.length && i3 < ch3.length) {
				return solve(ch2, i2, ch3, i3);
			} else if (i2 == ch2.length && i3 < ch3.length) {
				return solve(ch1, i1, ch3, i3);
			} else if (i3 == ch3.length) {
				return i1 == ch1.length && i2 == ch2.length;
			}
			if (ch1[i1] == ch2[i2]) {
				if (ch1[i1] != ch3[i3]) {
					return false;
				} else {
					return solve(ch1, i1 + 1, ch2, i2, ch3, i3 + 1) ||
							solve(ch1, i1, ch2, i2 + 1, ch3, i3 + 1);
				}
			}
			if (ch1[i1] == ch3[i3]) {
				return solve(ch1, i1 + 1, ch2, i2, ch3, i3 + 1);
			}
			if (ch2[i2] == ch3[i3]) {
				return solve(ch1, i1, ch2, i2 + 1, ch3, i3 + 1);
			}
			return false;
		}
		private boolean solve(char[] ch1, int i1, char[] ch2, int i2) {
			int j1 = i1, j2 = i2;
			while (j1 < ch1.length && j2 < ch2.length) {
				if (ch1[j1 ++] != ch2[j2 ++]) {
					return false;
				}
			}
			return j1 == ch1.length && j2 == ch2.length;
		}
	}
	/*	
	 *	对于这类问题，肯定要尝试DP
	 *	12 ms
	 */
	static class Solution2 {
		public boolean isInterleave(String s1, String s2, String s3) {
			if (s3 == null) {
	    		return null == s2 && s1 == null;
	    	}
	    	if (s1 == null || s1.length() == 0) {
	    		return s3.equals(s2);
	    	} else if (s2 == null || s2.length() == 0) {
	    		return s3.equals(s1);
	    	}
	    	int m = s1.length(), n = s2.length();
	    	if (m + n != s3.length()) {
	    		return false;
	    	}
	    	boolean[][] path = new boolean[m + 1][n + 1];
	    	path[0][0] = true;
	    	for (int i = 1; i < m + 1; i ++) {
	    		path[i][0] = path[i - 1][0] & (s1.charAt(i - 1) == s3.charAt(i - 1));
	    	}
	    	for (int j = 1; j < n + 1; j ++) {
	    		path[0][j] = path[0][j - 1] & (s2.charAt(j - 1) == s3.charAt(j - 1));
	    	}
	    	for (int i = 1; i < m + 1; i ++) {
	    		for (int j = 1; j < n + 1; j ++) {
	    			path[i][j] = (path[i - 1][j] & (s1.charAt(i - 1) == s3.charAt(i + j - 1))) ||
	    					(path[i][j - 1] & (s2.charAt(j - 1) == s3.charAt(i + j - 1)));
	    		}
	    	}
	    	return path[m][n];
		}
	}
}
