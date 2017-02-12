package leetcode;

/**
 *	Given an integer, return a base 7 representation of it as a String.
 *	Example 1:
 *	Input: 100
 *	Output: "202"
 *	Example 2:
 *	Input: -7
 *	Output: "-10"
 *	Note: The input will be in range of [-1e7, 1e7].
 */
public class P504_Base7 {
	static class Solution {
	    public String convertTo7(int num) {
	    	if (num == 0) return "0";
	        char[] cs = new char[40];
	        int csIndex = cs.length - 1;
	        boolean isNegative = num < 0;
	        num = Math.abs(num);
	        int count = 0;
	        while (num != 0) {
	        	cs[csIndex --] = (char)(num % 7 + '0');
	        	num = num / 7;
	        	count ++;
	        }
	        return isNegative ? "-" + new String(cs, csIndex + 1, count) : new String(cs, csIndex + 1, count);
	    }
	}
}
