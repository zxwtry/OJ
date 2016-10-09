package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
 * 	All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, 
 * 	for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify
 * 	repeated sequences within the DNA.

	Write a function to find all the 10-letter-long sequences (substrings) that
	occur more than once in a DNA molecule.
	
	For example,
	
	Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
	
	Return:
	["AAAAACCCCC", "CCCCCAAAAA"].
 */

public class P187_RepeatedDNASequences {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
	}
	/*
	 * 	61 ms
	 * 	39.03%
	 */
	static class Solution {
	    public List<String> findRepeatedDnaSequences(String s) {
			List<String> ans = new LinkedList<String>();
			HashMap<String, Integer> map = new HashMap<>();
	    	if (s == null || s.length() < 10) {
	    		return ans;
	    	}
	    	char[] cs = s.toCharArray();
	    	for (int i = 9; i < cs.length; i ++) {
	    		String thisStr = new String(cs, i - 9, 10);
	    		if (map.containsKey(thisStr)) {
	    			if (map.get(thisStr) == 1) {
	    				ans.add(thisStr);
	    				map.put(thisStr, 2);
	    			}
	    		} else {
	    			map.put(thisStr, 1);
	    		}
	    	}
	        return ans;
	    }
	}
}
