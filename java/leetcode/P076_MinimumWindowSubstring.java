package leetcode;

import java.util.HashMap;

/*
 * 	Given a string S and a string T, find the minimum window in S 
 * 	which will contain all the characters in T in complexity O(n).

	For example,
	S = "ADOBECODEBANC"
	T = "ABC"
	Minimum window is "BANC".
	
	Note:
	If there is no such window in S that covers all characters in T, 
	return the empty string "".
	
	If there are multiple such windows, you are guaranteed that 
	there will always be only one unique minimum window in S.
 */

public class P076_MinimumWindowSubstring {
	public static void main(String[] args) {
		System.out.println(new Solution2().minWindow("ADOBECODEBANC", "ABC"));
	}
	static class Solution {
		public String minWindow(String S, String T) {
	        HashMap<Character, Integer> dict = new HashMap<>();
	        for (int i = 0; i < T.length(); i++) {
	            char c = T.charAt(i);
	            if (!dict.containsKey(c))
	                dict.put(c, 1);
	            else
	                dict.put(c, dict.get(c) + 1);
	        }
	        HashMap<Character, Integer> found = new HashMap<>();
	        int foundCounter = 0;
	        int start = 0;
	        int end = 0;
	        int min = Integer.MAX_VALUE;
	        String minWindow = "";
	        while (end < S.length()) {
	            char c = S.charAt(end);
	            if (dict.containsKey(c)) {
	                if (found.containsKey(c)) {
	                    if (found.get(c) < dict.get(c))
	                        foundCounter++;
	                    found.put(c, found.get(c) + 1);
	                } else {
	                    found.put(c, 1);
	                    foundCounter++;
	                }
	            }
	            if (foundCounter == T.length()) {
	                char sc = S.charAt(start);
	                while (!found.containsKey(sc) || found.get(sc) > dict.get(sc)) {
	                    if (found.containsKey(sc) && found.get(sc) > dict.get(sc))
	                        found.put(sc, found.get(sc) - 1);
	                    start++;
	                    sc = S.charAt(start);
	                }
	                if (end - start + 1 < min) {
	                    minWindow = S.substring(start, end + 1);
	                    min = end - start + 1;
	                }
	            }
	            end++;
	        }
	        return minWindow;
	    }
	}
	/*
	 * 	AC
	 * 	82 ms
	 */
	static class Solution2 {
		public String minWindow(String S, String T) {
			String ans = "";
			HashMap<Character, Integer> target = new HashMap<Character, Integer>();
			HashMap<Character, Integer> current = new HashMap<Character, Integer>();
			int sti = 0, eni = 0, min = Integer.MAX_VALUE, cou = 0, lens = S.length(), lent = T.length();
			for (int i = lent - 1; i > -1; i --) {
				char tc = T.charAt(i);
				if (target.containsKey(tc)) {
					target.put(tc, target.get(tc) + 1);
				} else {
					target.put(tc, 1);
				}
			}
			while (eni < lens) {
				char sc = S.charAt(eni);
				if (target.containsKey(sc)) {
					if (current.containsKey(sc)) {
						if (current.get(sc) < target.get(sc))
							cou ++;
						current.put(sc, current.get(sc) + 1);
					} else {
						current.put(sc, 1);
						cou ++;
					}
				}
				if (cou == lent) {
					char s = S.charAt(sti);
					while (! current.containsKey(s) || current.get(s) > target.get(s)) {
						if (current.containsKey(s) && current.get(s) > target.get(s))
							current.put(s, current.get(s) - 1);
						s = S.charAt(++ sti);
					}
					if (eni - sti + 1 < min) {
						ans = S.substring(sti, eni + 1);
						min = eni - sti + 1;
					}
				}
				eni ++;
			}
			return ans;
		}
	}
}
