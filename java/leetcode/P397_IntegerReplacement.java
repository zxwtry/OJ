package leetcode;

/**
 * Given a positive integer n and you can do operations as follow:
 *	
 *	If n is even, replace n with n/2.
 *	If n is odd, you can replace n with either n + 1 or n - 1.
 *	What is the minimum number of replacements needed for n to become 1?
 *	
 *	Example 1:
 *	
 *	Input:
 *	8
 *	
 *	Output:
 *	3
 *	
 *	Explanation:
 *	8 -> 4 -> 2 -> 1
 *	Example 2:
 *	
 *	Input:
 *	7
 *	
 *	Output:
 *	4
 *	
 *	Explanation:
 *	7 -> 8 -> 4 -> 2 -> 1
 *	or
 *	7 -> 6 -> 3 -> 2 -> 1
 */

public class P397_IntegerReplacement {
	/*
	 * 	AC
	 * 	24 ms
	 */
	static class Solution {
	    public int integerReplacement(int n) {
	    	int bit = 30;
	    	while ((n >> bit) != 1) {
	    		bit --;
	    	}
	    	int minus1 = n - (1 << bit);
	    	int minus2 = n - (1 << (bit + 1));
	    	if (minus1 == 0) {
	    		return bit;
	    	} else if (minus1 == -1 || minus1 == 1) {
	    		return bit + 1;
	    	} else if (minus2 == -1) {
	    		return bit + 2;
	    	}
	    	if ( (n & 0x1) == 0 )
	    		return integerReplacement(n >> 1) + 1;
	    	else
	    		return Math.min(integerReplacement(n - 1), integerReplacement(n + 1)) + 1;
	    }
	}
}
