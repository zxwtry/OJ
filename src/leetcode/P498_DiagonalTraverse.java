package leetcode;

/**
 * 	Given a matrix of M x N elements (M rows, N columns), return all elements
 *  of the matrix in diagonal order as shown in the below image.
 *	
 *	Example:
 *	Input:
 *	[
 *	 [ 1, 2, 3 ],
 *	 [ 4, 5, 6 ],
 *	 [ 7, 8, 9 ]
 *	]
 *	Output:  [1,2,4,7,5,3,6,8,9]
 *	Explanation:
 *	
 *	Note:
 *	The total number of elements of the given matrix will not exceed 10,000.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P498_DiagonalTraverse.java
 * @type        P498_DiagonalTraverse
 * @date        2017年2月8日 下午11:21:03
 * @details     Solution1: AC 20ms 17.99%
 */
public class P498_DiagonalTraverse {
	static class Solution1 {
	    public int[] findDiagonalOrder(int[][] matrix) {
	        if (matrix == null || matrix.length == 0 ||
	                matrix[0] == null || matrix[0].length == 0) return new int[0];
	        int row = matrix.length, col = matrix[0].length;
	        int[] answer = new int[row * col];
	        int[] answerIndex = new int[] {0};
	        for (int i = 0; i < row + col - 1; i ++) {
	            int j = i;
                if (i % 2 == 0) {
                    diagonalUp(row, col, answer, answerIndex, i, j, matrix);
                } else {
                    diagonalDn(row, col, answer, answerIndex, j, i, matrix);
                }
	        }
	        return answer;
	    }
        private void diagonalDn(int row, int col, int[] answer, int[] answerIndex, int i, int j, int[][] matrix) {
            int stRow = 0, stCol = 0;
            if (i < col) {
                stRow = 0;
                stCol = i;
            } else {
                stRow = i - col + 1;
                stCol = col - 1;
            }
            int enRow = 0;
            if (j < row) {
                enRow = j;
            } else {
                enRow = row - 1;
            }
            while (true) {
                answer[answerIndex[0] ++] = matrix[stRow][stCol];
                if (stRow == enRow) break;
                stRow ++;
                stCol --;
            }
        }
        private void diagonalUp(int row, int col, int[] answer, int[] answerIndex, int i, int j, int[][] matrix) {
            int stRow = 0, stCol = 0;
            if (i < row) {
                stRow = i;
                stCol = 0;
            } else {
                stRow = row - 1;
                stCol = i - row + 1;
            }
            int enRow = 0;
            if (j < col) {
                enRow = 0;
            } else {
                enRow = j - col + 1;
            }
            while (true) {
                answer[answerIndex[0] ++] = matrix[stRow][stCol];
                if (stRow == enRow) break;
                stRow --;
                stCol ++;
            }
        }
	}
}
