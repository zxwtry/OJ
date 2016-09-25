package leetcode;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * 	Given a 2D board containing 'X' and 'O' (the letter O),
 *  capture all regions surrounded by 'X'.

	A region is captured by flipping all 'O's into 'X's in that surrounded region.
	
	For example,
	X X X X
	X O O X
	X X O X
	X O X X
	After running your function, the board should be:
	
	X X X X
	X X X X
	X X X X
	X O X X
 */

public class P130_SurroundedRegions {
	public static void main(String[] args) {
		char[][] board = new char[][] {
			{'X', 'X', 'X'},
			{'X', 'O', 'X'},
			{'X', 'X', 'X'},
		};
		Solution s = new Solution();
		s.solve(board);
		tools.Utils.A_打印二维char数组(board);
	}
	static class Solution {
		final char boom = 'O';
		ArrayList<Integer> is = new ArrayList<>();
		ArrayList<Integer> js = new ArrayList<>();
		boolean[][] isVisited = null;
		boolean isBoom = true;
	    public void solve(char[][] board) {
	        if (board == null) {
	        	return;
	        }
	        int row = board.length;
	        if (row < 3) {
	        	return;
	        }
	        int col = board[0].length;
	        if (col < 3) {
	        	return;
	        }
	        isVisited = new boolean[row][col];
	        for (int row_i = 1; row_i < row - 1; row_i ++) {
	        	for (int col_j = 1; col_j < col - 1; col_j ++) {
	        		if (board[row_i][col_j] != boom || isVisited[row_i][col_j]) {
	        			continue;
	        		}
        			isBoom = true;
        			is.clear();
        			js.clear();
        			search(board, row_i, col_j, row, col);
        			if (isBoom) {
            			Iterator<Integer> it_i = is.iterator();
            			Iterator<Integer> it_j = js.iterator();
	        			while (it_i.hasNext() && it_j.hasNext()) {
	        				board[it_i.next()][it_j.next()] = 'X';
	        			}
        			}
	        	}
	        }
	    }
		private void search(char[][] board, int row_i, int col_j, int row, int col) {
			if (row_i <= -1 || row_i >= row) {
				isBoom = false;
				return;
			}
			if (col_j <= -1 || col_j >= row) {
				isBoom = false;
				return;
			}
			if (isVisited[row_i][col_j]) {
				return;
			}
			if (! isBoom) {
				return;
			}
			isVisited[row_i][col_j] = true;
			if (board[row_i][col_j] == boom) {
				is.add(row_i);
				js.add(col_j);
				search(board, row_i - 1, col_j, row, col);
				search(board, row_i, col_j - 1, row, col);
				search(board, row_i, col_j + 1, row, col);
				search(board, row_i + 1, col_j, row, col);
			}
		}
	}
}
