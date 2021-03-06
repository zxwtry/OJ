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
	static class Solution2 {
        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> answer = new LinkedList<List<Integer>>();
            boolean[][] map = new boolean[words.length][words.length];
            for (int i = 0; i < words.length; i ++) {
                for (int j = 0; j < words.length; j ++) {
                    if (i != j) {
                        if (
                                (i > j && map[j][i] && words[i].length() == words[j].length()) || 
                                isPalindrome(words[i], words[j])) {
                            ArrayList<Integer> newList = new ArrayList<>(2);
                            newList.add(i);
                            newList.add(j);
                            answer.add(newList);
                            map[i][j] = true;
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
	static class Solution3 {
	    public List<List<Integer>> palindromePairs(String[] words) {
	        List<List<Integer>> answer = new LinkedList<List<Integer>>();
	        HashMap<String, Integer> map = new HashMap<String, Integer>(words.length);
	        for (int index = 0; index < words.length; index ++)
	            map.put(words[index], index);
	        for (int i = 0; i < words.length; i ++) {
	            for (int j = words[i].length() - 1; j >= -1; j --) {
	                String leftPart = words[i].substring(0, j + 1);
	                String rightPart = words[i].substring(j + 1, words[i].length());
	                boolean leftPalindrome = isPalindrome(leftPart);
	                boolean rightPalindrome = isPalindrome(rightPart);
	                if (leftPalindrome && leftPart.length() != 0) {
	                    String rightReverse = getReverse(rightPart);
	                    Integer rightValue = map.get(rightReverse);
	                    if (rightValue != null && rightValue != i) {
	                        ArrayList<Integer> newList = new ArrayList<Integer>(2);
	                        newList.add(rightValue);
	                        newList.add(i);
	                        answer.add(newList);
	                    }
	                }
	                if (rightPalindrome) {
	                    String leftReverse = getReverse(leftPart);
	                    Integer leftValue = map.get(leftReverse);
	                    if (leftValue != null && leftValue != i) {
	                        ArrayList<Integer> newList = new ArrayList<Integer>(2);
                            newList.add(i);
                            newList.add(leftValue);
                            answer.add(newList);
	                    }
	                }
	            }
	        }
	        return answer;
	    }

        private String getReverse(String string) {
            char[] cs = new char[string.length()];
            for (int csIndex = 0; csIndex < cs.length; csIndex ++) { 
                cs[csIndex] = string.charAt(cs.length - 1 - csIndex);
            }
            return new String(cs);
        }

        private boolean isPalindrome(String string) {
            int left = 0, right = string.length() - 1;
            while (left < right) {
                if (string.charAt(left) != string.charAt(right))
                    return false;
                left ++;
                right --;
            }
            return true;
        }
	}
}
