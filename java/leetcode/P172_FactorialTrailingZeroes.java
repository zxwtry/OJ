package leetcode;

/*
 * 	Given an integer n, return the number of trailing zeroes in n!.
	
	Note: Your solution should be in logarithmic time complexity.
 */

public class P172_FactorialTrailingZeroes {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.trailingZeroes(15));
	}
	/*
	 * 	1 ms 45.63%
	 */
	static class Solution {
	    public int trailingZeroes(int n) {
	    	int count = 0;
	    	while (n > 4) {
	    		count += n / 5;
	    		n = n / 5;
	    	}
	        return count;
	    }
	}
}