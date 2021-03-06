package leetcode;

/**
 * 	Convert a non-negative integer to its english 
 * 	words representation. Given input is guaranteed 
 * 	to be less than 231 - 1.
 *	
 *	For example,
 *	123 -> "One Hundred Twenty Three"
 *	12345 -> "Twelve Thousand Three Hundred Forty Five"
 *	1234567 -> "One Million Two Hundred Thirty Four 
 *	Thousand Five Hundred Sixty Seven"
 *	Hint:
 *	
 *	Did you see a pattern in dividing the number 
 *	into chunk of words? For example, 123 and 123000.
 *	Group the number by thousands (3 digits). 
 *	You can write a helper function that takes 
 *	a number less than 1000 and convert just that 
 *	chunk to words.
 *	There are many edge cases. What are some good 
 *	test cases? Does your code work with input 
 *	such as 0? Or 1000010? (middle chunk is zero 
 *	and should not be printed out)
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P273_IntegerToEnglishWords.java
 * @type        P273_IntegerToEnglishWords
 * @date        2016年12月13日 下午10:26:55
 * @details     Solution1: AC 5ms 35.37%
 */
public class P273_IntegerToEnglishWords {
	static class Solution1 {
		String[] words = new String[] {
			"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
			"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty",
		};
		String[] tens = new String[] {
			"","Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
		};
		String[] threeTens = new String[] {
			"", "Thousand", "Million", "Billion"
		};
		String Hundred = "Hundred";
		String Zero = "Zero";
	    public String numberToWords(int num) {
	        if (num <= 20) return num == 0 ? Zero : words[num];
	        char[] numChar = ("" + num).toCharArray();
	        String ans = "";
	        int numCharIndex = numChar.length - 1;
	        int threeTensIndex = 0;
	        while (numCharIndex > -1) {
	        	if (numCharIndex - 2 > -1) {
	        		ans = combineTwoString(combineTwoString(noMoreThanThreeChar(numChar, numCharIndex - 2, numCharIndex), threeTens[threeTensIndex]), ans);
	        		numCharIndex = numCharIndex - 3;
	        	} else {
	        		ans = combineTwoString(combineTwoString(noMoreThanThreeChar(numChar, 0, numCharIndex), threeTens[threeTensIndex]), ans);
	        		numCharIndex = -1;
	        	}
	        	threeTensIndex ++;
	        }
	        return ans;
	    }
	    public String noMoreThanThreeChar(char[] cs, int sti, int eni) {
	    	String ans = (eni - sti == 2 ?  combineTwoString(cs[sti] == '0' ? "" : words[cs[sti] - '0'], Hundred) : "");
	    	sti = (eni - sti == 2 ? sti + 1 : sti);
	    	int val = (sti == eni ? cs[sti] - '0' : (cs[sti] - '0') * 10 + (cs[eni] - '0'));
	    	if (val < words.length) {
	    		ans = combineTwoString(ans, words[val]);
	    	} else {
	    		ans = combineTwoString(ans, combineTwoString(tens[val / 10], words[val % 10]));
	    	}
	    	return ans;
	    }
	    public String combineTwoString(String a, String b) {
	    	if (a.equals("") && (b.equals("Thousand") || b.equals("Million") || b.equals("Billion") || b.equals("Hundred"))) {
	    		return a;
	    	}
	    	return a.equals("") ? b : ( b.equals("") ? a : a + " " + b);
 	    }
	}
}
