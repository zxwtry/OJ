package leetcode;

/**
 * 	Calculate the sum of two integers a and b, 
 * 	but you are not allowed to use the operator + and -.
 * 	
 * 	Example:
 * 	Given a = 1 and b = 2, return 3.
 */

public class P371_SumofTwoIntegers {
	/*
	 * 	0 ms
	 * 	8.45%
	 */
	static class Solution {
	    public int getSum(int a, int b) {
	    	int c = 0;
	    	while (0 != a) {
	    		c = (a & b) << 1;
	    		b = a ^ b;
	    		a = c;
	    	}
	        return b;
	    }
	}
}
