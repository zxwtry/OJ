package leetcode;

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
}
