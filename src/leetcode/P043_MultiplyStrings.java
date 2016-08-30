package leetcode;

/*
 * 	Given two numbers represented as strings, 
 * 	return multiplication of the numbers as a string.

	Note:
	The numbers can be arbitrarily large and are non-negative.
	Converting the input string to integer is NOT allowed.
	You should NOT use internal library such as BigInteger.
 */

public class P043_MultiplyStrings {
	public static void main(String[] args) {
//		System.out.println(new Solution().multiply("99", "9"));
		System.out.println(new Solution().multiply("99", "0"));
	}
	/*
	 * 	 一个简单的大数乘法
	 * 	8 ms
	 * 	79.50% 
	 */
	static class Solution {
	    public String multiply(String num1, String num2) {
	    	int len1 = 0, len2 = 0;
	    	if (num1 == null || (len1 = num1.length()) == 0 || 
	    			num1 == null || (len2 = num2.length()) == 0 )
	    		return String.valueOf(0);
	    	int[] ans = new int[len1 + len2 + 1];
	    	for (int i = len1 - 1; i > -1; i --)
	    		for (int j = len2 - 1; j > -1; j --)
	    			ans[i + 2 + j] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
	    	int carry = 0;
	    	for (int i = len1 + len2; i > -1; i --) {
	    		int temp = ans[i] + carry;
	    		ans[i] = temp % 10;
	    		carry = temp / 10;
	    	}
	    	int i = 0;
	    	char[] cs = null;
	    	for (carry = -1; i != ans.length; i ++)
	    		if (carry == -1) {
	    			if (ans[i] != 0) {
		    			cs = new char[ans.length - i];
		    			carry = i;
		    			cs[0] = (char)(ans[i] + '0');
	    			}
	    		} else
	    			cs[i - carry] = (char)(ans[i] + '0');
	        return cs == null ? "0" : new String(cs);
	    }
	}
}
