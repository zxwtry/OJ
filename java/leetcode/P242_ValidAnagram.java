package leetcode;

/**
 * 	Given two strings s and t, write a function to determine if t is an anagram of s.

	For example,
	s = "anagram", t = "nagaram", return true.
	s = "rat", t = "car", return false.
	
	Note:
	You may assume the string contains only lowercase alphabets.
	
	Follow up:
	What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P242_ValidAnagram.java
 * @type        P242_ValidAnagram
 * @date        2016年12月12日 下午10:01:59
 * @details     Solution: AC 7ms 49.78%
 */
public class P242_ValidAnagram {
	static class Solution {
	    public boolean isAnagram(String s, String t) {
	        if (s == null || t == null) return s==t;
	        if (s.length() != t.length()) return false;
	        int[] map = new int[26];
	        for (int i = s.length() - 1; i > -1; i --) {
	        	map[s.charAt(i) - 'a'] ++;
	        }
	        for (int i = s.length() - 1; i > -1; i --) {
	        	int k = -- map[t.charAt(i) - 'a'];
	        	if (k < 0) return false;
	        }
	        return true;
	    }
	}
}
