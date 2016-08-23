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
	public static void main(String[] args) {
//		System.out.println(new Solution().isValid("([{}()])"));
//		System.out.println(new Solution().isValid("()()()([)(])"));
		System.out.println(new Solution().isValid(")("));
	}
	/*
	 * 	1ms
	 * 	52.87%
	 */
	static class Solution {
	    public boolean isValid(String s) {
	        StringBuilder st = new StringBuilder(s);
	        for (int i = st.length() - 1; i > 0; i --) {
	        	if (judge(st.charAt(i - 1), st.charAt(i))) {
	        		st.delete(i - 1, i + 1);
	        		i = st.length();
	        	}
	        }
	        return st.length() == 0;
	    }
		private boolean judge(char c1, char c2) {
			switch (c1) {
			case '(':
				if (c2 == ')')
					return true;
				break;
			case '[':
				if (c2 == ']')
					return true;
				break;
			case '{':
				if (c2 == '}')
					return true;
				break;
			default:
				break;
			}
			return false;
		}
	}
}
