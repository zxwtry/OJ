package leetcode;

/**
 * 	Write a program to check whether a given number 
 * 	is an ugly number.
	
	Ugly numbers are positive numbers whose prime 
	factors only include 2, 3, 5. For example, 6, 8 
	are ugly while 14 is not ugly since it includes 
	another prime factor 7.
	
	Note that 1 is typically treated as an ugly number.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P263_UglyNumber.java
 * @type        P263_UglyNumber
 * @date        2016年12月13日 下午10:22:02
 * @details     Solution: AC 2ms 18.00%
 */
public class P263_UglyNumber {
	static class Solution {
	    public boolean isUgly(int num) {
	        if (num <= 1) return num == 1;
	        boolean is_2 = false, is_3 = false, is_5 = false;
	        while (num != 1) {
	        	is_2 = num % 2 == 0;
	        	is_3 = num % 3 == 0;
	        	is_5 = num % 5 == 0;
	        	if (is_2) num = num / 2;
	        	if (is_3) num = num / 3;
	        	if (is_5) num = num / 5;
	        	if (!is_2 && !is_3 && !is_5) return false;
	        }
	        return true;
	    }
	}
}
