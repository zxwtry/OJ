package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class P212_WordSearchII {
	/*
	 * 	["abc","aed","afg"]
	 *	["abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade"]
	 */
	public static void main(String[] args) {
		char[][] board = new char[][] {
			"abc".toCharArray(),
			"aed".toCharArray(),
			"afg".toCharArray(),
		};
		String[] words = {"abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade"};
//		words = new String[]{"eaabcdgfa"};
		Solution s = new Solution();
		System.out.println(s.findWords(board, words));
	}
	
	/*
	 * 题中说明了，是26个小写字母
	 * 那么肯定内部使用数组结构
	 * 24 ms
	 * 78.71%
	 */
	static class Solution {
		TrieNode root = new TrieNode();
		List<String> ans = new LinkedList<>();
		boolean[][] isVisited = null;
	    public List<String> findWords(char[][] board, String[] words) {
	    	if (board == null || board.length == 0 || board[0].length == 0) {
	    		return ans;
	    	}
	    	for (int i = 0; i < words.length; i ++) {
	    		insert(words[i], i);
	    	}
	    	isVisited = new boolean[board.length][board[0].length];
	    	for (int i = 0; i < board.length; i ++) {
	    		for (int j = 0; j < board[0].length; j ++) {
	    			for (int k = 0; k < isVisited.length; k ++) {
	    				Arrays.fill(isVisited[k], false);
	    			}
	    			search(board, i, j, root, words);
	    		}
	    	}
	    	return ans;
	    }
		private void search(char[][] board, int i, int j, TrieNode rootNow, String[] words) {
			if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || isVisited[i][j]) {
				return;
			}
			int cIndex = board[i][j] - 'a';
			TrieNode newRootNow = rootNow.arr[cIndex];
			if (newRootNow != null && newRootNow.indexOfWords != -1) {
				ans.add(words[newRootNow.indexOfWords]);
				newRootNow.indexOfWords = -1;
			}
			if (newRootNow != null) {
				isVisited[i][j] = true;
				search(board, i + 1, j, newRootNow, words);
				search(board, i - 1, j, newRootNow, words);
				search(board, i, j + 1, newRootNow, words);
				search(board, i, j - 1, newRootNow, words);
				isVisited[i][j] = false;
			}
		}
		private void insert(String str, int index) {
			TrieNode rootNow = root;
			for (int i = 0; i < str.length(); i ++) {
				int cIndex = str.charAt(i) - 'a';
				if (rootNow.arr[cIndex] == null) {
					rootNow.arr[cIndex] = new TrieNode();
				}
				rootNow = rootNow.arr[cIndex];
			}
			rootNow.indexOfWords = index;
		}
		static class TrieNode {
	    	TrieNode[] arr = new TrieNode[26];
	    	int indexOfWords = -1;
	    }
	}
}
