package leetcode;

/*
 * 	Given a range [m, n] where 0 <= m <= n <= 2147483647, 
 * 	return the bitwise AND of all numbers in this range, inclusive.
	
	For example, given the range [5, 7], you should return 4.
 */

public class P201_BitwiseANDofNumbersRange {
	public static void main(String[] args) {
		System.out.println(String.format("%%%s%%", "ddd"));
//		Solution s = new Solution();
//		System.out.println(s.rangeBitwiseAnd(2147483647, 2147483647));
	}
	/*
	 * 	9 ms
	 * 	44.28%
	 */
	static class Solution {
	    public int rangeBitwiseAnd(int m, int n) {
	    	int ans = m;
	    	if (m == 0 || n / m > 1) {
	    		return 0;
	    	}
	    	if (m + 1 > m) {
		    	for (int i = m + 1; i <= n; i ++) {
		    		if (i + 1 < i) {
		    			break;
		    		}
		    		if (ans == 0) {
		    			return 0;
		    		}
		    		ans &= i;
		    	}
	    	}
	        return ans;
	    }
	}
}
