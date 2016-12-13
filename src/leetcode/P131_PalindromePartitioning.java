package leetcode;

import java.util.LinkedList;
import java.util.List;

/*
 * 	Given a string s, partition s such that every substring 
 * 	of the partition is a palindrome.

	Return all possible palindrome partitioning of s.
	
	For example, given s = "aab",
	Return
	
	[
	  ["aa","b"],
	  ["a","a","b"]
	]
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P131_PalindromePartitioning.java
 * @type        P131_PalindromePartitioning
 * @date        2016年12月13日 下午6:13:52
 * @details     Solution1 AC
 */
public class P131_PalindromePartitioning {
	public static void main(String[] args) {
	}
	
	/**
	 * @author      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     leetcode
	 * @file        P131_PalindromePartitioning.java
	 * @type        Solution1
	 * @date        2016年12月13日 下午6:13:55
	 * @details     AC 10ms 50.34%
	 */
	static class Solution1 {
		List<List<String>> ans = new LinkedList<List<String>>();
		LinkedList<String> l = new LinkedList<String>();
		char[] cs = null;
	    public List<List<String>> partition(String s) {
	        cs = s.toCharArray();
	        for (int i = 0; i < cs.length; i ++) {
	        	search(0, i, 0);
	        }
	        return ans;
	    }
		private void search(int s, int t, int n) {
			if (isP(s, t)) {
				l.add(new String(cs, s, t-s+1));
				for (int nt = t+1; nt < cs.length; nt ++)
					search(t+1, nt, n + 1);
				if (n+1 == l.size()) {
					if (t == cs.length-1)
						ans.add(new LinkedList<>(l));
					l.removeLast();
				}
			}
		}
		private boolean isP(int s, int t) {
			while (s < t) {
				if (cs[s] != cs[t]) {
					return false;
				} else {
					s ++;
					t --;
				}
			}
			return true;
		}
	}
}
