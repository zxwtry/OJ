package leetcode;

/**
 * 	Write a function that takes a string as input and returns the string reversed.
 * 	
 * 	Example:
 * 	Given s = "hello", return "olleh".
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P344_ReverseString.java
 * @type        P344_ReverseString
 * @date        2017年2月2日 下午9:18:26
 * @details     Solution: AC 2ms 82.98%
 */
public class P344_ReverseString {
	static class Solution {
	    public String reverseString(String s) {
	        if (s == null) return null;
	        char[] c = s.toCharArray();
	        int i = 0, j = c.length - 1;
	        char ct = '\0';
	        while (i < j) {
	        	ct = c[i];
	        	c[i] = c[j];
	        	c[j] = ct;
	        	i ++;
	        	j --;
	        }
	        return new String(c);
	    }
	}
}
