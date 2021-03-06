package leetcode;

/**
 * 	Given a non-empty string containing an out-of-order English representation of digits 0-9, 
 * 	output the digits in ascending order.
 *	
 *	Note:
 *	Input contains only lowercase English letters.
 *	Input is guaranteed to be valid and can be transformed to its original digits. 
 *	That means invalid inputs such as "abc" or "zerone" are not permitted.
 *	Input length is less than 50,000.
 *	Example 1:
 *	Input: "owoztneoer"
 *	
 *	Output: "012"
 *	Example 2:
 *	Input: "fviefuro"
 *	
 *	Output: "45"
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P423_ReconstructOriginalDigitsfromEnglish.java
 * @type        P423_ReconstructOriginalDigitsfromEnglish
 * @date        2017年3月9日 下午9:32:39
 * @details     Solution: AC 23ms 63.30%
 */
public class P423_ReconstructOriginalDigitsfromEnglish {
	static class Solution {
		/*
		 * 	z : zero : 0
		 * 	w : two : 2
		 * 	u : four : 4 
		 * 	f : five : 5
		 * 	x : six : 6
		 * 	s : seven : 7 *
		 * 	r : three : 3 *
		 * 	o : one : 1 *
		 * 	t : eight : 8 *
		 * 	i : nine : 9 *
		 */
		char[][] cs = {
			"zero".toCharArray(),
			"one".toCharArray(),
			"two".toCharArray(),
			"three".toCharArray(),
			"four".toCharArray(),
			"five".toCharArray(),
			"six".toCharArray(),
			"seven".toCharArray(),
			"eight".toCharArray(),
			"nine".toCharArray(),
		};
		int[] seq = new int[] {0, 2, 4, 5, 6, 7, 3, 1, 8, 9};
		char[] core = "zwufxsroti".toCharArray();
		int[] ans = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	    public String originalDigits(String s) {
	    	if (s == null || s.length() == 0) {
	    		return "";
	    	}
	    	int[] map = new int[26];
	    	for (int i = s.length() - 1; i > -1; i --) {
	    		map[s.charAt(i) - 'a'] ++;
	    	}
	    	for (int i = 0; i < 10; i ++) {
	    		int seqValue = seq[i];
	    		int cut = map[core[i] - 'a'];
	    		ans[seqValue] += cut;
	    		for (int j = 0; j < cs[seqValue].length; j ++) {
	    			map[cs[seqValue][j] - 'a'] -= cut;
	    		}
	    	}
	    	StringBuilder st = new StringBuilder();
	    	for (int i = 0; i < 10; i ++) {
	    		for (int j = 0; j < ans[i]; j ++) {
	    			st.append(i);
	    		}
	    	}
	        return st.toString();
	    }
	}
}
