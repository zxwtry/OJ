package leetcode;

import java.util.HashMap;

public class P205_IsomorphicStrings {
	public static void main(String[] args) {
//		String s = "aba";
//		String t = "baa";
//		Solution ss = new Solution();
//		System.out.println(ss.isIsomorphic(s, t));
		
	}
	/*
	 * 	34 ms
	 * 	24.23%
	 */
	static class Solution {
	    public boolean isIsomorphic(String s, String t) {
	    	int len1 = s == null ? 0 : s.length();
	    	int len2 = s == null ? 0 : t.length();
	    	if (len1 != len2) {
	    		return false;
	    	}
	    	HashMap<Character, Character> map1 = new HashMap<>();
	    	HashMap<Character, Character> map2 = new HashMap<>();
	    	for (int i = 0; i < len1; i ++) {
	    		char c1 = s.charAt(i);
	    		char c2 = t.charAt(i);
	    		if (map1.containsKey(c1)) {
	    			if (map1.get(c1) != c2) {
	    				return false;
	    			}
	    		} else {
	    			map1.put(c1, c2);
	    		}
	    		if (map2.containsKey(c2)) {
	    			if (map2.get(c2) != c1) {
	    				return false;
	    			}
	    		} else {
	    			map2.put(c2, c1);
	    		}
	    	}
	        return true;
	    }
	}
}
