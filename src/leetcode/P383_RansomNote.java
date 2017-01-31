package leetcode;

import java.util.HashMap;

/**
 * 
 * 	 Given  an  arbitrary  ransom  note  string  and  another  string  containing  letters 
 * 	from  all  the  magazines,  write  a  function  that  will  return  true  if  the  ransom 
 * 	 note  can  be  constructed  from  the  magazines ;  otherwise,  it  will  return  false.   
 * 	 
 * 	 Each  letter  in  the  magazine  string  can  only  be  used  once  in  your  ransom  note.
 * 	 
 * 	 Note:
 * 	 You may assume that both strings contain only lowercase letters.
 * 	 
 * 	 canConstruct("a", "b") -> false
 * 	 canConstruct("aa", "ab") -> false
 * 	 canConstruct("aa", "aab") -> true
 */

public class P383_RansomNote {
	/*
	 * 	69 ms
	 * 	23.14%
	 */
	static class Solution {
	    public boolean canConstruct(String ransomNote, String magazine) {
	    	HashMap<Character, Integer> mapMagazine = new HashMap<>();
	    	if (magazine.length() < ransomNote.length()) {
	    		return false;
	    	}
	    	for (int i = 0; i < magazine.length(); i ++) {
	    		char c = magazine.charAt(i);
	    		if (mapMagazine.containsKey(c)) {
	    			mapMagazine.put(c, mapMagazine.get(c) + 1);
	    		} else {
	    			mapMagazine.put(c, 1);
	    		}
	    	}
	    	for (int i = 0; i < ransomNote.length(); i ++) {
	    		char c = ransomNote.charAt(i);
	    		if (mapMagazine.containsKey(c)) {
	    			int val = mapMagazine.get(c);
	    			if (val <= 0) {
	    				return false;
	    			} else {
	    				mapMagazine.put(c, val - 1);
	    			}
	    		} else {
	    			return false;
	    		}
	    	}
	        return true;
	    }
	}
	/*	
	 * 	18 ms
	 * 	76.97%
	 */
	static class Solution2 {
		public boolean canConstruct(String ransomNote, String magazine) {
			if (ransomNote == null) {
				return true;
			}
			if (magazine == null) {
				return false;
			}
			int lenr = ransomNote.length();
			int lenm = magazine.length();
			if (lenr > lenm) {
				return false;
			}
			int[] mapm = new int[26];
			for (int i = 0; i < lenm; i ++) {
				char c = magazine.charAt(i);
				mapm[(int) (c - 'a')]  ++;
			}
			for (int i = 0; i < lenr; i ++) {
				char c = ransomNote.charAt(i);
				if (mapm[(int) (c - 'a')] <= 0) {
					return false;
				} else {
					mapm[(int) (c - 'a')] --;
				}
			}
			return true;
		}
	}
}
