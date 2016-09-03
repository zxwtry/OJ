package leetcode;

/*
 * 	Given a string s consists of upper/lower-case alphabets 
 * 	and empty space characters ' ', return the length of last word in the string.

	If the last word does not exist, return 0.
	
	Note: A word is defined as a character sequence consists of non-space characters only.
	
	For example, 
	Given s = "Hello World",
	return 5.
 */

public class P058_LengthOfLastWord {
	public static void main(String[] args) {
		System.out.println(new Solution().lengthOfLastWord(" "));
	}
	/*
	 * 	1 ms
	 * 	43.86% 
	 */
	static class Solution {
	    public int lengthOfLastWord(String s) {
	    	int len = 0;
	    	if (s == null || (len = s.length()) == 0)
	    		return 0;
	    	boolean start = false;
	    	for (int i = len - 1; i != -1; i --) {
	    		char c = s.charAt(i);
	    		if (c != ' ') {
	    			if (! start) {
	    				len = i;
	    				start = true;
	    			}
	    		} else {
	    			if (start)
	    				return len - i;
	    		}
	    	}
	        return start ? len + 1 : 0;
	    }
	}
}
