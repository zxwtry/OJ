package leetcode;

import java.util.ArrayList;

/**
 * 	Write a program to find the n-th ugly number.
 *	
 *	Ugly numbers are positive numbers whose prime 
 *	factors only include 2, 3, 5. For example, 
 *	1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence 
 *	of the first 10 ugly numbers.
 *	
 *	Note that 1 is typically treated as an ugly number.
 *	
 *	Hint:
 *	
 *	The naive approach is to call isUgly for every 
 *	number until you reach the nth one. Most numbers 
 *	are not ugly. Try to focus your effort on generating 
 *	only the ugly ones.
 *	An ugly number must be multiplied by either 2, 3, 
 *	or 5 from a smaller ugly number.
 *	The key is how to maintain the order of the ugly 
 *	numbers. Try a similar approach of merging from 
 *	three sorted lists: L1, L2, and L3.
 *	Assume you have Uk, the kth ugly number. Then Uk+1 
 *	must be Min(L1 * 2, L2 * 3, L3 * 5).
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P264_UglyNumberII.java
 * @type        P264_UglyNumberII
 * @date        2016年12月13日 下午10:23:30
 * @details     Solution1: AC 58ms 19.62%
 * @details     Solution2: AC 14ms 42.68%
 */
public class P264_UglyNumberII {
	static class StandardSolution {
		public int nthUglyNumber(int n) {
			if (n < 2) return 1;
			int nIndex = 1;
			int index = 1;
			for (; nIndex <= n; index ++) {
				if (isUgly(index)) {
					nIndex ++;
				}
			}
			return index - 1;
 		}
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
