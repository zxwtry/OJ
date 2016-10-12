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
		int count = 0;
		for (int i = 0; i < 1000; i ++) {
			long n1 = Math.abs((long) (Math.random() * ( ( (long)Integer.MAX_VALUE ) * 2 )) );
			long n2 = Math.abs((long) (Math.random() * ( ( (long)Integer.MAX_VALUE ) * 2 )) );
			
			String num1 = String.valueOf(n1);
			String num2 = String.valueOf(n2);
			Solution2 s = new Solution2();
			String ans = s.addStrings(num1, num2);
			
			long n3 = Long.parseLong(ans);
			
			
			
			if (n1 + n2 != n3) {
				count ++;
				System.out.println(num1 + "....." + num2);
			}
			
		}
		System.out.println(count);
		
		//525175143.....1545754252
//		Solution2 s2 = new Solution2();
//		
//		System.out.println(s2.addStrings("525175143", "1545754252"));
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
	/*
	 * 	肯定能省时间
	 * 	26 ms
	 * 	翻车了。。。
	 */
	static class Solution2 {
	    public String addStrings(String num1, String num2) {
	    	int len1 = num1.length(), len2 = num2.length();
	    	if (len1 < len2) {
	    		return addStrings(num2, num1);
	    	}
	    	int[] n1 = new int[len1];
	    	for (int i = 0; i < len1; i ++) {
	    		n1[i] = num1.charAt(i) - '0';
	    	}
//	    	tools.Utils.printArray(n1, 100);
	    	int range = len1 - len2;
	    	for (int i = len2 - 1; i > -1; i --) {
	    		n1[i + range] += num2.charAt(i) - '0';
	    	}
//	    	for (int i = 0; i < len2; i ++) {
//	    		n1[i] += num2.charAt(i) - '0';
//	    	}
//	    	tools.Utils.printArray(n1, 100);
	    	int carry = 0;
	    	for (int i = len1 - 1; i > 0; i --) {
	    		int sum = n1[i] + carry;
	    		n1[i] = sum % 10;
	    		carry = sum / 10;
	    	}
	    	n1[0] += carry;
	    	int lenOf0 = 0, v0 = n1[0];
	    	while (v0 != 0) {
	    		lenOf0 ++;
	    		v0 = v0 / 10;
	    	}
	    	char[] ans = new char[lenOf0 + len1 - 1];
	    	v0 = n1[0];
	    	for (int i = lenOf0 - 1; i > -1; i --) {
	    		ans[i] = (char)('0' + (v0 % 10));
	    		v0 = v0 / 10;
	    	}
	    	for (int i = 1; i < len1; i ++) {
	    		ans[i + lenOf0 - 1]  = (char)('0' + n1[i]);
	    	}
	    	return ans.length == 0 ? "0" : new String(ans);
	    }
	}
}
