package leetcode;

/**
 *  Given a positive integer num, write a function which returns True
 * 	if num is a perfect square else False.

	Note: Do not use any built-in library function such as sqrt.
	
	Example 1:
	
	Input: 16
	Returns: True
	Example 2:
	
	Input: 14
	Returns: False
	Credits:
	Special thanks to @elmirap for adding this problem and creating all test cases.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P367_ValidPerfectSquare.java
 * @type        P367_ValidPerfectSquare
 * @date        2016年12月8日 下午10:32:36
 * @details     Solution1: AC 
 * @details     Solution2: AC 速度大概是Solution1的1/3
 */
public class P367_ValidPerfectSquare {
	static class Solution1 {
	    public boolean isPerfectSquare(int num) {
	        int sqrt = (int)Math.sqrt(num);
	        return sqrt * sqrt == num;
	    }
	}
}
