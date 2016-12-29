package leetcode;

/*
 * 	Given a string s and a dictionary of words dict, 
 * 	add spaces in s to construct a sentence 
 * 	where each word is a valid dictionary word.

	Return all such possible sentences.
	
	For example, given
	s = "catsanddog",
	dict = ["cat", "cats", "and", "sand", "dog"].
	
	A solution is ["cats and dog", "cat sand dog"].
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P140_WordBreakII.java
 * @type        P140_WordBreakII
 * @date        2016年12月29日 下午8:39:47
 * @details     Solution1: AC 31 ms 3.30%
 * @details     Solution2: AC 29 ms 4.50%
 */
public class P140_WordBreakII {
	static class Solution1 {
		List<String> a = new LinkedList<String>();
		StringBuilder st = new StringBuilder();
		LinkedList<String>[] dp;
		@SuppressWarnings("unchecked")
		public List<String> wordBreak(String s, Set<String> wd) {
			if (s == null || s.length() == 0 || wd == null || wd.size() == 0) return a;
			dp = new LinkedList[s.length()];
			for (int i = 0; i < dp.length; i ++) {
				for (String w : wd) {
					int j = i+1-w.length();
					if (j < 0) continue;
					if (s.substring(j, i+1).equals(w) && 
							(j == 0 || dp[j-1] != null)) {
						if (dp[i] == null) dp[i] = new LinkedList<String>();
						dp[i].add(w);
					}
				}
			}
			dfs(dp.length - 1);
			return a;
		}
		private void dfs(int i) {
			if (i < 0) return;
			if (dp[i] != null)
			for (String s : dp[i]) {
				int j = i - s.length();
				if (j != -1) {
					st.insert(0, " " + s);
					dfs(j);
					st.delete(0, s.length()+1);
				} else {
					st.insert(0, s);
					a.add(st.toString());
					st.delete(0, s.length());
				}
			}
		}
	}
	static class Solution2 {
		List<String> a = new LinkedList<String>();
		char[] cs;
		int ci;
		LinkedList<String>[] dp;
		@SuppressWarnings("unchecked")
		public List<String> wordBreak(String s, Set<String> wd) {
			if (s == null || s.length() == 0 || wd == null || wd.size() == 0) return a;
			dp = new LinkedList[s.length()];
			cs = new char[s.length() * 2];
			ci = cs.length - 1;
			for (int i = 0; i < dp.length; i ++) {
				for (String w : wd) {
					int j = i+1-w.length();
					if (j < 0) continue;
					if (s.substring(j, i+1).equals(w) && 
							(j == 0 || dp[j-1] != null)) {
						if (dp[i] == null) dp[i] = new LinkedList<String>();
						dp[i].add(w);
					}
				}
			}
			dfs(dp.length - 1);
			return a;
		}
		private void dfs(int i) {
			if (i < 0) return;
			if (dp[i] != null)
			for (String s : dp[i]) {
				int j = i - s.length();
				for (int k = s.length() - 1; k > -1; k --)
					cs[ci --] = s.charAt(k);
				if (j != -1) {
					cs[ci --] = ' ';
					dfs(j);
					ci += s.length()+1;
				} else {
					a.add(new String(cs, ci + 1, cs.length - ci - 1));
					ci += s.length();
				}
			}
		}
	}
}
