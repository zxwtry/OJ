package leetcode;

import java.util.ArrayList;
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
 * @details     Solution1: AC 10ms 50.34%
 * @details     Solution2: AC 10ms 50.34%
 * @details     Solution3: AC  8ms 79.05%
 */
public class P131_PalindromePartitioning {
	static class Solution1 {
        List<List<String>> ans = new LinkedList<List<String>>();
        LinkedList<String> l = new LinkedList<String>();
        char[] cs = null;
        public List<List<String>> partition(String s) {
            cs = s.toCharArray();
            for (int i = 0; i < cs.length; i ++) {
                search(0, i);
            }
            return ans;
        }
        private void search(int s, int t) {
            if (isP(s, t)) {
                l.add(new String(cs, s, t-s+1));
                for (int nt = t+1; nt < cs.length; nt ++)
                    search(t+1, nt);
                if (t == cs.length-1)
                    ans.add(new LinkedList<>(l));
                l.removeLast();
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
	static class Solution2 {
	    public List<List<String>> partition(String s) {
	        int sn = s == null ? 0 : s.length();
	        if (sn == 0) return new ArrayList<List<String>>(0);
	        List<List<List<String>>> rec = new ArrayList<>(sn+1);
	        for (int i = 0; i <= sn; i ++)
	            rec.add(new ArrayList<List<String>>());
	        rec.get(0).add(new ArrayList<String>());
	        boolean[][] pair = new boolean[sn][sn];
	        for (int i = 0; i < sn; i ++) {
	            for (int left = 0; left <= i; left ++) {
	                if (s.charAt(left) == s.charAt(i) && (i <= 1 + left || pair[left + 1][i - 1])) {
	                    pair[left][i] = true;
	                    String str = s.substring(left, i+1);
	                    for (List<String> r : rec.get(left)) {
	                        List<String> ri = new ArrayList<String>(r);
	                        ri.add(str);
	                        rec.get(i+1).add(ri);
	                    }
	                }
	            }
	        }
	        return rec.get(sn);
	    }
	}
   static class Solution3 { 
        List<List<String>> ans = new LinkedList<List<String>>();
        LinkedList<String> l = new LinkedList<String>();
        char[] cs = null;
        boolean[][] pal = null;
        public List<List<String>> partition(String s) {
            if (s == null) return ans;
            cs = s.toCharArray();
            pal = new boolean[cs.length][cs.length];
            for (int i = 0; i < cs.length; i ++) {
                search(0, i);
            }
            return ans;
        }
        private void search(int s, int t) {
            if (cs[s] == cs[t] && (t <= 1+s || pal[s+1][t-1])) {
                pal[s][t] = true;
                l.add(new String(cs, s, t-s+1));
                for (int nt = t+1; nt < cs.length; nt ++)
                    search(t+1, nt);
                if (t == cs.length-1)
                    ans.add(new LinkedList<>(l));
                l.removeLast();
            }
        }
    }
}
