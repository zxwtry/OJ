package leetcode;

import java.util.ArrayList;

/**
 * 	Given a string that consists of only uppercase English letters, 
 * 	you can replace any letter in the string with another letter at most k times. 
 * 	Find the length of a longest substring containing all repeating letters
 * 	 you can get after performing the above operations.
 *	
 *	Note:
 *	Both the string's length and k will not exceed 104.
 *	
 *	Example 1:
 *	
 *	Input:
 *	s = "ABAB", k = 2
 *	
 *	Output:
 *	4
 *	
 *	Explanation:
 *	Replace the two 'A's with two 'B's or vice versa.
 *	Example 2:
 *	
 *	Input:
 *	s = "AABABBA", k = 1
 *	
 *	Output:
 *	4
 *	
 *	Explanation:
 *	Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 *	The substring "BBBB" has the longest repeating letters, which is 4.
 */

public class P424_LongestRepeatingCharacterReplacement {
	/*
	 * 	AC
	 * 	242 ms
	 */
	static class Solution {
	    public int characterReplacement(String s, int k) {
	    	if (s == null || s.length() == 0) {
	    		return 0;
	    	}
	    	if (s.length() == 1) {
	    		return 1;
	    	}
	    	if ( k >= s.length() - 1) {
	    		return s.length();
	    	}
	    	char[] cs = s.toCharArray();
	    	@SuppressWarnings("unchecked")
			ArrayList<Integer>[] startIndices = new ArrayList[26];
	    	@SuppressWarnings("unchecked")
			ArrayList<Integer>[] endIndices = new ArrayList[26];
	    	char repeatChar = cs[0];
	    	int preIndex = 0;
	    	int ans = 1;
	    	for (int i = 1; i <= cs.length; i ++) {
	    		if (i == cs.length || repeatChar != cs[i]) {
	    			int listsIndex = repeatChar - 'A';
	    			if (startIndices[listsIndex] == null) {
	    				startIndices[listsIndex] = new ArrayList<>();
	    				endIndices[listsIndex] = new ArrayList<>();
	    			}
	    			startIndices[listsIndex].add(preIndex);
	    			endIndices[listsIndex].add(i - 1);
	    			ans = Math.max(ans, i - preIndex);
	    			if (i != cs.length) {
		    			repeatChar = cs[i];
		    			preIndex = i;
	    			}
	    		}
	    	}
	    	int[] help = new int[cs.length];
	    	for (int i = 0; i < 26; i ++) {
	    		if (ans >= cs.length) {
	    			return cs.length;
	    		}
	    		if (startIndices[i] != null) {
	    			ArrayList<Integer> startIndex = startIndices[i];
	    			ArrayList<Integer> endIndex = endIndices[i];
	    			int lenOfIndex = startIndex.size();
	    			for (int indexOfIndex = 0; indexOfIndex < lenOfIndex; indexOfIndex ++) {
	    				help[indexOfIndex] = k;
	    				int ansNow = endIndex.get(indexOfIndex) - startIndex.get(indexOfIndex) + 1;
	    				int jndexOfIndex = indexOfIndex + 1;
	    				for (; jndexOfIndex < lenOfIndex; ++ jndexOfIndex) {
	    					help[jndexOfIndex] = help[jndexOfIndex - 1] - (startIndex.get(jndexOfIndex) - endIndex.get(jndexOfIndex - 1) - 1);
	    					if (help[jndexOfIndex] < 0) {
	    						ansNow += help[jndexOfIndex - 1];
	    						ans = Math.max(ans, ansNow);
	    						break;
	    					}
	    					ansNow += help[jndexOfIndex - 1] - help[jndexOfIndex];
	    					ansNow += endIndex.get(jndexOfIndex) - startIndex.get(jndexOfIndex) + 1;
	    				}
	    				if (jndexOfIndex == lenOfIndex && help[lenOfIndex - 1] > 0) {
	    					ansNow += help[lenOfIndex - 1];
	    				}
	    				ans = Math.max(ans, ansNow);
	    			}
	    		}
//	    		if (startIndices[i] != null) {
//	    			ArrayList<Integer> startIndex = startIndices[i];
//	    			ArrayList<Integer> endIndex = endIndices[i];
//	    			int len = startIndex.size();
//	    			help[0] = k;
//	    			int ansCount = endIndex.get(0) - startIndex.get(0) + 1;
//	    			ans = Math.max(ans, ansCount);
//	    			preIndex = 0;
//	    			for (int iLen = 1; iLen < len; iLen ++) {
//	    				int cut = startIndex.get(iLen) - endIndex.get(iLen - 1) - 1;
//	    				if (help[iLen - 1] < cut) {
//	    					if (startIndex.get(iLen) != cs.length - 1) {
//	    						help[iLen] = k;
//	    					} else {
//	    						help[iLen] = 0;
//	    					}
//	    					ansCount += help[iLen - 1];
//	    					help[iLen - 1] = 0;
//	    					ans = Math.max(ans, ansCount);
//	    					preIndex = iLen;
//	    					ansCount = endIndex.get(iLen) - startIndex.get(iLen) + 1;
//	    					ans = Math.max(ans, ansCount);
//	    				} else {
//	    					help[iLen] = help[iLen - 1] - cut;
//	    					ansCount += endIndex.get(iLen) - endIndex.get(iLen - 1);
//	    					ans = Math.max(ans, ansCount);
//	    				}
//	    			}
//	    			if (help[len - 1] > 0) {
//	    				int newAnsCountAdd = Math.min(help[len - 1], cs.length - 1 - endIndex.get(len - 1));
//	    				help[len - 1] -= newAnsCountAdd;
//	    				ansCount += newAnsCountAdd;
//	    				ans = Math.max(ans, ansCount);
//	    			}
//	    			if (help[len - 1] > 0) {
//	    				if (startIndex.get(preIndex) != 0) {
//		    				ansCount += help[len - 1];
//		    			}
//	    			}
//	    			ans = Math.max(ans, ansCount);
//	    		}
	    	}
	    	return ans > cs.length ? cs.length : ans;
	    }
	}
}
