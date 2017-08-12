package leetcode;

/*
 * 	Given a string containing just the characters '(' and ')', 
 * 	find the length of the longest valid (well-formed) parentheses substring.

	For "(()", the longest valid parentheses substring is "()", 
	which has length = 2.

	Another example is ")()())", where the longest valid parentheses substring
	 is "()()", which has length = 4.
 */

public class P032_LongestValidParentheses {
    static class Solution {
        public int longestValidParentheses(String s) {
            int sn = s == null ? 0 : s.length();
            int[] arr = new int[sn + 1];
            int ans = 0;
            char pc = '\0';
            for (int i = 1; i < sn; i ++) {
                char c = s.charAt(i);
                if (c == ')' && pc == '(') {
                    arr[i + 1] = Math.max(arr[i - 1] + 2, arr[i + 1]);
                }
                int j = i - arr[i] - 1;
                if (j > -1 && c == ')' && s.charAt(j) == '(') {
                    arr[i + 1] = Math.max(arr[i] + 2 + arr[j], arr[i + 1]);
                }
                ans = Math.max(ans, arr[i + 1]);
                pc = c;
            }
            return ans;
        }
    }
}