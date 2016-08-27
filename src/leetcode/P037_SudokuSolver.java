package leetcode;

/*
 * 	解数独
 * 	方法应该是试凑和回溯
 */

public class P037_SudokuSolver {
	public static void main(String[] args) {

	}
	/*
	 * 	还有很多bug
	 */
	static class Solution {
		private boolean[][] isValidRow = new boolean[9][9];
		private boolean[][] isValidColumn = new boolean[9][9];
		private boolean[][] isValidNine = new boolean[9][9];
		private boolean isSuccess = false;

		public void solveSudoku(char[][] board) {
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					if (board[i][j] != '.')
						init(board, i, j);
			solve(board, 0, 0);
		}

		private void solve(char[][] board, int i, int j) {
			if (true == isSuccess)
				return;
			if (i > 8) {
				isSuccess = true;
				return;
			}
			if (board[i][j] != '.') {
				if (j < 8)
					solve(board, i, j + 1);
				else
					solve(board, i + 1, 0);
				if (isSuccess)
					return;
			} else {
				int temp = i / 3 * 3 + j / 3;
				for (int n = 0; n < 9; n++) {
					if (!isValidColumn[j][n] && !isValidRow[i][n] && !isValidNine[temp][n]) {
						board[i][j] = (char) (n + '1');
						isValidColumn[j][n] = isValidRow[i][n] = isValidNine[temp][n] = true;
						if (j < 8)
							solve(board, i, j + 1);
						else
							solve(board, i + 1, 0);
						isValidColumn[j][n] = isValidRow[i][n] = isValidNine[temp][n] = false;
						if (isSuccess)
							return;
					}
				}
			}
		}

		private void init(char[][] board, int i, int j) {
			isValidRow[i][board[i][j] - '1'] = true;
			isValidColumn[j][board[i][j] - '1'] = true;
			isValidNine[i / 3 * 3 + j / 3][board[i][j] - '1'] = true;
		}

	}
	/*
	 * 	36 ms
	 * 	18.91%
	 */
	static class Solution2 {
		public void solveSudoku(char[][] board) {
			solveSudoku2(board);
		}
		public boolean solveSudoku2(char[][] board) {
			for (int i = 0; i < 9; ++i)
				for (int j = 0; j < 9; ++j) {
					if (board[i][j] == '.') {
						for (int k = 0; k < 9; ++k) {
							board[i][j] = (char) ('1' + k);
							if (isValid(board, i, j) && solveSudoku2(board))
								return true;
							board[i][j] = '.';
						}
						return false;
					}
				}
			return true;
		}
		private boolean isValid(char[][] board, int x, int y) {
			int i, j;
			for (i = 0; i < 9; i++)
				if (i != x && board[i][y] == board[x][y])
					return false;
			for (j = 0; j < 9; j++)
				if (j != y && board[x][j] == board[x][y])
					return false;
			for (i = 3 * (x / 3); i < 3 * (x / 3 + 1); i++)
				for (j = 3 * (y / 3); j < 3 * (y / 3 + 1); j++)
					if ((i != x || j != y) && board[i][j] == board[x][y])
						return false;
			return true;
		}
	}
}
