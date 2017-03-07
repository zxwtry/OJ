package leetcode;

/**
 * 	Given two words word1 and word2, find the minimum number of 
 * 	steps required to convert word1 to word2. (each operation is counted as 1 step.)
 *  
 *  You have the following 3 operations permitted on a word:
 *  
 *  a) Insert a character
 *  b) Delete a character
 *  c) Replace a character
 */


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P072_EditDistance.java
 * @type        P072_EditDistance
 * @date        2017年3月7日 下午2:45:17
 * @details     Solution1: AC 16ms 35.42%
 * @details     Solution2: AC 12ms 77.30%
 * @details     Solution3: AC 17ms 24.20%
 */
public class P072_EditDistance {
	static class Solution1 {
	    public int minDistance(String word1, String word2) {
	        if (word1 == null || word2 == null) 
	            return minDistance(word1 == null ? "" : word1, word2 == null ? "" : word2);
	        if (word1.length() < word2.length())
	            return minDistance(word2, word1);
	        int[][] dp = new int[2][word2.length() + 1];
	        for (int j = 0; j <= word2.length(); j ++)
	            dp[0][j] = j;
	        for (int i = 1; i <= word1.length(); i ++) {
	            dp[1][0] = i; 
	            for (int j = 1; j <= word2.length(); j ++) {
	                dp[1][j] = Math.min(Math.min(dp[0][j] + 1, dp[1][j - 1] + 1), 
	                        dp[0][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1));
	            }
	            System.arraycopy(dp[1], 0, dp[0], 0, dp[0].length);
	        }
	        return dp[1][word2.length()];
	    }
	}
	static class Solution2 {
		public int minDistance(String word1, String word2) {
			if (word1 == null)	return minDistance("", word2);
			if (word2 == null)	return minDistance("", word1);
			int len1 = word1.length(), len2 = word2.length();
			if (len1 == 0 || len2 == 0)	return len1 + len2;
			int[][] m = new int[len1 + 1][len2 + 1];
			for (int i = 0; i <= len1; i ++)
				m[i][0] = i;
			for (int j = 1; j <= len2; j ++)
				m[0][j] = j;
			for (int i = 1; i <= len1; i ++)
				for (int j = 1; j <= len2; j ++) {
					m[i][j] = Math.min(Math.min(m[i - 1][j] + 1, m[i][j - 1] + 1), 
	        				m[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ?  0 : 1));
				}
			return m[len1][len2];
		}
	}
}
