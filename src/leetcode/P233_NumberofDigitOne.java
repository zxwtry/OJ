package leetcode;

/**
 * 	Given an integer n, count the total number of digit 1 appearing 
 * 	in all non-negative integers less than or equal to n.
 * 

	For example:
	Given n = 13,
	Return 6, because digit 1 occurred in the following numbers:
	 1, 10, 11, 12, 13.
	
	Hint:
	
	Beware of overflow.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P233_NumberofDigitOne.java
 * @type        P233_NumberofDigitOne
 * @date        2016年12月9日 下午10:11:50
 * @details     Solution: AC 0ms 11.75%
 */
public class P233_NumberofDigitOne {
	static class Solution {
	    public int countDigitOne(int n) {
	    	if (n < 0) return 0;
	    	int[] arr = {0,1,1,1,1,1,1,1,1,1,2,4,5,6,7,8,9,10
	    			,11,12,12,13,13,13,13,13,13,13,13,13,13,14,
	    			14,14,14,14,14,14,14,14,14,15,15,15,15,15,15
	    			,15,15,15,15,16,16,16,16,16,16,16,16,16,16,17,
	    			17,17,17,17,17,17,17,17,17,18,18,18,18,18,18,18,
	    			18,18,18,19,19,19,19,19,19,19,19,19,19,20,20,20,
	    			20,20,20,20,20,20};
	    	if (n < 100) return arr[n];
	    	char[] c = String.valueOf(n).toCharArray();
	    	int small = Integer.parseInt(new String(c, 1, c.length - 1));
	    	int addOnce = 1;
	    	for (char i = 2; i < c.length; i ++) addOnce *= 10;
	    	int digitOne = countDigitOne(small);
	    	if (c[0] == '1') digitOne += small + 1;
	    	else digitOne += addOnce * 10;
	    	digitOne += (c[0] - '0') * addOnce * (c.length - 1);
	    	return digitOne;
	    }
	}
}
