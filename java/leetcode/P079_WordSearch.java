package leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


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
			"ABCE".toCharArray(),
			"SFES".toCharArray(), 
			"ADEE".toCharArray(),
		};
		String word = "ABCESEEEFS";
		System.out.println(new Solution3().exist(board, word));
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
			return false;
		}
		private void search(int i, int j, int c) {
			
		}
	}
	
	/**
	 * @author      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     leetcode
	 * @file        P079_WordSearch.java
	 * @type        Solution3
	 * @date        2016年12月8日 下午3:57:13
	 * @details     31ms 16.54% AC
	 */
	static class Solution3 {
		//不就是一个图遍历嘛
		char[] c = null;
		boolean[][] iv = null;
		boolean[] su = new boolean[] {false};
		int[] x = new int[] {-1, 1, 0, 0};
		int[] y = new int[] {0, 0, -1, 1};
		LinkedList<Integer> xs = new LinkedList<Integer>(); 
		LinkedList<Integer> ys = new LinkedList<Integer>(); 
		public boolean exist(char[][] b, String w) {
			if (b == null || b.length == 0)
	    		return w == null || w.length() == 0;
			c = w.toCharArray();
			iv = new boolean[b.length][b[0].length];
			for (int i = 0; i < b.length; i ++) {
				for (int j = 0; j < b[0].length; j ++) {
					if (b[i][j] == c[0] && ! su[0]) {
						se(b, i, j, 0);
						Iterator<Integer> itx = xs.iterator();
						Iterator<Integer> ity = ys.iterator();
						while (itx.hasNext()) {
							int ix = itx.next();
							int iy = ity.next();
							iv[ix][iy] = false;
						}
						xs.clear();
						ys.clear();
					}
				}
			}
			return su[0];
		}
		private void se(char[][] b, int i, int j, int ci) {
			xs.add(i);
			ys.add(j);
			iv[i][j] = true;
			if (ci == c.length-1 || su[0]) {
				su[0] = true;
				return;
			}
			for (int k = 0; k < x.length; k ++) {
				int nx = i + x[k], ny = j + y[k];
				if (nx > -1 && nx < b.length && ny > -1 && ny < b[0].length && 
						b[nx][ny] == c[ci + 1] && !iv[nx][ny]) {
					se(b, nx, ny, ci + 1);
					while(xs.size() > ci + 1) {
						int px = xs.pollLast();
						int py = ys.pollLast();
						iv[px][py] = false;
					}
				}
			}
		}
	}
}