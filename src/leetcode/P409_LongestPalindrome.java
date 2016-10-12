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
		System.out.println(new Solution().longestPalindrome("ddd"));
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
}
