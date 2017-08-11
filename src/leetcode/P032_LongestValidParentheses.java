package leetcode;

import java.util.Stack;

/*
 * 	Given a string containing just the characters '(' and ')', 
 * 	find the length of the longest valid (well-formed) parentheses substring.

	For "(()", the longest valid parentheses substring is "()", 
	which has length = 2.

	Another example is ")()())", where the longest valid parentheses substring
	 is "()()", which has length = 4.
 */

public class P032_LongestValidParentheses {
	static class Solution1 {
	    public int longestValidParentheses(String s) {
	    	if (s == null)
	    		return 0;
	    	boolean[] isMatch = new boolean[s.length()];
	    	Stack<Integer> stack = new Stack<Integer>();
	    	int ans = 0, i = 0, cou = 0, left = 0, right = 0;
	    	for (i = 0; i != isMatch.length; i ++) {
	    		if (s.charAt(i) == '(') {
	    			left ++;
	    		} else {
	    			right ++;
	    		}
	    	}
	    	if (right == 0 || left == 0)
	    		return 0;
	    	if ((left - 1) * (right - 1) == 0 && s.charAt(0) == '(')
	    		return 2;
	    	for (i = 0; i != isMatch.length; i ++) {
	    		if (s.charAt(i) == '(') {
	    			stack.add(i);
	    		} else {
	    			if (stack.isEmpty())
	    				continue;
	    			isMatch[i] = true;
	    			isMatch[stack.peek()] = true;
	    			stack.pop();
	    		}
	    	}
	    	for (i = 0; i!= isMatch.length; i ++) {
	    		if(isMatch[i])	++cou;
	    		else	cou = 0;
	    		ans = Math.max(ans, cou);
	    	}
	    	return ans;
	    }
	}
}