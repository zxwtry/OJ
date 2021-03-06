package leetcode;


/**
 * Given an 2D board, count how many different battleships are in it. 
 * The battleships are represented with 'X's, empty slots are represented with '.'s. 
 * You may assume the following rules:
 *	
 *	You receive a valid board, made of only battleships or empty slots.
 *	Battleships can only be placed horizontally or vertically. In other words,
 *	 they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), 
 *	 where N can be of any size.
 *	At least one horizontal or vertical cell separates between two battleships
 *	 - there are no adjacent battleships.
 *	Example:
 *	X..X
 *	...X
 *	...X
 *	In the above board there are 2 battleships.
 *	Invalid Example:
 *	...X
 *	XXXX
 *	...X
 *	This is an invalid board that you will not receive - as battleships will always
 *	 have a cell separating between them.
 *	Follow up:
 *	Could you do it in one-pass, using only O(1) extra memory and without modifying 
 *	the value of the board?
 */


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P419_BattleshipsInABoard.java
 * @type        P419_BattleshipsInABoard
 * @date        2016年12月8日 下午10:24:02
 * @details     Solution1: AC 5ms 15.48% 
 */
public class P419_BattleshipsInABoard {
	static class Solution1 {
	    public int countBattleships(char[][] bs) {
	        int count = 0;
	        for (int i = 0; i < bs.length; i ++)
	            for (int j = 0; j < bs[0].length; j ++)
	                if (bs[i][j] == 'X' && access(bs, i - 1,j) == '.' && access(bs, i,j - 1) == '.')
	                    count ++;
            return count;
	    }
	    private char access(char[][] bs, int i, int j) {
	        if (i < 0 || j < 0) return '.';
	        return bs[i][j];
	    }
	}
}
