package leetcode;

import java.util.ArrayList;
import java.util.List;
/*
 * Given a sequence of words, check whether it forms a valid word square.
	
	A sequence of words forms a valid word square if the kth row and column read the exact same string, 
	where 0 ≤ k < max(numRows, numColumns).
	
	Note:
	The number of words given is at least 1 and does not exceed 500.
	Word length will be at least 1 and does not exceed 500.
	Each word contains only lowercase English alphabet a-z.
	Example 1:
	
	Input:
	[
	  "abcd",
	  "bnrt",
	  "crmy",
	  "dtye"
	]
	
	Output:
	true
	
	Explanation:
	The first row and first column both read "abcd".
	The second row and second column both read "bnrt".
	The third row and third column both read "crmy".
	The fourth row and fourth column both read "dtye".
	
	Therefore, it is a valid word square.
	Example 2:
	
	Input:
	[
	  "abcd",
	  "bnrt",
	  "crm",
	  "dt"
	]
	
	Output:
	true
	
	Explanation:
	The first row and first column both read "abcd".
	The second row and second column both read "bnrt".
	The third row and third column both read "crm".
	The fourth row and fourth column both read "dt".
	
	Therefore, it is a valid word square.
	Example 3:
	
	Input:
	[
	  "ball",
	  "area",
	  "read",
	  "lady"
	]
	
	Output:
	false
	
	Explanation:
	The third row reads "read" while the third column reads "lead".
	
	Therefore, it is NOT a valid word square.
 * 
 * 
 */

public class P422_ValidWordSquare {
	public static void main(String[] args) {
		ArrayList<String> words = new ArrayList<>();
//		words.add("abcd");
//		words.add("bnrt");
//		words.add("crm");
//		words.add("dt");
		words.add("abcd");
		words.add("bnrt");
		words.add("crmy");
		words.add("dtye");
//		words.add("ball");
//		words.add("area");
//		words.add("read");
//		words.add("lady");
		Solution s = new Solution();
		System.out.println(s.validWordSquare(words));
	}
	/*
	 * 	AC
	 * 	22 ms
	 */
	static class Solution {
	    public boolean validWordSquare(List<String> words) {
	    	if (words == null ||  words.size() == 0) {
	    		return true;
	    	}
	        int maxCol = 0;
	        int maxRow = words.size();
	        for (String word : words) {
	        	maxCol = Math.max(maxCol, word.length());
	        }
	        if (maxCol != maxRow) {
	        	return false;
	        }
	        char[] cs = new char[maxRow];
	        int csIndex = 0;
	        boolean answer = true;
	        for (int i = maxRow - 1; answer && i > -1; i --) {
	        	csIndex = 0;
	        	for (int j = 0; j < maxRow; j ++) {
	        		if (i < words.get(j).length()) {
	        			cs[csIndex++] = words.get(j).charAt(i);
	        		}
	        	}
	        	String temp = new String(cs, 0, csIndex);
	        	if (! temp.equals(words.get(i))) {
	        		answer = false;
	        	}
	        }
	        return answer;
	    }
	}
}
