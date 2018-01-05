package leetcode;

import java.util.Arrays;

/*
 * 	A valid Sudoku board (partially filled) is not necessarily solvable. 
 * 	Only the filled cells need to be validated.	
 * 	只需要判断已经存在的数字是否合法就行，不需要管数独是否有解
 */

public class P036_ValidSudoku {
	public static void main(String[] args) {
		System.out.println(new Solution2().isValidSudoku(new char[][] {
			".87654321".toCharArray(),
			"2........".toCharArray(),
			"3........".toCharArray(),
			"4........".toCharArray(),
			"5........".toCharArray(),
			"6........".toCharArray(),
			"7........".toCharArray(),
			"8........".toCharArray(),
			"9........".toCharArray()
		}));
	}
	/*
	 * 	8 ms
	 * 	24.64%
	 */
	static class Solution {
	    public boolean isValidSudoku(char[][] board) {
	        boolean[] isExistRow = new boolean[9];
	        boolean[][] isExistColumn = new boolean[9][9];
	        boolean[][] isExistNine = new boolean[9][9];
	        for (int i = 0; i < 9; i ++) {
	        	Arrays.fill(isExistRow, false);
	        	for (int j = 0; j < 9; j ++) {
	        		if (board[i][j] == '.')
	        			continue;
	        		if (! isValid(isExistRow, board[i][j] - '1') ||
	        				! isValid(isExistColumn[j], board[i][j] - '1') ||
	        				! isValid(isExistNine[i/3*3+j/3], board[i][j] - '1'))
	        			return false;
	        	}
	        }
	        return true;
	    }
	    private boolean isValid(boolean[] isExist, int index) {
	    	if(isExist[index])
	    		return false;
	    	isExist[index] = true;
	    	return true;
	    }
	}
	/*
	 * 	6 ms
	 * 	41.76% 
	 */
	static class Solution2 {
		public boolean isValidSudoku(char[][] board) {
	        boolean[] isExistRow = new boolean[9];
	        boolean[] isExistColumn = new boolean[9];
	        boolean[] isExistNine = new boolean[9];
	        for (int i = 0; i < 9; i ++) {
	        	Arrays.fill(isExistRow, false);
	        	Arrays.fill(isExistColumn, false);
	        	Arrays.fill(isExistNine, false);
	        	for (int j = 0; j < 9; j ++) {
	        		if (! isValid(isExistRow, board[i][j] - '1') ||
	        				! isValid(isExistColumn, board[j][i] - '1') ||
	        				! isValid(isExistNine, board[i/3*3+j/3][i%3*3+j%3] - '1'))
	        			return false;
	        	}
	        }
	        return true;
	    }
	    private boolean isValid(boolean[] isExist, int index) {
	    	if (index < 0)
	    		return true;
	    	if(isExist[index])
	    		return false;
	    	isExist[index] = true;
	    	return true;
	    }
	}
	static class Solution3 {
		public boolean isValidSudoku(char[][] board) {
			return false;
		}
	}
}