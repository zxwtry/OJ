package leetcode;

/**
 * 	You are playing the following Nim Game with your friend: 
 * 	There is a heap of stones on the table, each time one of 
 * 	you take turns to remove 1 to 3 stones. The one who removes 
 * 	the last stone will be the winner. You will take the first turn
 *  to remove the stones.
 * 
 *	Both of you are very clever and have optimal strategies for the
 *	game. Write a function to determine whether you can win the game
 *	given the number of stones in the heap.
 *	
 *	For example, if there are 4 stones in the heap, then you will
 *	never win the game: no matter 1, 2, or 3 stones you remove, the 
 *	last stone will always be removed by your friend.
 *	
 *	Hint:
 *	
 *	If there are 5 stones in the heap, could you figure out a way to 
 *	remove the stones such that you will always be the winner?
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P292_NimGame.java
 * @type        P292_NimGame
 * @date        2016年12月17日 下午10:33:24
 * @details     Solution1: MLE
 * @details     Solution2: TLE
 * @details     Solution3: AC 0ms 4.85%
 */
public class P292_NimGame {
	static class Solution1 {
	    public boolean canWinNim(int n) {
	        boolean[] dp = new boolean[Math.max(n + 1, 8)];
	        for (int index = 0; index < 8; index ++) dp[index] = true;
	        dp[4] = false;
	        boolean canWin = false;
	        for (int index = 8; index <= n; index ++) {
	        	canWin = true;
	        	for (int otherIndex = index - 1; otherIndex >= index - 3; otherIndex --) {
	        		canWin &= dp[otherIndex];
	        	}
	        	dp[index] = ! canWin;
	        }
	        return dp[n];
	    }
	}
	static class Solution2 {
		public boolean canWinNim(int n) {
			boolean[] dp = new boolean[] {true, true, true, false};
			if (n <= dp.length) return dp[n - 1];
			boolean canWin = false;
			for (int index = 5; index <= n; index ++) {
	        	canWin = true;
	        	for (int otherIndex = index - 1; otherIndex >= index - 3; otherIndex --) {
	        		canWin &= dp[getIndex(otherIndex, dp)];
	        	}
	        	dp[getIndex(index, dp)] = ! canWin;
	        }
			return dp[getIndex(n, dp)];
		}
		private int getIndex(int value, boolean[] dp) {
			return (value - 1) % dp.length;
		}
	}
	static class Solution3 {
		public boolean canWinNim(int n) {
			return n % 4 != 0;
		}
	}
 	static class StandardSolution {
		public boolean canWinNim(int n) {
			if (n >= 1 && n <= 3) return true;
			if (n == 4) return false;
			if (n >= 5 && n <= 7) return true;
			boolean isWin = true;
			for (int index = 1; index <= 3; index ++) {
				isWin &= canWinNim(n - index);
			}
			return !isWin;
		}
	}
}
