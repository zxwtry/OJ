package leetcode;

/**
 * 	Given a non-negative integer num, repeatedly add
 *  all its digits until the result has only one digit.

	For example:
	
	Given num = 38, the process is like: 3 + 8 = 11, 
	1 + 1 = 2. Since 2 has only one digit, return it.
	
	Follow up:
	Could you do it without any loop/recursion in O(1) 
	runtime?
	
	Hint:
	
	A naive implementation of the above process is trivial.
	 Could you come up with other methods?
	What are all the possible results?
	How do they occur, periodically or randomly?
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P258_AddDigits.java
 * @type        P258_AddDigits
 * @date        2016年12月13日 下午10:18:15
 * @details     Solution: AC 2ms 22.18%
 */
public class P258_AddDigits {
	static class Solution {
	    public int addDigits(int num) {
	        if (num < 10) return num;
	        int v = num;
	        int nv = 0;
	        int c = 0;
	        while (true) {
	        	nv = 0;
	        	c = 0;
	        	while (v != 0) {
	        		nv += v % 10;
	        		v = v / 10;
	        		c ++;
	        	}
	        	v = nv;
	        	if (c == 1) break;
	        }
	        return v;
	    }
	}
}
