package leetcode;

import java.util.HashMap;
import java.util.Set;

/*
    Given a string s and a dictionary of words dict, 
    determine if s can be segmented into a space-separated 
    sequence of one or more dictionary words.

	For example, given
	s = "leetcode",
	dict = ["leet", "code"].
	
	Return true because "leetcode" can be segmented as "leet code".
 */

public class P139_WordBreak {
	/*
	 * 	14.05%
	 * 	16 ms
	 */
	static class Solution {
		HashMap<String, Boolean> map = new HashMap<>();
	    public boolean wordBreak(String s, Set<String> wordDict) {
	    	if (s == null || s.length() == 0 || wordDict.size() == 0) {
	    		return false;
	    	}
	    	char[] cs = s.toCharArray();
	    	for (String str : wordDict) {
	    		map.put(str, true);
	    	}
	    	return wordBreak(cs, 0, cs.length - 1, wordDict);
	    }
		private boolean wordBreak(char[] cs, int i, int j, Set<String> wordDict) {
			if (i == cs.length) {
				return true;
			}
			String all = new String(cs, i, j + 1 - i);
			if (map.containsKey(all)) {
				if (! map.get(all)) {
					return false;
				}
			}
			boolean isTrue = false;
			for (int k = i; k <= j; k ++) {
				String this_part = new String(cs, i, k + 1 - i);
				if (wordDict.contains(this_part) || ((map.containsKey(this_part) && map.get(this_part)))) {
					isTrue |= wordBreak(cs, k + 1, j, wordDict);
				}
				if (isTrue) {
					break;
				}
			}
			map.put(new String(cs, i, j + 1 - i), isTrue);
			return isTrue;
		}
	}
}
