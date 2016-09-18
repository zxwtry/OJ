package leetcode;

/*
 * 	Given a non-negative integer num represented as a string, 
 * remove k digits from the number so that the new number is the smallest possible.

	Note:
	The length of num is less than 105 and will be ≥ k.
	The given num does not contain any leading zero.
	Example 1:
	
	Input: num = "1432219", k = 3
	Output: "1219"
	Explanation: Remove the three digits 4, 3, and 2 to form the new number
	 1219 which is the smallest.
	Example 2:
	
	Input: num = "10200", k = 1
	Output: "200"
	Explanation: Remove the leading 1 and the number is 200. Note that
	 the output must not contain leading zeroes.
	Example 3:
	
	Input: num = "10", k = 2
	Output: "0"
	Explanation: Remove all the digits from the number and it is left with
	 nothing which is 0.
 */

public class P402_RemoveKDigits {
	public static void main(String[] args) {
		Solution s = new Solution();
//		System.out.println(s.removeKdigits("1432219", 1));
//		System.out.println(s.removeKdigits("200", 1));
//		System.out.println(s.removeKdigits("10", 3));
//		System.out.println(s.removeKdigits("10", 3));
		System.out.println(s.removeKdigits("8899", 1));
	}
	/*
	 * 	AC
	 * 	23 ms
	 */
	static class Solution {
	    public String removeKdigits(String num, int k) {
	    	if (num == null || k >= num.length()) {
	    		return String.valueOf(0);
	    	}
	    	if (k <= 0) {
	    		return num;
	    	}
	    	StringBuilder st = new StringBuilder(num);
	    	while (k -- > 0) {
	    		while (st.length() > 0 && st.charAt(0) == '0') {
	    			st.deleteCharAt(0);
	    		}
	    		if (st.length() < 2) {
	    			return String.valueOf(0);
	    		}
	    		if (st.charAt(1) >= st.charAt(0)) {
	    			int del_index = 1;
	    			while (st.length() - 1 > del_index && st.charAt(del_index + 1) >= st.charAt(del_index)) {
	    				del_index ++;
	    			}
	    			st.deleteCharAt(del_index);
	    		} else {
	    			st.deleteCharAt(0);
	    		}
	    	}
	    	while (st.length() > 0 && st.charAt(0) == '0') {
    			st.deleteCharAt(0);
    		}
    		if (st.length() == 0) {
    			return String.valueOf(0);
    		}
	        return st.toString();
	    }
//	    int getIndex(StringBuilder st) {
//	    	int i = 1;
//	    	for (; i < st.length(); i ++) {
//	    		if (st.charAt(i) != '0') {
//	    			return i;
//	    		}
//	    	}
//	    	return i;
//	    }
	}
}
