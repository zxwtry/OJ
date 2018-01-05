package leetcode;

/*
    Given a string, determine if it is a palindrome, 
    considering only alphanumeric characters and ignoring cases.

	For example,
	"A man, a plan, a canal: Panama" is a palindrome.
	"race a car" is not a palindrome.
	
	Note:
	Have you consider that the string might be empty? 
	This is a good question to ask during an interview.
	
	For the purpose of this problem, we define empty string as valid palindrome.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P125_ValidPalindrome.java
 * @type        P125_ValidPalindrome
 * @date        2017年5月7日 下午9:53:18
 * @details     Solution: AC 5ms 97.89%
 */
public class P125_ValidPalindrome {
	static class Solution {
	    private char get(String s, int i) {
	        char c = s.charAt(i);
	        if (c >= 'A' && c <= 'Z') c -= 'A'-'a';
	        return c;
	    }
	    private boolean cmp(char c) {
	        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z');
	    }
	    public boolean isPalindrome(String s) {
	        int i = 0, j = s == null ? -1 : s.length()-1;
	        while (i < j) {
	            char l = get(s, i);
	            if (! cmp(l)) {
	                i ++;
	            } else {
	                char r = get(s, j);
	                if (! cmp(r)) {
	                    j --;
	                } else if (l == r) {
	                    i ++;
	                    j --;
	                } else return false;
	            }
	        }
	        return true;
	    }
	}
}
