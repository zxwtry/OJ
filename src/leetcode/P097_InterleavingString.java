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
		System.out.println(new Solution().isInterleave("aabcc", "dbbca", "aadbbcbcac"));
		System.out.println(new Solution().isInterleave("aabcc", "dbbca", "aadbbbaccc"));
		System.out.println(new Solution().isInterleave(
				"a", 
				"a", 
				"ab"));
	}
	/*
	 * 	已有的测试数据都通过，提交TLE
	 */
	static class Solution {
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
	        return solve(s1.toCharArray(), 0, s2.toCharArray(), 0, s3.toCharArray(), 0);
	    }
		private boolean solve(char[] ch1, int i1, char[] ch2, int i2, char[] ch3, int i3) {
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
}
