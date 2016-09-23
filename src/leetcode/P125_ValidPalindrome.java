package leetcode;

/*
 * 	Given a string, determine if it is a palindrome, 
 * considering only alphanumeric characters and ignoring cases.

	For example,
	"A man, a plan, a canal: Panama" is a palindrome.
	"race a car" is not a palindrome.
	
	Note:
	Have you consider that the string might be empty? 
	This is a good question to ask during an interview.
	
	For the purpose of this problem, we define empty string as valid palindrome.
 */

public class P125_ValidPalindrome {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.isPalindrome("A man, a plan, a canal: Panama"));
		System.out.println(s.isPalindrome("race a car"));
//		System.out.println(s.isPalindrome(""));
//		System.out.println(s.isPalindrome("a"));
//		System.out.println(s.isPalindrome("ab"));
//		System.out.println(s.isPalindrome("abc"));
//		System.out.println(s.isPalindrome("abba"));
//		System.out.println(s.isPalindrome("aef"));
	}
	/*
	 * 	5 ms
	 * 	96.22%
	 */
	static class Solution {
	    public boolean isPalindrome(String s) {
	    	int len = 0;
	    	if (s == null || (len = s.length()) == 0) {
	    		return true;
	    	}
	    	int i = 0, j = len - 1;
	    	while (i <= j) {
	    		char ic = s.charAt(i);
	    		if ( ! ( (ic >= '0' && ic <= '9') || (ic  >= 'A' && ic <= 'Z') || (ic >= 'a' && ic <= 'z') ) ) {
	    			i ++;
	    			continue;
	    		}
	    		char jc = s.charAt(j);
	    		if ( ! ( (jc >= '0' && jc <= '9') || (jc  >= 'A' && jc <= 'Z') || (jc >= 'a' && jc <= 'z') ) ) {
	    			j --;
	    			continue;
	    		}
	    		if (ic == jc || (ic  >= 'A' && ic <= 'Z' && ic + 32 == jc) || (jc  >= 'A' && jc <= 'Z' && jc + 32 == ic)) {
		    		i ++;
		    		j --;
	    		} else {
	    			return false;
	    		}
	    	}
	    	return true;
	    }
	}
}
