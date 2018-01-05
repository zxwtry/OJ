package leetcode;

/**
 * 	Given a positive integer, output its complement number. 
 * 	The complement strategy is to flip the bits of its binary representation.
 *	
 *	Note:
 *	The given integer is guaranteed to fit within the range of a 32-bit signed integer.
 *	You could assume no leading zero bit in the integer’s binary representation.
 *	Example 1:
 *	Input: 5
 *	Output: 2
 *	Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
 *	Example 2:
 *	Input: 1
 *	Output: 0
 *	Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to out
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P476_NumberComplement.java
 * @type        P476_NumberComplement
 * @date        2017年1月8日 上午10:31:29
 * @details     Solution: AC 12ms 42.40%
 */
public class P476_NumberComplement {
	static class Solution {
	    public int findComplement(int n) {
	    	int _n = ~ n;
	    	int i = 1 << 31;
	    	while ((n & i) == 0) {
	    		_n ^= i;
	    		i >>>= 1;
	    	}
	        return _n;
	    }
	}
}
