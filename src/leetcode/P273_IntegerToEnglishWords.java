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
 * @details     
 */
public class P273_IntegerToEnglishWords {
	public static void main(String[] args) {
		Solution1 solution1 = new Solution1();
		for (int i = 0; i < 21; i ++) {
			System.out.println(solution1.numberToWords(i));
		}
		System.out.println(Integer.MAX_VALUE);
	}
	static class Solution1 {
		String[] words = new String[] {
			"zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
			"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty",
		};
		String[] tens = new String[] {
			"","Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty"
		};
		String[] threeTens = new String[] {
			"", "Thousand", "Million", "Billion"
		};
	    public String numberToWords(int num) {
	        if (num <= 20) return words[num];
	        char[] numChar = ("" + num).toCharArray();
	        
	        return "NULL";
	    }
	    public String lessThanThreeChar(char[] cs, int sti, int eni) {
	    	
	    }
	}
}
