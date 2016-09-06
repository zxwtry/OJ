package leetcode;

/*
 * 	Given two binary strings, return their sum (also a binary string).

	For example,
	a = "11"
	b = "1"
	Return "100".
 */

public class P067_AddBinary {
	public static void main(String[] args) {
		System.out.println(new Solution().addBinary("11111", "1"));
	}
	/*
	 * 	一次AC，好爽！！！
	 * 	3 ms
	 * 	89.82%
	 */
	static class Solution {
	    public String addBinary(String a, String b) {
	    	int m = 0, n = 0;
	        if (a == null || b == null || (m = a.length()) == 0 || (n = b.length()) == 0)
	        	return m == 0 ? b : a;
	        if (m > n)
	        	return addBinary(b, a);
	        int[] sum = new int[n];
	        int carry = 0;
	        while (true) {
	        	m --;
	        	n --;
	        	if (m > -1)
	        		sum[n] = a.charAt(m) - '0';
	        	if (n == -1)
	        		break;
	        	sum[n] += carry;
	        	sum[n] += b.charAt(n) - '0';
	        	carry = sum[n] / 2;
	        	sum[n] = sum[n] % 2;
	        }
	        char[] cs = new char[carry + b.length()];
	        cs[0] = '1';
	        for (int i = carry; i != cs.length; i ++)
	        	cs[i] = sum[i - carry] == 0 ? '0' : '1';
	        return new String(cs);
	    }
	}
}