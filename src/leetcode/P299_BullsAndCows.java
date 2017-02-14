package leetcode;

/**
 * 	You are playing the following Bulls and Cows game with your friend: 
 * 	You write down a number and ask your friend to guess what the number is. 
 * 	Each time your friend makes a guess, you provide a hint that indicates 
 * 	how many digits in said guess match your secret number exactly in both digit 
 * 	and position (called "bulls") and how many digits match the secret number 
 * 	but locate in the wrong position (called "cows"). Your friend will use 
 * 	successive guesses and hints to eventually derive the secret number.
 *
 *	For example:
 *	
 *	Secret number:  "1807"
 *	Friend's guess: "7810"
 *	Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
 *	Write a function to return a hint according to the secret number and 
 *	friend's guess, use A to indicate the bulls and B to indicate the cows. 
 *	In the above example, your function should return "1A3B".
 *	
 *	Please note that both secret number and friend's guess may contain duplicate digits, 
 *	for example:
 *	
 *	Secret number:  "1123"
 *	Friend's guess: "0111"
 *	In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, 
 *	and your function should return "1A1B".
 *	You may assume that the secret number and your friend's guess only contain digits, 
 *	and their lengths are always equal.
 *	
 *	Credits:
 *	Special thanks to @jeantimex for adding this problem and creating all test cases.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P299_BullsAndCows.java
 * @type        P299_BullsAndCows
 * @date        2016年12月28日 下午10:15:00
 * @details     Solution: AC 2ms 95.65%
 */
public class P299_BullsAndCows {
	static class Solution {
	    public String getHint(String secret, String guess) {
	        if (secret.length() < 1) return "";
	        int[] secretCount = new int[10], guessCount = new int[10];
	        int sameCount = 0;
	        for (int index = 0; index < guess.length(); index ++) {
	        	int c1 = secret.charAt(index) - '0', c2 = guess.charAt(index) - '0';
	        	if (c1 == c2) {
	        		sameCount ++;
	        	} else {
	        		secretCount[c1] ++;
	        		guessCount[c2] ++;
	        	}
	        }
	        int bCount = 0;
	        for (int index = 0; index < secretCount.length; index ++) {
	        	bCount += Math.min(secretCount[index], guessCount[index]);
	        }
	        return sameCount + "A" + bCount + "B";
 	    }
	}
}
