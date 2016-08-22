package leetcode;

import java.util.LinkedList;
import java.util.List;
/*
 * 	Given a digit string, return all possible letter combinations 
 * 	that the number could represent.
 * 
	A mapping of digit to letters (just like on the telephone buttons)
	 is given below.
	 
	Input:Digit string "23"
	Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */


public class P017_LetterCombinationsOfAPhoneNumb {
	public static void main(String[] args) {
		System.out.println(new Solution().letterCombinations(""));
	}
	/*
	 * 	2 ms
	 * 	12.81%
	 */
	static class Solution {
		char tran[][] = {
			{'a', 'b', 'c'},
			{'d', 'e', 'f'},
			{'g', 'h', 'i'},
			{'j', 'k', 'l'},
			{'m', 'n', 'o'},
			{'p', 'q', 'r', 's'},
			{'t', 'u', 'v'},
			{'w', 'x', 'y', 'z'}
		};
		StringBuilder st = new StringBuilder();
    	List<String> ans = new LinkedList<String>();
	    public List<String> letterCombinations(String digits) {
	    	if (digits == null || digits.length() == 0)
	    		return ans;
	    	char[] cs = digits.toCharArray();
	    	dfs(cs, 0);
	    	return ans;
	    }
	    private void dfs(char[] cs, int i) {
	    	if (i == cs.length) {
	    		ans.add(st.toString());
	    		return;
	    	}
	    	int tran_i = cs[i] - '2';
	    	for (int tran_j = 0; tran_j != tran[tran_i].length; tran_j ++) {
	    		st.append(tran[tran_i][tran_j]);
	    		dfs(cs, i + 1);
	    		st.deleteCharAt(st.length() - 1);
	    	}
	    }
	}
}
