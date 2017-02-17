package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 	Remove the minimum number of invalid parentheses in order to make the 
 * 	input string valid. Return all possible results.
 *	
 *	Note: The input string may contain letters other than the parentheses ( and ).
 *	
 *	Examples:
 *	"()())()" -> ["()()()", "(())()"]
 *	"(a)())()" -> ["(a)()()", "(a())()"]
 *	")(" -> [""]
 *	Credits:
 *	Special thanks to @hpplayer for adding this problem and creating all test cases.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P301_RemoveInvalidParentheses.java
 * @type        P301_RemoveInvalidParentheses
 * @date        2016年12月28日 下午10:22:08
 * @details     Solution1: AC 4ms 69.26%
 * @details     Solution1修改后: AC 3ms 75.05%
 * @details     Solution2: AC 3ms 75.05%
 */
public class P301_RemoveInvalidParentheses {
	static class Solution1 {
		public List<String> removeInvalidParentheses(String s) {
		    List<String> ans = new LinkedList<>();
		    remove(s, ans, 0, 0, new char[]{'(', ')'});
		    return ans;
		}
		public void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
			int stack = 0;
		    for (int i = last_i; i < s.length(); ++i) {
		        if (s.charAt(i) == par[0]) stack++;
		        if (s.charAt(i) == par[1]) stack--;
		        if (stack >= 0) continue;
		        for (int j = last_j; j <= i; ++j)
		            if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
		                remove(removeCharAtJ(s, j), ans, i, j, par);
		        return;
		    }
		    if (par[0] == '(') // finished left to right
		        remove(reverse(s), ans, 0, 0, new char[]{')', '('});
		    else // finished right to left
		        ans.add(reverse(s));
		}
		private String reverse(String string) {
			char[] cs = new char[string.length()];
			int startIndex = 0, endIndex = string.length() - 1;
			while (startIndex <= endIndex) {
				cs[startIndex] = string.charAt(endIndex);
				cs[endIndex] = string.charAt(startIndex);
				startIndex ++;
				endIndex --;
			}
			return new String(cs);
		}
		private String removeCharAtJ(String string, int j) {
			char[] cs = new char[string.length() - 1];
			int csIndex = 0;
			for (int index = 0; index < string.length(); index ++) {
				if (index == j) continue;
				cs[csIndex ++] = string.charAt(index);
			}
			return new String(cs);
		}
	}
	static class Solution2 {
		public List<String> removeInvalidParentheses(String s) {
			List<String> answerList = new LinkedList<String>();
			if (s == null || s.length() < 1) {
				answerList.add("");
				return answerList; 
			}
			removeInvalidParenthesesInternal(s, answerList, 0, 0, new char[] {'(', ')'});
			return answerList;
		}
		private void removeInvalidParenthesesInternal(String string, List<String> answerList,
				int checkedIndex, int removedIndex, char[] compareParentheses) {
			int compareParentheseCount = 0;
			for (int checkIndex = checkedIndex; checkIndex < string.length(); checkIndex ++) {
				if (string.charAt(checkIndex) == compareParentheses[0]) compareParentheseCount ++;
				if (string.charAt(checkIndex) == compareParentheses[1]) compareParentheseCount --;
				if (compareParentheseCount >= 0) continue;
				for (int removeIndex = removedIndex; removeIndex <= checkIndex; removeIndex ++) {
					if (string.charAt(removeIndex) == compareParentheses[1] && 
							(removeIndex == removedIndex || string.charAt(removeIndex - 1) != compareParentheses[1]))
						removeInvalidParenthesesInternal(removeCharAtIndex(string, removeIndex),
								answerList, checkIndex, removeIndex, compareParentheses);
				}
				return;
			}
			if (compareParentheses[0] == '(')
				removeInvalidParenthesesInternal(reverse(string), answerList, 0, 0, new char[] {')', '('});
			else
				answerList.add(reverse(string));
		}
		private String reverse(String string) {
			int startIndex = 0, endIndex = string.length() - 1;
			char[] cs = new char[string.length()];
			while (startIndex <= endIndex) {
				cs[startIndex] = string.charAt(endIndex);
				cs[endIndex] = string.charAt(startIndex);
				startIndex ++;
				endIndex --;
			}
			return new String(cs);
		}
		private String removeCharAtIndex(String string, int index) {
			char[] cs = new char[string.length() - 1];
			int csIndex = 0;
			for (int stringIndex = 0; stringIndex <= cs.length; stringIndex ++) {
				if (stringIndex != index)
					cs[csIndex ++] = string.charAt(stringIndex); 
			}
			return new String(cs);
		}
	}
}