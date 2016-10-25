package leetcode;

import java.util.LinkedList;
import java.util.List;

/*
 * 	Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

	Strings consists of lowercase English letters only and the length of both strings s and p
	 will not be larger than 20,100.
	
	The order of output does not matter.
	
	Example 1:
	
	Input:
	s: "cbaebabacd" p: "abc"
	
	Output:
	[0, 6]
	
	Explanation:
	The substring with start index = 0 is "cba", which is an anagram of "abc".
	The substring with start index = 6 is "bac", which is an anagram of "abc".
	Example 2:
	
	Input:
	s: "abab" p: "ab"
	
	Output:
	[0, 1, 2]
	
	Explanation:
	The substring with start index = 0 is "ab", which is an anagram of "ab".
	The substring with start index = 1 is "ba", which is an anagram of "ab".
	The substring with start index = 2 is "ab", which is an anagram of "ab".
 */

public class P438_FindAllAnagramsinaString {
	public static void main(String[] args) {
		String s = "cbaebabacd";
		String p = "abc";
		Solution solution = new Solution();
		System.out.println(solution.findAnagrams(s, p));
	}
	/*
	 * 	AC
	 * 	22 ms
	 */
	static class Solution {
	    public List<Integer> findAnagrams(String s, String p) {
	    	int[] countP = new int[26];
	    	int pLength = p.length();
	        for (int i = 0; i < pLength; i ++) {
	        	countP[p.charAt(i) - 'a'] ++;
	        }
	        List<Integer> ans = new LinkedList<>();
	        int[] countS = new int[26];
	        int sLength = s.length();
	        if (sLength < pLength) {
	        	return ans;
	        }
	        for (int i = 0; i < pLength; i ++) {
	        	countS[s.charAt(i) - 'a'] ++;
	        }
	        int sIndex = 0;
        	boolean isTrue = true;
	        while (true) {
	        	isTrue = true;
	        	for (int i = 0; isTrue && i < 26; i ++) {
	        		isTrue &= countP[i] == countS[i];
	        	}
	        	if (isTrue) {
	        		ans.add(sIndex);
	        	}
	        	if (sIndex >= sLength - pLength) {
	        		break;
	        	}
	        	countS[s.charAt(sIndex) - 'a'] --;
	        	countS[s.charAt(sIndex + pLength) - 'a'] ++;
	        	sIndex ++;
	        }
	        return ans;
	    }
	}
}
