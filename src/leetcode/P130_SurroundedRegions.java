package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

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
//			{'X', 'X', 'X'},
//			{'X', 'O', 'X'},
//			{'X', 'X', 'X'},
			{'X', 'X', 'X', 'O'},
			{'X', 'X', 'O', 'X'},
			{'X', 'O', 'X', 'X'},
			{'X', 'X', 'X', 'X'},
			{'X', 'X', 'O', 'O'},
			{'X', 'O', 'X', 'X'},
			{'X', 'O', 'O', 'X'},
			{'X', 'X', 'X', 'X'},
		};
		Solution2 s = new Solution2();
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
	/*
	 * 	3.92%
	 * 	30 ms
	 * 	时间很慢
	 */
	static class Solution2 {
		final char boom = 'O';
		boolean[][] isBaned = null;
		int row, col;
	    public void solve(char[][] board) {
	    	if (board == null) {
	        	return;
	        }
	        row = board.length;
	        if (row < 3) {
	        	return;
	        }
	        col = board[0].length;
	        if (col < 3) {
	        	return;
	        }
	        isBaned = new boolean[row][col];
	        for (int j = 0; j < col - 1; j ++) {
	        	search(board, 0, j);
	        }
	        for (int i = 0; i < row - 1; i ++) {
	        	search(board, i, col - 1);
	        }
	        for (int j = col - 1; j > 0; j --) {
	        	search(board, row - 1, j);
	        }
	        for (int i = row - 1; i > 0; i --) {
	        	search(board, i, 0);
	        }
	        for (int i = 0; i < row; i ++) {
	        	for (int j = 0; j < col; j ++) {
	        		if (! isBaned[i][j]) {
	        			board[i][j] = 'X';
	        		}
	        	}
	        }
	    }
	    private void search(char[][] board, int i, int j) {
	    	Queue<Integer> q_i = new LinkedList<Integer>();
	    	Queue<Integer> q_j = new LinkedList<Integer>();
	    	q_i.add(i);
	    	q_j.add(j);
	    	while (! q_i.isEmpty()) {
	    		int i_now = q_i.poll();
	    		int j_now = q_j.poll();
	    		if (i_now < 0 || i_now >= row || j_now < 0 || j_now >= col || 
	    				isBaned[i_now][j_now] || board[i_now][j_now] != boom) {
	    			continue;
	    		}
	    		isBaned[i_now][j_now] = true;
	    		q_i.add(i_now + 1);
	    		q_j.add(j_now);
	    		q_i.add(i_now);
	    		q_j.add(j_now + 1);
	    		q_i.add(i_now - 1);
	    		q_j.add(j_now);
	    		q_i.add(i_now);
	    		q_j.add(j_now - 1);
	    	}
	   	}
	}
}
