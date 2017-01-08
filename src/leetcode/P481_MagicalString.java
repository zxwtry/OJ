package leetcode;

/**
 * 	A magical string S consists of only '1' and '2' and obeys the following rules:

	The string S is magical because concatenating the number of contiguous occurrences of characters '1' and '2' generates the string S itself.
	
	The first few elements of string S is the following: S = "1221121221221121122……"
	
	If we group the consecutive '1's and '2's in S, it will be:
	
	1 22 11 2 1 22 1 22 11 2 11 22 ......
	
	and the occurrences of '1's or '2's in each group are:
	
	1 2	 2 1 1 2 1 2 2 1 2 2 ......
	
	You can see that the occurrence sequence above is the S itself.
	
	Given an integer N as input, return the number of '1's in the first N number in the magical string S.
	
	Note: N will not exceed 100,000.
	
	Example 1:
	Input: 6
	Output: 3
	Explanation: The first 6 elements of magical string S is "12211" and it contains three 1's, so return 3.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P481_MagicalString.java
 * @type        P481_MagicalString
 * @date        2017年1月8日 上午10:39:47
 * @details     
 */
public class P481_MagicalString {
	static class Solution {
		String cons = "122";
		char sign_1 = '1';
		char sign_2 = '2';
		char sign_0 = '0';
	    public int magicalString(int n) {
	    	if (n < 0) return 0;
	    	int count = 0;
	        if (n <= cons.length()) {
	        	for (int i = 0; i < n; i ++)
	        		count += cons.charAt(i) == sign_1 ? 1 : 0;
	        	return count;
	        }
	        char[] cs = new char[n];
	        System.arraycopy(cons.toCharArray(), 0, cs, 0, cons.length());
	        int ci = 1, oi = 2;
	        while (oi < n) {
	        	char sign = cs[ci + 1];
	        	if (sign == sign_1) {
	        		if (oi + 1 >= n) break;
	        		cs[oi + 1] = cs[oi] == sign_1 ? sign_2 : sign_1;
	        	} else {
	        		if (oi + 1 >= n) break;
	        		cs[oi + 1] = cs[oi] == sign_1 ? sign_2 : sign_1;
	        		if (oi + 2 >= n) break;
	        		cs[oi + 2] = cs[oi + 1];
	        	}
	        	ci ++;
	        	oi += sign - sign_0;
	        }
	        for (int i = 0; i < n; i ++)
	        	count += cs[i] == sign_1 ? 1 : 0;
	        return count;
	    }
	}
}
