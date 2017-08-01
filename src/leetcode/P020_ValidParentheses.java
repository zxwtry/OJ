package leetcode;

/*
 * 	20. Valid Parentheses  QuestionEditorial Solution  My Submissions
	Total Accepted: 125821
	Total Submissions: 411392
	Difficulty: Easy
	Given a string containing just the characters
	 '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
	The brackets must close in the correct order,
	 "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
	Subscribe to see which companies asked this question
 */

public class P020_ValidParentheses {
	static class Solution {
	    public boolean isValid(String s) {
	        int sn = s == null ? 0 : s.length();
	        if (sn < 2) return sn == 0;
	        char[] stk = new char[sn];
	        int top = -1;
	        for (int i = 0; i < sn; i ++) {
	            char c = s.charAt(i);
	            if (c == '(' || c == '[' || c == '{') {
	                stk[++ top] = c;
	                continue;
	            }
	            if (top == -1) return false;
	            char t = stk[top --];
	            if ( (c == ')' && t == '(') || 
	                    (c == ']' && t == '[') ||
	                    (c == '}' && t == '{') ) {}
	            else return false;
	        }
	        return top == -1;
	    }
	}
}
