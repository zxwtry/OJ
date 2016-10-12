package leetcode;

import java.util.HashMap;
import java.util.Map;

/*
 * 	Given a string which consists of lowercase or uppercase letters, 
 * 	find the length of the longest palindromes that can be built with those letters.

	This is case sensitive, for example "Aa" is not considered a palindrome here.
	
	Note:
	Assume the length of given string will not exceed 1,010.
	
	Example:
	
	Input:
	"abccccdd"
	
	Output:
	7
	
	Explanation:
	One longest palindrome that can be built is "dccaccd", whose length is 7.
 */

public class P409_LongestPalindrome {
	public static void main(String[] args) {
		System.out.println(new Solution2().longestPalindrome("aaaAaaaa"));
//		System.out.println((int)'A');
	}
	/*
	 * 	24 ms
	 * 	AC
	 */
	static class Solution {
	    public int longestPalindrome(String s) {
	        int ans = 0;
	        HashMap<Character, Integer> map = new HashMap<>();
	        int sLen = s.length();
	        for (int i = 0; i < sLen; i ++) {
	        	char c = s.charAt(i);
	        	if (map.containsKey(c)) {
	        		map.put(c, map.get(c) + 1);
	        	} else {
	        		map.put(c, 1);
	        	}
	        }
	        boolean isExistSingle = false;
	        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
	        	int val = entry.getValue();
	        	if (val % 2 == 1) {
	        		isExistSingle = true;
	        	}
        		ans += (val / 2) * 2;
	        }
	        if (isExistSingle) {
	        	ans ++;
	        }
	        return ans;
	    }
	}
	/*
	 * 	15 ms
	 * 	AC
	 */
	static class Solution2 {
		int[] map = new int[52];
	    public int longestPalindrome(String s) {
	    	for (int i = 0; i < s.length(); i ++) {
	    		char c = s.charAt(i);
	    		map[getIndex(c)] ++;
	    	}
	    	int ans = 0;
	    	boolean isSingleExists = false;
	    	for (int i = 0; i < map.length; i ++) {
	    		if (map[i] > 0) {
	    			if (map[i] % 2 == 1) {
	    				isSingleExists = true;
	    			}
	    			ans += (map[i] / 2) * 2;
	    		}
	    	}
	    	if (isSingleExists) {
	    		ans ++;
	    	}
	    	return ans;
	    }
	    int getIndex(char c) {
	    	int index = c - 'A';
	    	if (index > 25) {
	    		index = c - 'a' + 26;
	    	}
	    	return index;
	    }
	}
}
