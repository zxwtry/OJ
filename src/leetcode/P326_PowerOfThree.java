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
 * @details     Solution1: AC 使用pow
 */
public class P326_PowerOfThree {
	static class Solution1 {
	    public boolean isPowerOfThree(int n) {
	    	boolean isNegative = n < 0;
	    	n = isNegative ? -n : n;
	    	double pow = Math.pow(n, (double)1/3);
	        int a = (int)pow;
	        if (pow - a > 0.5) a ++;
	        return a * a * a == n;
	    }
	}
}
