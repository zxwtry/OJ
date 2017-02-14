package leetcode;

import java.util.HashMap;

/**
 * 	Given a pattern and a string str, find if str follows the same pattern.
 * 
 * 	Here follow means a full match, such that there is a bijection between 
 * 	a letter in pattern and a non-empty word in str.
 * 	
 * 	Examples:
 * 	pattern = "abba", str = "dog cat cat dog" should return true.
 * 	pattern = "abba", str = "dog cat cat fish" should return false.
 * 	pattern = "aaaa", str = "dog cat cat dog" should return false.
 * 	pattern = "abba", str = "dog dog dog dog" should return false.
 * 	Notes:
 * 	You may assume pattern contains only lowercase letters, and str 
 * 	contains lowercase letters separated by a single space
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P290_WordPattern.java
 * @type        P290_WordPattern
 * @date        2016年12月17日 下午10:29:25
 * @details     Solution1: AC 2ms 35.81%
 */
public class P290_WordPattern {
	static class Solution1 {
	    public boolean wordPattern(String pattern, String str) {
	        if ((pattern == null || pattern.length() < 1) && (str == null || str.length() < 1)) return true;
	        String[] strSplits = str.split(" ");
	        if (pattern.length() != strSplits.length) return false;
	        HashMap<Character, String> map = new HashMap<Character, String>();
	        HashMap<String, Character> mapAdd = new HashMap<String, Character>();
	        for (int index = 0; index < strSplits.length; index ++) {
	        	String value = map.get(pattern.charAt(index));
	        	if (value != null && ! value.equals(strSplits[index])) return false;
	        	map.put(pattern.charAt(index), strSplits[index]);
	        	Character valueAdd = mapAdd.get(strSplits[index]);
	        	if (valueAdd != null && valueAdd != pattern.charAt(index)) return false;
	        	mapAdd.put(strSplits[index], pattern.charAt(index));
	        }
	        return true;
	    }
	}
}
