package leetcode;

import java.util.HashSet;

/*
 * 	Given two words (beginWord and endWord), and a dictionary's word list, 
 * find all shortest transformation sequence(s) from beginWord to endWord, such that:

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
import java.util.Set;

public class P126_WordLadderII {
	public static void main(String[] args) {
		
	}
	static class Solution {
		List<List<String>> ans = new LinkedList<>();
	    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
			if (beginWord.equals(endWord)) {
				return ans;
			}
			if (wordList == null) {
				return ans;
			}
			wordList.add(beginWord);
			wordList.add(endWord);
			Queue<String> q_str = new LinkedList<>();
			HashSet<String> set = new HashSet<String>();
			set.add(beginWord);
			q_str.add(beginWord);
			int len = 1;
			char[] cs = new char[beginWord.length()];
			while(! q_str.isEmpty()) {
				len ++;
				int size = q_str.size();
				System.out.println(len);
				for (int i = 0; i < size; i ++) {
					cs = q_str.poll().toCharArray();
					for (int j = 0; j < cs.length; j ++) {
						char c_j = cs[j];
						for (char c = 'a'; c <= 'z'; c ++) {
							if (c == c_j) {
								continue;
							}
							cs[j] = c;
							String now_temp = new String(cs);
							if (now_temp.equals(endWord)) {
								return ans;
							}
							if (set.contains(now_temp)) {
								continue;
							}
							if (wordList.contains(now_temp)) {
								q_str.add(now_temp);
								set.add(now_temp);
							}
						}
						cs[j] = c_j;
					}
				}
			}
		}
	}
}
