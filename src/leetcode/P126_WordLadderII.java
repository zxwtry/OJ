package leetcode;

import java.util.ArrayList;
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
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P126_WordLadderII.java
 * @type        P126_WordLadderII
 * @date        2016年12月13日 下午5:16:11
 * @details     
 */
public class P126_WordLadderII {
	public static void main(String[] args) {
	    String s = "hit", t = "cog";
	    List<String> w = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
	    Set<String> wl = new HashSet<String>(w);
	    System.out.println(new Solution1().findLadders(s, t, wl));
	}
	
	/**
	 * @author      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     leetcode
	 * @file        P126_WordLadderII.java
	 * @type        Solution1
	 * @date        2016年12月13日 下午5:16:17
	 * @details     AC 132ms 84.85%
	 */
	static class Solution1 {
		List<List<String>> ans;
		LinkedList<String> l;
		Map<String, List<String>> m;
		public List<List<String>> findLadders(String s, String t, Set<String> wl) {
			ans = new LinkedList<List<String>>();
			if (wl.size() == 0) return ans;
			l = new LinkedList<String>();
			m = new HashMap<String, List<String>>();
			int cur = 1, next = 0;
			boolean isFind = false;
			Set<String> nv = new HashSet<String>(wl);		//not  visited
			Set<String> hv = new HashSet<String>();			//have visited
			Queue<String> q = new LinkedList<String>();
			nv.add(t);
			nv.remove(s);
			q.add(s);
			char[] cs = new char[s.length()];
			while (! q.isEmpty()) {
				String w = q.poll();
				cur --;
				for (int i = 0; i < w.length(); i ++) cs[i] = w.charAt(i);
				for (int i = 0; i < w.length(); i ++) {
					for (char c = 'a'; c <= 'z'; c ++) {
						cs[i] = c;
						String nw = new String(cs);
						if (! nv.contains(nw)) {continue;}
						if (hv.add(nw)) {
							next ++;
							q.add(nw);
						}
						if (! m.containsKey(nw))
							m.put(nw, new LinkedList<String>());
						m.get(nw).add(w);
						if (nw.equals(t)) isFind = true;
					}
					cs[i] = w.charAt(i);
				}
				if (cur == 0) {
					if (isFind) break;
					cur = next;
					next = 0;
					nv.removeAll(hv);
					hv.clear();
				}
			}
			System.out.println(m);
			backtrace(t, s);
			return ans;
		}
		private void backtrace(String t, String s) {
			l.addFirst(t);
			if (t.equals(s)) {
				ans.add(new LinkedList<>(l));
				l.removeFirst();
				return;
			}
			if (m.containsKey(t))
				for (String nt : m.get(t))
					backtrace(nt, s);
			l.removeFirst();
		}
	}
	static class Solution2 {
	    public List<List<String>> findLadders(String s, String t, List<String> w) {
	        int wn = w == null ? 0 : w.size();
	        List<List<String>> ans = new ArrayList<List<String>>();
	        if (wn == 0) return ans;
	        int n = w.get(0).length();
	        char[] cs = new char[n];
	        Map<String, ArrayList<String>> m = new HashMap<String, ArrayList<String>>(wn*2);
	        Map<String, Boolean> u = new HashMap<String, Boolean>(wn*2);
	        Set<String> e = new HashSet<String>(w);
	        for (String v : w) {
	            ArrayList<String> l = new ArrayList<String>();
	            for (int i = 0; i < n; i ++) cs[i] = v.charAt(i);
	            for (int i = 0; i < n; i ++) {
    	            for (char c = 'a'; c <= 'z'; c ++) {
    	                if (c == v.charAt(i)) continue;
    	                cs[i] = c;
    	                String g = new String(cs);
    	                if (e.contains(g)) l.add(g);
    	            }
    	            cs[i] = v.charAt(i);
	            }
	            m.put(v, l);
	            u.put(v, false);
	        }
	        String v = s;
            ArrayList<String> l = new ArrayList<String>();
            for (int i = 0; i < n; i ++) cs[i] = v.charAt(i);
            for (int i = 0; i < n; i ++) {
                for (char c = 'a'; c <= 'z'; c ++) {
                    if (c == v.charAt(i)) continue;
                    cs[i] = c;
                    String g = new String(cs);
                    if (e.contains(g)) l.add(g);
                }
                cs[i] = v.charAt(i);
            }
            m.put(v, l);
	        List<String> p = new ArrayList<String>();
	        p.add(s);
	        int[] len = new int[] {Integer.MAX_VALUE};
	        search(p, m, u, t, ans, len); 
	        for (int i = 0; i < ans.size(); i ++)
	            if (ans.get(i).size() > len[0]) {
	                ans.remove(i);
	                i --;
	            }
	        return ans;
	    }

        private void search(List<String> p, Map<String, ArrayList<String>> m, Map<String, Boolean> u, String t,
                List<List<String>> ans, int[] len) {
            int n = p.size();
            if (p.get(n-1).equals(t)) {
                ans.add(new ArrayList<>(p));
                len[0] = Math.min(len[0], p.size());
            }
            if (n >= len[0]) return;
            for (String g : m.get(p.get(n-1))) {
                if (u.get(g)) continue;
                p.add(g);
                u.put(g, true);
                search(p, m, u, t, ans, len);
                p.remove(n);
                u.put(g, false);
            }
        }
	}
}
