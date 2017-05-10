package leetcode;

/*
    Given a 2D board containing 'X' and 'O' (the letter O),
    capture all regions surrounded by 'X'.

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

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P130_SurroundedRegions.java
 * @type        P130_SurroundedRegions
 * @date        2017年5月10日 上午11:20:47
 * @details     Solution: AC 4ms 95.29%
 */
public class P130_SurroundedRegions {
	static class Solution {
	    int rn = 0, cn = 0;
	    char[][] b = null;
	    public void solve(char[][] b) {
	        int i = 0, j = 0;
	        this.b = b;
	        rn = b == null ? 0 : b.length;
	        if (rn < 3) return;
	        cn = b[0] == null ? 0 : b[0].length;
	        if (cn < 3) return;
	        for (i = 0; i < rn; i ++) {
	            search(i, 0);
	            search(i, cn-1);
	        }
	        for (j = 1; j < cn-1; j ++) {
	            search(0, j);
	            search(rn-1, j);
	        }
	        for (i = 0; i < rn; i ++) {
	            for (j = 0; j < cn; j ++) {
	                if (b[i][j] == 'O') {
	                    b[i][j] = 'X';
	                } else if (b[i][j] == '1') {
	                    b[i][j] = 'O';
	                }
	            }
	        }
	    }
	    void search(int i, int j) {
	        if (b[i][j] != 'O') return;
	        b[i][j] = '1';
	        if(i > 1)search(i-1, j);
	        if(i+1 < rn)search(i+1, j);
	        if(j > 1)search(i, j-1);
	        if(j+1 < cn)search(i, j+1);
	    }
	}
}