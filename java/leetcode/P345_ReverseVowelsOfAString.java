package leetcode;

/**
 * 	Write a function that takes a string as input and reverse only the vowels of a string.
 * 	
 * 	Example 1:
 * 	Given s = "hello", return "holle".
 * 	
 * 	Example 2:
 * 	Given s = "leetcode", return "leotcede".
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P345_ReverseVowelsOfAString.java
 * @type        P345_ReverseVowelsOfAString
 * @date        2017年2月2日 下午11:15:26
 * @details     Solution: AC 5ms 84.40%
 */
public class P345_ReverseVowelsOfAString {
	static class Solution {
	    public String reverseVowels(String s) {
	    	if (s == null) return null;
	        char[] cs = s.toCharArray();
	        int i = 0, j = cs.length - 1;
	        char ct = '\0';
	        while (i < j) {
	        	while (i < j && i < cs.length && ! isVowel(cs[i]))  i++;
	        	while (i < j &&j > -1 && ! isVowel(cs[j])) j --;
	        	if (i < j) {
		        	ct = cs[i];
		        	cs[i] = cs[j];
		        	cs[j] = ct;
		        	i ++;
		        	j --;
	        	}
	        }
	        return new String(cs);
	    }
	    private boolean isVowel(char c) {
	    	return 	c == 'a' || c == 'A' ||
	    			c == 'e' || c == 'E' ||
	    			c == 'i' || c == 'I' ||
	    			c == 'o' || c == 'O' ||
	    			c == 'u' || c == 'U';
	    }
	}
}
