package leetcode;

/*
 * 	Given a non-empty string check if it can be constructed by 
 * 	taking a substring of it and appending multiple copies of the substring together. 
 * 	You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

	Example 1:
	Input: "abab"
	
	Output: True
	
	Explanation: It's the substring "ab" twice.
	Example 2:
	Input: "aba"
	
	Output: False
	Example 3:
	Input: "abcabcabcabc"
	
	Output: True
	
	Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */

public class P459_RepeatedSubstringPattern {
	public static void main(String[] args) {
		debugSolution();
	}
	static void testSolution() {
		
	}
	static void debugSolution() {
		String str = "a";
		Solution s = new Solution();
		System.out.println(s.repeatedSubstringPattern(str));
	}
	/*
	 * 	AC
	 * 	19 ms
	 */
	static class Solution {
	    public boolean repeatedSubstringPattern(String str) {
	        if (str == null || str.length() < 1) {
	        	return true;
	        }
	        int len = str.length();
	        char[] cs = str.toCharArray();
	        int[] map = new int[26];
	        for (int indexOfCs = 0; indexOfCs < cs.length; indexOfCs ++) {
	        	map[cs[indexOfCs]-'a'] ++;
	        }
	        boolean ans = false;
	        for (int d = 1; ! ans && d + d <= len; d ++) {
	        	if (len % d == 0) {
	        		int times = len / d;
	        		boolean isMatch = true;
	        		for (int indexOfMap = 0; isMatch && indexOfMap < map.length; indexOfMap ++) {
	        			isMatch &= (map[indexOfMap] % times == 0);
	        		}
	        		if (isMatch) {
	        			for (int i = 0; isMatch && i < d; i ++) {
	        				char c = cs[i];
	        				for (int j = 1; isMatch && j < times; j ++) {
	        					isMatch &= c == cs[j * d + i];
	        				}
	        			}
	        			ans |= isMatch;
	        		}
	        	}
	        }
	        return ans;
	    }
	}
}