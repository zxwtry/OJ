package leetcode;

import java.util.ArrayList;

/*
 * 	Implement a basic calculator to evaluate a simple expression string.

	The expression string contains only non-negative integers, +, -, *, / 
	operators and empty spaces . The integer division should truncate toward zero.
	
	You may assume that the given expression is always valid.
	
	Some examples:
	"3+2*2" = 7
	" 3/2 " = 1
	" 3+5 / 2 " = 5
	Note: Do not use the eval built-in library function.
 */

public class P227_BasicCalculatorII {
	public static void main(String[] args) {
//		StringBuilder st = new StringBuilder("0123456");
//		st.delete(3, 4);
//		System.out.println(st.toString());
		
		Solution2 s = new Solution2();
		System.out.println(s.calculate("3+2*2"));
		
	}
	/*
	 * 	AC
	 * 	129 ms
	 * 	4.02%
	 */
	static class Solution {
	    public int calculate(String s) {
	    	StringBuilder st = new StringBuilder(s);
	    	for (int i = 0; i < st.length(); i ++) {
	    		if (st.charAt(i) == ' ') {
	    			st.deleteCharAt(i);
	    			i --;
	    		}
	    	}
	    	if (st.length() == 0) {
	    		return 0;
	    	}
	    	while (true) {
	    		int indexOfDM = 0;
	    		for (; indexOfDM < st.length(); indexOfDM ++) {
	    			char c = st.charAt(indexOfDM);
	    			if (c == '/' || c == '*') {
	    				break;
	    			}
	    		}
	    		if (indexOfDM == st.length()) {
	    			break;
	    		}
	    		int indexOfNumber1 = indexOfDM - 1;
	    		while (indexOfNumber1 > -1 && isNumber(st.charAt(indexOfNumber1))) {
	    			indexOfNumber1 --;
	    		}
	    		int indexOfNumber2 = indexOfDM + 1;
	    		while (indexOfNumber2 < st.length() && isNumber(st.charAt(indexOfNumber2))) {
	    			indexOfNumber2 ++;
	    		}
	    		int intValue1 = Integer.parseInt(st.substring(indexOfNumber1 + 1, indexOfDM));
	    		int intValue2 = Integer.parseInt(st.substring(indexOfDM + 1, indexOfNumber2));
	    		int intValue3 = calc(intValue1, intValue2, st.charAt(indexOfDM));
    			st.delete(indexOfNumber1 + 1, indexOfNumber2);
    			if (st.length() == 0) {
    				return intValue3;
    			}
    			st.insert(indexOfNumber1 + 1, intValue3);
	    	}
	    	String[] parts = st.toString().split("[-+]");
	    	ArrayList<Character> list = new ArrayList<>();
	    	for (int i = 0; i < st.length(); i ++) {
	    		char c = st.charAt(i);
	    		if (c == '+' || c == '-') {
	    			list.add(c);
	    		}
	    	}
	    	int[] partsInt = new int[parts.length];
	    	for (int i = 0; i < parts.length; i ++) {
	    		partsInt[i] = Integer.parseInt(parts[i]);
	    	}
	    	int ans = partsInt[0];
	    	for (int i = 1; i < partsInt.length; i ++) {
	    		ans = calc(ans, partsInt[i], list.get(i - 1));
	    	}
//	    	int answer = 0;
//	    	boolean isFirst = true;
//	    	while (true) {
//	    		int indexOfDM = 0;
//	    		for (; indexOfDM < st.length(); indexOfDM ++) {
//	    			char c = st.charAt(indexOfDM);
//	    			if (c == '+' || c == '-') {
//	    				break;
//	    			}
//	    		}
//	    		if (indexOfDM == st.length()) {
//	    			break;
//	    		}
//	    		int indexOfNumber1 = indexOfDM - 1;
//	    		while (indexOfNumber1 > -1 && isNumber(st.charAt(indexOfNumber1))) {
//	    			indexOfNumber1 --;
//	    		}
//	    		int indexOfNumber2 = indexOfDM + 1;
//	    		while (indexOfNumber2 < st.length() && isNumber(st.charAt(indexOfNumber2))) {
//	    			indexOfNumber2 ++;
//	    		}
//	    		System.out.println(st.toString());
//	    		int intValue1 = Integer.parseInt(st.substring(indexOfNumber1 + 1, indexOfDM));
//	    		int intValue2 = Integer.parseInt(st.substring(indexOfDM + 1, indexOfNumber2));
//	    		int intValue3 = calc(intValue1, intValue2, st.charAt(indexOfDM));
//    			st.delete(indexOfNumber1 + 1, indexOfNumber2);
//    			if (st.length() == 0) {
//    				return intValue3;
//    			}
//    			st.insert(indexOfNumber1 + 1, intValue3);
//	    	}
	    	return ans;
	    }
	    public boolean isNumber(char c) {
	    	return c >= '0' && c <= '9';
	    }
	    public int calc(int val1, int val2, char c) {
	    	switch (c) {
			case '+':
				return val1 + val2;
			case '-':
				return val1 - val2;
			case '*':
				return val1 * val2;
			case '/':
				return val1 / val2;
			default:
				return 0;
			}
	    }
	}
	
	/*
	 * 	100 ms
	 * 	6.70%
	 */
	static class Solution2 {
	    public int calculate(String s) {
	    	StringBuilder st = new StringBuilder(s);
	    	for (int i = 0; i < st.length(); i ++) {
	    		if (st.charAt(i) == ' ') {
	    			st.deleteCharAt(i);
	    			i --;
	    		}
	    	}
	    	if (st.length() == 0) {
	    		return 0;
	    	}
	    	String[] parts = st.toString().split("[-+*/]");
	    	ArrayList<Character> list = new ArrayList<>();
	    	for (int i = 0; i < st.length(); i ++) {
	    		char c = st.charAt(i);
	    		if (c == '+' || c == '-' || c == '*' || c == '/') {
	    			list.add(c);
	    		}
	    	}
	    	ArrayList<Integer> partsInt = new ArrayList<Integer>(parts.length);
	    	for (int i = 0; i < parts.length; i ++) {
	    		partsInt.add(Integer.parseInt(parts[i]));
	    	}
	    	for (int i = 0; i < list.size(); i ++) {
	    		char c = list.get(i);
	    		if (c == '*' || c == '/') {
	    			int val = calc(partsInt.get(i), partsInt.get(i + 1), c);
	    			partsInt.remove(i + 1);
	    			partsInt.remove(i);
	    			partsInt.add(i, val);
	    			list.remove(i);
	    			i --;
	    		}
	    	}
	    	int ans = partsInt.get(0);
	    	for (int i = 1; i < partsInt.size(); i ++) {
	    		ans = calc(ans, partsInt.get(i), list.get(i - 1));
	    	}
	    	return ans;
	    }
	    public boolean isNumber(char c) {
	    	return c >= '0' && c <= '9';
	    }
	    public int calc(int val1, int val2, char c) {
	    	switch (c) {
			case '+':
				return val1 + val2;
			case '-':
				return val1 - val2;
			case '*':
				return val1 * val2;
			case '/':
				return val1 / val2;
			default:
				return 0;
			}
	    }
	}
}
