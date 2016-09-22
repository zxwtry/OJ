package leetcode;

import java.util.HashMap;


/*
 * 	Given a 2D board and a word, find if the word exists in the grid.

	The word can be constructed from letters of sequentially adjacent cell,
	 where "adjacent" cells are those horizontally or vertically neighboring.
	  The same letter cell may not be used more than once.
	
	For example,
	Given board =
	
	[
	  ['A','B','C','E'],
	  ['S','F','C','S'],
	  ['A','D','E','E']
	]
	word = "ABCCED", -> returns true,
	word = "SEE", -> returns true,
	word = "ABCB", -> returns false.
 */

public class P079_WordSearch {
	public static void main(String[] args) {
		char[][] board = null;
		board = new char[][] {
			{'A','B','C','E'},
			{'S','F','C','S'},
			{'A','D','E','E'}
		};
		String word = "ABCB";
		System.out.println(new Solution2().exist(board, word));
	}
	/*
	 * 	没有正确理解题意。
	 */
	static class Solution {
	    public boolean exist(char[][] board, String word) {
	    	if (board == null || board.length == 0)
	    		return word == null || word.length() == 0;
	    	HashMap<Character, Integer> map = new HashMap<>();
	    	for (int i = 0; i != board.length; i ++) {
	    		for (int j = 0; j != board[0].length; j ++) {
	    			if (map.containsKey(board[i][j]))
	    				map.put(board[i][j], map.get(board[i][j]) + 1);
	    			else
	    				map.put(board[i][j], 1);
	    		}
	    	}
	    	for (int i = word.length() - 1; i > -1; i --) {
	    		char c = word.charAt(i);
	    		if (! map.containsKey(c)) {
	    			return false;
	    		} else {
	    			int num = 0;
	    			if ((num = map.get(c)) < 1)
	    				return false;
	    			else
	    				map.put(c, num - 1);
	    		}
	    	}
	        return true;
	    }
	}
	static class Solution2 {
		boolean[][] isVisited = null;
		HashMap<Character, Integer> map = null;
		boolean isFound = false;
		int len = 0;
		public boolean exist(char[][] board, String word) {
			if (board == null || board.length == 0)
	    		return word == null || word.length() == 0;
			isVisited = new boolean[board.length][board[0].length];
			map = new HashMap<>();
			char c = '\0';
			for (int i = (len = word.length()) - 1; i > -1; i --)
				if (map.containsKey(c = word.charAt(i)))
					map.put(c, map.get(c) + 1);
				else
					map.put(c, 1);
			for (int i = 0; i != board.length; i ++)
				for (int j = 0; j != board[0].length; j ++)
					if (map.containsKey(board[i][j]) && ! isVisited[i][j])
						search(i, j, 0);
			return isFound;
		}
		private void search(int i, int j, int c) {
			
		}
	}
}