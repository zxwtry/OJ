package leetcode;

/**
 * 	Write an efficient algorithm that searches for a value in an m x n matrix. 
 * 	This matrix has the following properties:
 *	
 *	Integers in each row are sorted in ascending from left to right.
 *	Integers in each column are sorted in ascending from top to bottom.
 *	For example,
 *	
 *	Consider the following matrix:
 *	
 *	[
 *	  [1,   4,  7, 11, 15],
 *	  [2,   5,  8, 12, 19],
 *	  [3,   6,  9, 16, 22],
 *	  [10, 13, 14, 17, 24],
 *	  [18, 21, 23, 26, 30]
 *	]
 *	Given target = 5, return true.
 *	
 *	Given target = 20, return false.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P240_SearchA2DMatrixII.java
 * @type        P240_SearchA2DMatrixII
 * @date        2016年12月12日 下午9:58:47
 * @details     Solution1: AC 19ms 13.07%
 */
public class P240_SearchA2DMatrixII {
	static class Solution1 {
		boolean isFind = false;
		int row = 0, col = 0;
	    public boolean searchMatrix(int[][] m, int t) {
	    	if (m == null || m.length == 0 || m[0].length == 0)
	    		return false;
	        row = m.length;
	        col = m[0].length;
	        if (t < m[0][0] || t > m[row - 1][col - 1]) return false;
	        isFind = false;
	        search(m, 0, 0, row - 1, col - 1, t);
	        return isFind;
	    }
		private void search(int[][] m, int smallI, int smallJ, int bigI, int bigJ, int t) {
			if (isFind) return;
			if (bigI < smallI || bigJ < smallJ || smallI < 0 || bigI >= row || smallJ < 0 || bigJ >= col)
				return;
			if (t < m[smallI][smallJ] || t > m[bigI][bigJ]) {
				return;
			}
			int midI = (smallI + bigI) / 2, midJ = (smallJ + bigJ) / 2;
			if (m[midI][midJ] == t) {
				isFind = true;
			} else if (m[midI][midJ] < t) {
				search(m, smallI, midJ + 1, bigI, bigJ, t);
				search(m, midI + 1, smallJ, bigI, midJ, t);
			} else {
				search(m, smallI, smallJ, midI - 1, bigJ, t);
				search(m, midI, smallJ, bigI, midJ - 1, t);
			}
		}
	}
}
