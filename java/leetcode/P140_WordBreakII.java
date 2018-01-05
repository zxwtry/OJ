package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
    Given a string s and a dictionary of words dict, 
    add spaces in s to construct a sentence 
    where each word is a valid dictionary word.
	Return all such possible sentences.
	
	For example, given
	s = "catsanddog",
	dict = ["cat", "cats", "and", "sand", "dog"].
	
	A solution is ["cats and dog", "cat sand dog"].
 */


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P140_WordBreakII.java
 * @type        P140_WordBreakII
 * @date        2016年12月29日 下午8:39:47
 * @details     Solution: AC 13ms 86.75%
 */
public class P140_WordBreakII {
	static class Solution {
	    public List<String> wordBreak(String s, List<String> wd) {
	        if (s == null || wd == null) return new ArrayList<>(0); 
	        return search(s, wd, new HashMap<String, ArrayList<String>>());
	    }
        private List<String> search(String s, List<String> wd, HashMap<String, ArrayList<String>> map) {
            if (s.length() == 0) return Arrays.asList("");
            ArrayList<String> ans = map.get(s);
            if (ans != null) return ans;
            ans = new ArrayList<>();
            int sl = s.length();
            for (String w : wd) {
                int wl = w.length();
                if (sl >= wl && s.startsWith(w)) {
                    List<String> sub = search(s.substring(wl), wd, map);
                    String sep = sl == wl ? "" : " ";
                    for (String subS : sub) ans.add(w + sep + subS);
                }
            }
            map.put(s, ans);
            return ans;
        }
	}
}
