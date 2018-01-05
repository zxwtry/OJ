package leetcode;

/*
 * 	You are climbing a stair case. It takes n steps to reach to the top.

	Each time you can either climb 1 or 2 steps. 
	In how many distinct ways can you climb to the top?
 */

public class P070_ClimbingStairs {
	public static void main(String[] args) {
		System.out.println(new Solution().climbStairs(5));
	}
	/*
	 * 	1 ms
	 * 	4.24%
	 */
	static class Solution {
	    public int climbStairs(int n) {
	    	int a = 1, b = 2, c = 2, d = 2;
	    	if (n == 1)
	    		return a;
	    	if (n == 2)
	    		return b;
	    	while (c != n) {
	    		d = a + b;
	    		a = b;
	    		b = d;
	    		c ++;
	    	}
	    	return d;
	    }
	}
}