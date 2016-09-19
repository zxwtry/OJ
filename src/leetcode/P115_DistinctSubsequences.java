package leetcode;


/*
 * 	Given a string S and a string T, count the number of distinct subsequences of
 *  T in S.

	A subsequence of a string is a new string which is formed from the original
	 string by deleting some (can be none) of the characters without disturbing
	  the relative positions of the remaining characters. (ie, "ACE" is a 
	  subsequence of "ABCDE" while "AEC" is not).
	
	Here is an example:
	S = "rabbbit", T = "rabbit"
	
	Return 3.
 */


public class P115_DistinctSubsequences {
	public static void main(String[] args) {
		Solution2 solution = new Solution2();
		String s = null, t = null;
		s = "";	t = "";
		System.out.println(solution.numDistinct(s, t));
		s = "abbc";	t = "ab";
		System.out.println(solution.numDistinct(s, t));
		s = "rabbbit";	t = "rabbit";
		System.out.println(solution.numDistinct(s, t));
		s = "aaaaaaa";	t = "aaa";
		System.out.println(solution.numDistinct(s, t));
		s = "aaaaaaaq";	t = "aaaq";
		System.out.println(solution.numDistinct(s, t));
		s = "aaq";	t = "aaaaaaaaq";
		System.out.println(solution.numDistinct(s, t));
	}
	/*
	 * 	递归是为DP做准备
	 */
	static class Solution1 {
		char[] sc = null, tc = null;
		public int numDistinct(String s, String t) {
			if (s == null || t == null) {
				return t == null ? 1 : 0;
			}
			sc = s.toCharArray();
			tc = t.toCharArray();
			return numDistinct(0,  sc.length - 1, 0, tc.length - 1);
		}
		private int numDistinct(int i, int j, int k, int l) {
			if (j - i < l - k) {
				return 0;
			}
			if (k > l) {
				return 1;
			}
			if (sc[i] != tc[k]) {
				return numDistinct(i + 1, j, k, l);
			} else {
				return numDistinct(i + 1, j, k, l) + numDistinct(i + 1, j, k + 1, l);
			}
		}
	}
	/*
	 * 	这种问题毫无疑问第一方法DP
	 * 	AC
	 * 	15 ms
	 * 	对于这种问题，最好先写出递归版本，对应递归版本，写出dp版本
	 */
	static class Solution2 {
		int[][] dp = null;
		int len1, len2;
	    public int numDistinct(String s, String t) {
	    	if (s == null || t == null) {
				return t == null ? 1 : 0;
			}
	    	len1 = s.length();
	    	len2 = t.length(); 
	    	if (len1 < len2) {
	    		return 0;
	    	}
	    	if (len2 == 0) {
	    		return 1;
	    	}
	    	dp = new int[len1 + 1][len2 + 1];
	    	for (int i = 0; i <= len1; i ++) {
	    		dp[i][len2] = 1;
	    	}
	    	for (int i = len1 - 1; i > -1; i --) {
	    		for (int j = len2 - 1; j > -1; j --) {
	    			dp[i][j] = dp[i + 1][j] +(s.charAt(i) == t.charAt(j) ? dp[i + 1][j + 1] : 0);
	    		}
	    	}
	        return dp[0][0];
	    }
	}
}
