package leetcode;

/*
 * 	Given two non-negative numbers num1 and num2 represented as string, 
 * 	return the sum of num1 and num2.

	Note:
	
	The length of both num1 and num2 is < 5100.
	Both num1 and num2 contains only digits 0-9.
	Both num1 and num2 does not contain any leading zero.
	You must not use any built-in BigInteger library or convert 
	the inputs to integer directly.
 */

public class P415_AddStrings {
	public static void main(String[] args) {
//		int count = 0;
//		for (int i = 0; i < 1000; i ++) {
//			long n1 = Math.abs((long) (Math.random() * ( ( (long)Integer.MAX_VALUE ) * 2 )) );
//			long n2 = Math.abs((long) (Math.random() * ( ( (long)Integer.MAX_VALUE ) * 2 )) );
//			
//			String num1 = String.valueOf(n1);
//			String num2 = String.valueOf(n2);
//			Solution s = new Solution();
//			String ans = s.addStrings(num1, num2);
//			
//			long n3 = Long.parseLong(ans);
//			
//			if (n1 + n2 != n3) {
//				count ++;
//			}
//			
//		}
//		System.out.println(count);
		
		System.out.println(new Solution().addStrings("0", "0"));
	}
	/*
	 * 	22 ms
	 */
	static class Solution {
	    public String addStrings(String num1, String num2) {
	    	if (num1.length() < num2.length()) {
	    		return addStrings(num2, num1);
	    	}
	    	int[] n1 = new int[num1.length()];
	    	int[] n2 = new int[num2.length()];
	    	for (int i = 0; i < n1.length; i ++) {
	    		n1[i] = num1.charAt(i) - '0';
	    	}
	    	for (int i = 0; i < n2.length; i ++) {
	    		n2[i] = num2.charAt(i) - '0';
	    	}
	    	int[] n3 = new int[n1.length];
	    	int gap = n1.length - n2.length;
	    	
	    	for (int i = n3.length - 1; i >= gap; i --) {
	    		int i1 = i;
	    		int i2 = i - gap;
	    		n3[i] = n1[i1] + n2[i2];
	    	}
	    	for (int i = gap - 1; i > -1; i --) {
	    		n3[i] = n1[i];
	    	}
	    	int carry = 0;
	    	for (int i = n3.length - 1; i > 0; i --) {
	    		int sum = carry + n3[i];
	    		carry = sum / 10;
	    		n3[i] = sum % 10;
	    	}
	    	n3[0] += carry;
	    	StringBuilder st = new StringBuilder(n1.length + 2);
	    	for (int val : n3) {
	    		st.append(String.valueOf(val));
	    	}
	    	
	    	return st.toString();
	    }
	}
}
