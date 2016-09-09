package leetcode;

/*
 * 	Given two words word1 and word2, find the minimum number of 
 * 	steps required to convert word1 to word2. (each operation is counted as 1 step.)

	You have the following 3 operations permitted on a word:
	
	a) Insert a character
	b) Delete a character
	c) Replace a character
 */


public class P072_EditDistance {
	public static void main(String[] args) {
		System.out.println(new Solution2().minDistance("pneumonoultramicroscopicsilicovolcanoconiosis",
				"ultramicroscopically"));
	}
	/*
	 * 	碰到上面用例的问题，答案是27，而我的是26诶
	 * 	这个算法是错误的，不能这么做。
	 */
	static class Solution {
	    public int minDistance(String word1, String word2) {
	    	if (word1 == null)	return minDistance("", word2);
	    	if (word2 == null)	return minDistance("", word1);
	    	int len1 = word1.length(), len2 = word2.length();
	    	if (len1 == 0 || len2 == 0)	return len1 + len2;
	    	char[] c1 = word1.toCharArray(), c2 = word2.toCharArray();
	    	int[][] m = new int[len1][len2];
	    	m[0][0] = c1[0] == c2[0] ? 0 : 1;
	    	for (int i = 1; i != len1; i ++)
	    		m[i][0] = m[i - 1][0] + (c1[i] == c2[0] ? 0 : 1);
	        for (int j = 1; j != len2; j ++)
	        	m[0][j] = m[0][j - 1] + (c1[0] == c2[j] ? 0 : 1);
	        for (int i = 1; i != len1; i ++)
	        	for (int j = 1; j != len2; j ++)
	        		m[i][j] = Math.min(Math.min(m[i - 1][j] + 1, m[i][j - 1] + 1), 
	        				m[i - 1][j - 1] + (c1[i] == c2[j] ?  0 : 1));
	        return m[len1 - 1][len2 -1];
	    }
	}
	/*
	 * 	AC
	 * 	16 ms
	 */
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
