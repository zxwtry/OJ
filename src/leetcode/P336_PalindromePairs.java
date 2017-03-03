package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 	Given a list of unique words, find all pairs of distinct indices (i, j) in the given list,
 *  so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 * 	
 * 	Example 1:
 * 	Given words = ["bat", "tab", "cat"]
 * 	Return [[0, 1], [1, 0]]
 * 	The palindromes are ["battab", "tabbat"]
 * 	Example 2:
 * 	Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 * 	Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 * 	The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P336_PalindromePairs.java
 * @type        P336_PalindromePairs
 * @date        2017年2月2日 下午9:11:08
 * @details     Solution1: TLE *
 * @details     Solution2: TLE *
 * @details     Solution3: AC 132 79.05% *
 */
public class P336_PalindromePairs {
	static class Solution1 {
	    public List<List<Integer>> palindromePairs(String[] words) {
	        List<List<Integer>> answer = new LinkedList<List<Integer>>();
	        for (int i = 0; i < words.length; i ++) {
	            for (int j = 0; j < words.length; j ++) {
	                if (i != j) {
	                    if (isPalindrome(words[i], words[j])) {
	                        ArrayList<Integer> newList = new ArrayList<>(2);
	                        newList.add(i);
	                        newList.add(j);
	                        answer.add(newList);
	                    }
	                }
	            }
	        }
	        return answer;
	    }
	    private boolean isPalindrome(String start, String end) {
	        int i = 0, j = start.length() + end.length() - 1;
	        while (i < j) {
	            if (accessChar(start, end, i) != accessChar(start, end, j))
	                return false;
	            i ++;
	            j --;
	        }
	        return true;
	    }
	    private char accessChar(String start, String end, int index ) {
	        if (index >= start.length()) return end.charAt(index - start.length());
	        return start.charAt(index);
	    }
	}
}
