package leetcode;

/*
 * 	Given an array of strings, group anagrams together.

	For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
	Return:
	
	[
	  ["ate", "eat","tea"],
	  ["nat","tan"],
	  ["bat"]
	]
	Note: All inputs will be in lower-case.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class P049_GroupAnagrams {
	public static void main(String[] args) {
		System.out.println(new Solution1().groupAnagrams(new String[] {
				"eat", "tea", "tan", "ate", "nat", "bat"
		}));
	}
	/*
	 * 	20 ms
	 * 	75.05% 
	 */
	static class Solution1 {
		List<List<String>> ans = new ArrayList<List<String>>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int count = -1;
	    public List<List<String>> groupAnagrams(String[] strs) {
	        if (strs == null || strs.length < 1)
	        	return ans;
	        char[] cs = null;
	        for (int i = 0; i != strs.length; i ++) {
	        	cs = strs[i].toCharArray();
	        	Arrays.sort(cs);
	        	String temp = new String(cs);
	        	if (map.containsKey(temp)) {
	        		ans.get(map.get(temp)).add(strs[i]);
	        	} else {
	        		map.put(temp, ++ count);
	        		List<String> answer = new LinkedList<String>();
	        		answer.add(strs[i]);
	        		ans.add(answer);
	        	}
	        }
	        return ans;
	    }
	}
}
