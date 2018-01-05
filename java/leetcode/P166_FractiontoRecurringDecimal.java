package leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Given two integers representing the numerator and denominator of
 *  a fraction, return the fraction in string format.
	

	If the fractional part is repeating, enclose the repeating part
	 in parentheses.
	
	For example,
	
	Given numerator = 1, denominator = 2, return "0.5".
	Given numerator = 2, denominator = 1, return "2".
	Given numerator = 2, denominator = 3, return "0.(6)".
 */


public class P166_FractiontoRecurringDecimal {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.fractionToDecimal(1, 99));
	}
	/*
	 * 	4 ms
	 * 	48.59%
	 */
	static class Solution {
		// numerator: 分子
		// denominator: 分母
	    public String fractionToDecimal(int numerator, int denominator) {
	        long n = numerator, d = denominator;
	        if (n % d == 0) {
	        	return String.valueOf(n / d);
	        }
	        int negativeBit = 0;
	        if ((n < 0) ^ (d < 0)) {
	        	negativeBit = 1;
	        }
	        n = Math.abs(n);
	        d = Math.abs(d);
	    	long bigThan1 = (int) ( n / d );
	        ArrayList<Character> smallThan1 = generateSmallThan1(n, d);
	        int lenOfDot = smallThan1.size() == 0 ? 0 : 1;
	        int lenOfBigThan1 = bigThan1 == 0 ? 1 : (int)Math.log10(bigThan1) + 1;
	        char[] ans = new char[lenOfBigThan1 + smallThan1.size() + lenOfDot + negativeBit];
	        if (negativeBit == 1) {
	        	ans[0] = '-';
	        }
	        for (int i = lenOfBigThan1 - 1 + negativeBit; i > -1 + negativeBit; i --) {
	        	ans[i] = (char) ( bigThan1 % 10 + '0');
	        	bigThan1 = bigThan1 / 10;
	        }
	        if (lenOfDot == 1) {
		        ans[lenOfBigThan1 + negativeBit] = '.';
		        for (int i = lenOfBigThan1 + 1 + negativeBit; i < ans.length; i ++) {
		        	ans[i] = smallThan1.get(i - lenOfBigThan1 - negativeBit - 1);
		        }
	        }
	    	return new String(ans);
	    }
	    // n: 分子
	    // d: 分母
	    ArrayList<Character> generateSmallThan1(long n, long d) {
	    	ArrayList<Character> ans = new ArrayList<>();
	    	HashMap<Long, Integer> map = new HashMap<>();
	    	n = n % d;
	    	if (n == 0) {
	    		return ans;
	    	}
	    	int index = 0;
	    	while (! map.containsKey(n)) {
	    		if (n == 0) {
	    			break;
	    		}
	    		map.put(n, index ++);
	    		n = n * 10;
	    		ans.add((char) ((n / d) + '0') );
	    		n = n % d;
	    	}
	    	if (n != 0) {
		    	ans.add(map.get(n), '(');
		    	ans.add(')');
	    	}
	    	return ans;
	    }
	}
}
