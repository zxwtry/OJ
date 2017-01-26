package leetcode;

import java.util.TreeSet;

/**
 * 	Given a string which contains only lowercase letters, 
 * 	remove duplicate letters so that every letter appear once 
 * 	and only once. You must make sure your result is the smallest 
 * 	in lexicographical order among all possible results.

 *	Example:
 *	Given "bcabc"
 *	Return "abc"
 *	
 *	Given "cbacdcbc"
 *	Return "acdb"
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P316_RemoveDuplicateLetters.java
 * @type        P316_RemoveDuplicateLetters
 * @date        2017年1月4日 下午10:16:14
 * @details     Solution1: AC 数据结构有点多
 */
public class P316_RemoveDuplicateLetters {
	static class Solution1 {
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		public String removeDuplicateLetters(String s) {
	        TreeSet[] sets = new TreeSet[26];
	        int tmp = 0;
	        for (int i = s.length() - 1; i > -1; i --) {
	        	tmp = s.charAt(i) - 'a';
	        	if (sets[tmp] == null)
	        		sets[tmp] = new TreeSet<Integer>();
	        	sets[tmp].add(i);
 	        }
	        int len = 0;
	        int startIndex = Integer.MAX_VALUE;
	        TreeSet<Integer> ansSet = new TreeSet<Integer>();
	        for (int i = sets.length - 1; i > -1; i --) {
	        	if (sets[i] != null) {
	        		len ++;
	        		startIndex = Math.min(startIndex, (Integer)sets[i].last());
	        	}
	        }
	        char[] ans = new char[len];
	        int ansIndex = 0;
	        for (int i = 0; i < sets.length; i ++) {
	        	if (sets[i] != null) {
	        		ansSet.add((Integer)sets[i].higher(startIndex - 1));
	        	}
	        }
	        for (int i : ansSet) {
	        	ans[ansIndex ++] = s.charAt(i);
	        }
	        return new String(ans);
	    }
	}
}
