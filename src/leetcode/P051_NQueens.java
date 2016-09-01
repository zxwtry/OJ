package leetcode;

import java.util.LinkedList;
import java.util.List;

/*
 * 	the n-queens puzzle is the problem of placing n queens 
 * 	on an n×n chessboard such that no two queens attack each other.

	Given an integer n, return all distinct solutions to the n-queens puzzle.
	
	Each solution contains a distinct board configuration of 
	the n-queens' placement, where 'Q' and '.' both indicate a queen 
	and an empty space respectively.
	
	For example,
	There exist two distinct solutions to the 4-queens puzzle:
	[
	 [".Q..",  // Solution 1
	  "...Q",
	  "Q...",
	  "..Q."],
	 ["..Q.",  // Solution 2
	  "Q...",
	  "...Q",
	  ".Q.."]
	]
 */

public class P051_NQueens {
	public static void main(String[] args) {
//		Queen queen = new Queen();
//		queen.backtrack(1);
		System.out.println(new Solution().solveNQueens(4));
	}
	/*
	 * 	4 ms
	 * 	91.55% 
	 */
	static class Solution1 {
		List<List<String>> ans = new LinkedList<List<String>>();
		int[] col = null, rup = null, lup = null, row = null;
		public List<List<String>> solveNQueens(int n) {
			if (n < 1)
				return ans;
			col = new int[n];
			row = new int[n];
			rup = new int[(n << 1) - 1];
			lup = new int[rup.length];
			search(0);
			return ans;
		}
		private void search(int index) {
			if (index == col.length) {
				List<String> answer = new LinkedList<String>();
				char[] cs = new char[index];
				for (int i = 0; i != index; i ++) {
					for (int j = 0; j != index; j ++)
						cs[j] = j == row[i] ? 'Q' : '.';
					answer.add(new String(cs));
				}
				ans.add(answer);
				return;
			}
			for (int i = 0; i != col.length; i ++) {
				if (col[i] == 0 && lup[index + i] == 0 && rup[col.length - 1 + index - i] == 0) {
					col[i] = lup[index + i] = rup[col.length - 1 + index - i] = 1;
					row[index] = i;
					search(index + 1);
					col[i] = lup[index + i] = rup[col.length - 1 + index - i] = 0;
				}
			}
		}
	}
}
