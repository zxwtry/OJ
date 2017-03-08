package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 	Given a list of words, please write a program that returns all concatenated words
 * 	in the given list of words.
 *	
 *	A concatenated word is defined as a string that is comprised entirely of at least
 *	 two shorter words in the given array.
 *	
 *	Example:
 *	Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat",
 *	"ratcatdogcat"]
 *	
 *	Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 *	
 *	Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
 *	 "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
 *	"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 *	Note:
 *	The number of elements of the given array will not exceed 10,000
 *	The length sum of elements in the given array will not exceed 600,000.
 *	The returned elements order does not matter.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P472_ConcatenatedWords.java
 * @type        P472_ConcatenatedWords
 * @date        2016年12月18日 上午11:40:43
 * @details     Solution1: MLE
 * @details     Solution2: TLE
 * @details     Solution3: AC 130ms 79.92%
 */
public class P472_ConcatenatedWords {
	static class Solution1 {
		public List<String> findAllConcatenatedWordsInADict(String[] words) {
			for (String w : words) {
				root.insert(w);
			}
			root.suffix = root;
			Queue<TrieNode> trieNodeQueue = new LinkedList<>();
			for (int index = 0; index < 26; index ++) {
				if (root.nexts[index] == null) {
					root.nexts[index] = root;
				} else {
					root.nexts[index].suffix = root;
					trieNodeQueue.add(root.nexts[index]);
				}
			}
			TrieNode rootNow = null;
			while (! trieNodeQueue.isEmpty()) {
				rootNow = trieNodeQueue.poll();
				for (int index = 0; index < 26; index ++) {
					if (rootNow.nexts[index] == null) {
						rootNow.nexts[index] = rootNow.suffix.nexts[index];
					} else {
						rootNow.nexts[index].suffix = rootNow.suffix.nexts[index];
						trieNodeQueue.add(rootNow.nexts[index]);
					}
				}
			}
			List<String> list = new LinkedList<String>();
			for (String w : words) {
				rootNow = root;
				boolean isFind = false;
				stk.clear();
				search(w, isFind, rootNow, list);
			}
			return list;
	    }
		private void search(String w, boolean isFind, TrieNode rootNow, List<String> list) {
			for (int index = 0; index < w.length(); index ++) {
				rootNow = rootNow.nexts[cov(w.charAt(index))];
				if (isFind && rootNow.isEndOfAWord && index==w.length()-1) {
					list.add(w);
					return;
				}
				if (rootNow.isEndOfAWord) {
					isFind = true;
					stk.push(rootNow);
					stk_int.push(index);
					rootNow = root;
				}
				
			}
			while (! stk.isEmpty()) {
				TrieNode rootSave = stk.pop();
				int index = stk_int.pop();
				rootNow = root;
				for (; index < w.length(); index ++) {
					rootNow = rootNow.nexts[cov(w.charAt(index))];
					if (isFind && rootNow.isEndOfAWord && index==w.length()-1) {
						list.add(w);
						return;
					}
					if (rootNow.isEndOfAWord && rootNow != rootSave) {
						isFind = true;
						rootNow = root;
					}
					
				}
			}
		}
		static Stack<TrieNode> stk = new Stack<TrieNode>();
		static Stack<Integer> stk_int = new Stack<Integer>();
		static TrieNode root = new TrieNode();
		static class TrieNode {
			boolean isEndOfAWord = false;
			TrieNode[] nexts = new TrieNode[26];
			TrieNode suffix = null;
			public void insert(String s) {
				TrieNode rootNow = root;
				for (int i = 0; i < s.length(); i ++) {
					int nextsIndex = cov(s.charAt(i));
					if (null != rootNow.nexts[nextsIndex]) {
						rootNow = rootNow.nexts[nextsIndex];
					} else {
						TrieNode addTrieNode = new TrieNode();
						rootNow.nexts[nextsIndex] = addTrieNode;
						rootNow = addTrieNode;
					}
				}
				rootNow.isEndOfAWord = true;
			}
		}
		static int cov(char c) {
			int ans = c - 'a';
			if (ans >= 0 && ans < 26) return ans;
			return c - 'A'+26;
		}
	}
	
	static class Solution2 {
		int[][] ne = null;
		LinkedList<Integer>[][] sl = null; 
		@SuppressWarnings("unchecked")
		public List<String> findAllConcatenatedWordsInADict(String[] ws) {
			ne = new int[ws.length][];
			sl = new LinkedList[ws.length][];
			for (int i = 0; i < ws.length; i ++) {
				sl[i] = new LinkedList[ws[i].length()];
				ne[i] = getNext(ws[i]);
			}
			init(ws);
			LinkedList<String> ans = new LinkedList<String>();
			for (int i = 0; i < ws.length; i ++) {
				if (ws[i].length() == 0) continue;
				search(ws, i, 0, ans);
			}
			return ans;
			
		}
		private void init(String[] ws) {
			for (int i = 0; i < ws.length; i ++) {
				for (int j = 0; j < ws.length; j ++) {
					if (i == j) continue;
					String s = ws[i], p = ws[j];
					int[] next = ne[j];
					kmp_ll(next, s, p, sl[i], j);
				}
			}
		}
		private void search(String[] ws, int wi, int index, LinkedList<String> ans) {
			if (index == ws[wi].length() && (ans.size() == 0 || ! ans.peekLast().equals(ws[wi]))) ans.add(ws[wi]);
			if (index >= ws[wi].length() || sl[wi][index] == null) return;
			for (int k : sl[wi][index]) {
				k --;
				search(ws, wi, index + ws[k].length(), ans);
			}
		}
		private int kmp_ll(int[] next, String s, String p, LinkedList<Integer>[] sss, int j) {
			int pi = 0;
			for (int si = 0; si <= s.length() - p.length(); si ++) {
				while (pi < p.length() && s.charAt(si + pi) == p.charAt(pi)) pi ++;
				if (pi == p.length()) {
					if (sss[si] == null)
						sss[si] = new LinkedList<>();
					sss[si].add(j+1);
				}
				pi = next[pi];
			}
			return -1;
		}
		private int[] getNext(String p) {
			int[] next = new int[p.length() + 1];
			next[0] = -1;
			int bi = -1, fi = 0;
			for (; fi < p.length(); fi ++) {
				if (bi == -1 || p.charAt(bi) == p.charAt(fi)) {
					bi ++;
					fi ++;
					next[fi] = bi;
				} else {
					bi = next[bi];
				}
			}
			next[0] = 0;
			return next;
		}
	}
	static class Solution3 {
	    List<String> answer = null;
	    HashMap<String, Integer> map = null;
	    public List<String> findAllConcatenatedWordsInADict(String[] ws) {
	        answer = new LinkedList<String>();
	        map = new HashMap<String, Integer>();
	        for (String w : ws) map.put(w, 0);
	        for (String w : ws)
	            if (find(w, 0) >= 1) answer.add(w);
	        return answer;
	    }
        private int find(String w, int i) {
            Integer val = map.get(w);
            if (val != null && i != 0) return val;
            for (int index = 1; index < w.length(); index ++) {
                if (map.containsKey(w.substring(0, index))) {
                    int fid = find(w.substring(index), i + 1);
                    if (fid != -1) {
                        map.put(w.substring(index), 1);
                        return fid + 1;
                    }
                }
            }
            return -1;
        }
	}
}
