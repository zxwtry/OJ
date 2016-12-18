package leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 	Given a list of words, please write a program that returns all concatenated words
 * 	in the given list of words.

	A concatenated word is defined as a string that is comprised entirely of at least
	 two shorter words in the given array.
	
	Example:
	Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat",
	"ratcatdogcat"]
	
	Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
	
	Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
	 "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
	"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
	Note:
	The number of elements of the given array will not exceed 10,000
	The length sum of elements in the given array will not exceed 600,000.
	The returned elements order does not matter.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P472_ConcatenatedWords.java
 * @type        P472_ConcatenatedWords
 * @date        2016年12月18日 上午11:40:43
 * @details     
 */
public class P472_ConcatenatedWords {
	
	/**
	 * @author      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     leetcode
	 * @file        P472_ConcatenatedWords.java
	 * @type        Solution1
	 * @date        2016年12月18日 下午4:20:40
	 * @details     MLE
	 */
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
	
	
	
}
