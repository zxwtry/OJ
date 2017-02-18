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
 * @details     Solution1: WA 数据结构有点多
 * @details     Solution3: AC 54ms 2.36%
 */
public class P316_RemoveDuplicateLetters {
    public static void main(String[] args) {
//        TreeSet<Integer> treeSet = new TreeSet<Integer>();
//        treeSet.add(2);
//        treeSet.add(8);
//        treeSet.add(-1);
//        treeSet.add(9);
//        for (int treeSetValue : treeSet)
//            System.out.println(treeSetValue);
    }
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
	static class Solution2 {
	    public String removeDuplicateLetters(String string) {
	        final char startChar = 'a';
	        @SuppressWarnings("unchecked")
            TreeSet<Integer>[] treeSetArray = new TreeSet[26];
	        TreeSet<Integer> moreThanTwoSet = new TreeSet<Integer>();
	        for (int stringIndex = string.length() - 1; stringIndex > -1; stringIndex --) {
	            int treeSetArrayIndex = string.charAt(stringIndex) - startChar;
	            if (treeSetArray[treeSetArrayIndex] == null)
	                treeSetArray[treeSetArrayIndex] = new TreeSet<Integer>();
	            treeSetArray[treeSetArrayIndex].add(stringIndex);
	            if (treeSetArray[treeSetArrayIndex].size() == 2) moreThanTwoSet.add(treeSetArrayIndex);
	        }
	        while (moreThanTwoSet.size() > 0) {
	            
	        }
	        return "";
	    }
	}
	static class Solution3 {
	    public String removeDuplicateLetters(String s) {
	        int[] cnt = new int[26];
	        int pos = 0;
	        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
	        for (int i = 0; i < s.length(); i++) {
	            if (s.charAt(i) < s.charAt(pos)) pos = i;
	            if (--cnt[s.charAt(i) - 'a'] == 0) break;
	        }
	        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
	    }
	}
}
