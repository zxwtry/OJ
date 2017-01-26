package leetcode;

/**
 * 	Given an integer, write a function to determine if it is a power of three.
	
	Follow up:
	Could you do it without using any loop / recursion?
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P326_PowerOfThree.java
 * @type        P326_PowerOfThree
 * @date        2017年1月10日 下午9:58:20
 * @details     Solution1: AC 使用了loop
 */
public class P326_PowerOfThree {
	static class Solution1 {
	    public boolean isPowerOfThree(int n) {
	    	if (n < 1) return false; 
	    	while (n != 1) {
	    		if (n % 3 != 0)
	    			return false;
	    		n = n / 3;
	    	}
	    	return true;
	    }
	}
	
}
