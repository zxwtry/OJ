package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/*
 * 	Given two words (beginWord and endWord), and a dictionary's word list, 
 * 	find all shortest transformation sequence(s) from beginWord to endWord, such that:

	Only one letter can be changed at a time
	Each intermediate word must exist in the word list
	For example,
	
	Given:
	beginWord = "hit"
	endWord = "cog" 
	wordList = ["hot","dot","dog","lot","log"]
	Return
	  [
	    ["hit","hot","dot","dog","cog"],
	    ["hit","hot","lot","log","cog"]
	  ]
	Note:
	All words have the same length.
	All words contain only lowercase alphabetic characters.
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P126_WordLadderII.java
 * @type        P126_WordLadderII
 * @date        2016年12月13日 下午5:16:11
 * @details     Solution: AC 204ms 65.85%
 */
public class P126_WordLadderII {
	static class Solution {
	    public List<List<String>> findLadders(String s, String t, List<String> w) {
	        List<List<String>> ans = new LinkedList<List<String>>();
	        if (s == null || t == null || w == null) {
	            if (s == null && t == null)
	                ans.add(Arrays.asList((String)null));
	            return ans;
	        }
	        if (s.equals(t)) {
	            ans.add(Arrays.asList(s));
	            return ans;
	        }
	        int sn = s.length();
	        if (w.size() == 0) return ans;
	        HashSet<String> nv = new HashSet<String>(w);
	        HashSet<String> hv = new HashSet<String>();
	        if (! nv.contains(t)) return ans;
	        nv.add(s);
	        Queue<String> q = new LinkedList<String>();
	        q.add(s);
	        HashMap<String, List<String>> m = new HashMap<String, List<String>>();
	        char[] cs = new char[sn];
	        boolean isFind = false;
	        while (! q.isEmpty()) {
	            int size = q.size();
	            while (size -- > 0) {
	                String n = q.poll();
	                for (int i = 0; i < sn; i ++) cs[i] = n.charAt(i);
	                for (int i = 0; i < sn; i ++) {
	                    for (char c = 'a'; c <= 'z'; c ++) {
	                        cs[i] = c;
	                        String nn = new String(cs);
	                        if (! nv.contains(nn)) continue;
	                        if (hv.add(nn)) q.add(nn);
	                        if (! m.containsKey(nn)) m.put(nn, new LinkedList<String>());
	                        m.get(nn).add(n);
	                        if (nn.equals(t)) isFind = true; 
	                    }
	                    cs[i] = n.charAt(i);
	                }
	            }
	            if (isFind) break;
	            nv.removeAll(hv);
	            hv.clear();
	        }
	        find(t, s, new LinkedList<String>(), m, ans);
	        return ans;
	    }
        private void find(String t, String s, LinkedList<String> l, HashMap<String, List<String>> m,
                List<List<String>> ans) {
            l.addFirst(t);
            if (t.equals(s)) {
                ans.add(new LinkedList<String>(l));
            } else {
                List<String> vs = m.get(t);
                if (vs != null) {
                    for (String v : vs) {
                        find(v, s, l, m, ans);
                    }
                }
            }
            l.removeFirst();
        }
	}
	
}
