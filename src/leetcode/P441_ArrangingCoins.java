package leetcode;

/**
 * 	You have a total of n coins that you want to form in a staircase shape, 
 * 	where every k-th row must have exactly k coins.
 *	
 *	Given n, find the total number of full staircase rows that can be formed.
 *	
 *	n is a non-negative integer and fits within the range of a 32-bit signed integer.
 *	
 *	Example 1:
 *	
 *	n = 5
 *	
 *	The coins can form the following rows:
 *	¤
 *	¤ ¤
 *	¤ ¤
 *	
 *	Because the 3rd row is incomplete, we return 2.
 *	Example 2:
 *	
 *	n = 8
 *	
 *	The coins can form the following rows:
 *	¤
 *	¤ ¤
 *	¤ ¤ ¤
 *	¤ ¤
 *	
 *	Because the 4th row is incomplete, we return 3.
 */

public class P441_ArrangingCoins {
	public static void main(String[] args) {
		debugSolution();
	}
	static void debugSolution() {
		Solution s = new Solution();
		System.out.println(s.arrangeCoins(1804289383));
	}
	/*
	 * 	会不会有点太简单了。
	 * 	46 ms
	 * 	73.34%
	 */
	static class Solution {
	    public int arrangeCoins(int n) {
	    	if (n < 1) {
	    		return 0;
	    	}
	    	return (int) (0.5 * (-1 + Math.sqrt(1 + 8 * (long)n)));
	    }
	}
}
