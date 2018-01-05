package leetcode;

/**
 * 	According to the Wikipedia's article: "The Game of Life, also known 
 * 	simply as Life, is a cellular automaton devised by the British mathematician
 *  John Horton Conway in 1970."
 *  
 * 	Given a board with m by n cells, each cell has an initial state live 
 * 	(1) or dead (0). Each cell interacts with its eight neighbors (horizontal, 
 * 	vertical, diagonal) using the following four rules (taken from the above 
 * 	Wikipedia article):
 * 	
 * 	Any live cell with fewer than two live neighbors dies, as if caused by
 * 	under-population.
 * 	Any live cell with two or three live neighbors lives on to the next generation.
 * 	Any live cell with more than three live neighbors dies, as if by over-population..
 * 	Any dead cell with exactly three live neighbors becomes a live cell, as if 
 * 	by reproduction.
 * 	Write a function to compute the next state (after one update) of the board
 * 	given its current state.
 * 	
 * 	Follow up: 
 * 	Could you solve it in-place? Remember that the board needs to be updated 
 * 	at the same time: You cannot update some cells first and then use their 
 * 	updated values to update other cells.
 * 	In this question, we represent the board using a 2D array. In principle, 
 * 	the board is infinite, which would cause problems when the active area 
 * 	encroaches the border of the array. How would you address these problems?
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P289_GameOfLife.java
 * @type        P289_GameOfLife
 * @date        2016年12月17日 下午10:27:41
 * @details     Solution: AC 1ms 13.69%
 */
public class P289_GameOfLife {
	static class Solution {
		int[] xs = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] ys = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};
	    public void gameOfLife(int[][] board) {
	        for (int xIndex = 0; xIndex < board.length; xIndex ++) {
	        	for (int yIndex = 0; yIndex < board[0].length; yIndex ++) {
	        		if (isAlive(board, xIndex, yIndex)) {
	        			board[xIndex][yIndex] += 1000;
	        		}
	        	}
	        }
	        for (int xIndex = 0; xIndex < board.length; xIndex ++) {
	        	for (int yIndex = 0; yIndex < board[0].length; yIndex ++) {
	        		board[xIndex][yIndex] /= 1000;
	        	}
	        }
	    }
	    private boolean isAlive(int[][] board, int xIndex, int yIndex) {
	    	int countAliveAround = 0;
	    	int newX = 0, newY = 0;
	    	for (int index = 0; index < xs.length; index ++) {
	    		newX = xs[index] + xIndex;
	    		newY = ys[index] + yIndex;
	    		if (newX > -1 && newX < board.length && newY > -1 && newY < board[0].length) {
	    			if (board[newX][newY] % 2 == 1) {
	    				countAliveAround ++;
	    			}
	    		}
	    	}
	    	if (countAliveAround == 3) {
	    		return true;
	    	}
	    	if (board[xIndex][yIndex] % 2 == 1) {
	    		if (countAliveAround == 2)
	    			return true;
	    	}
	    	return false;
	    }
	}
}
