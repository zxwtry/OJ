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
 * @details     
 */
public class P233_NumberofDigitOne {
	public static void main(String[] args) {
		System.out.println(new Solution().countDigitOne(13));
	}
	static class Solution {
	    public int countDigitOne(int n) {
	    	if (n < 10) return 1;
	        String s = String.valueOf(n);
	        int max = 1;
	        while (max <= n) {
	        	max = max * 10;
	        }
	        max = max / 100;
	        System.out.println(max);
	        int ans = 0;
	        if (s.charAt(0) > '1') {
		        ans += (s.length() - 1) * max;
	        	ans += ans * (s.charAt(0) - '1');
	        	ans += countDigitOne(n - (s.charAt(0) - '0') * max * 10);
	        	ans += max * 10;
	        	return ans;
	        } else {
	        	ans += (s.length() - 1) * max;
	        	ans += ans * (s.charAt(0) - '1');
	        	ans += n - (s.charAt(0) - '0') * max * 10 + 1;
	        	return ans;
	        }
	        return
	    }
	    
	}
}
