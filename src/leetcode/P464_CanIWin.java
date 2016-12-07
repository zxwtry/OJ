package leetcode;

/*
 * 	In the "100 game," two players take turns adding, to a running total, any integer from
 *  1..10. The player who first causes the running total to reach or exceed 100 wins.
	
	What if we change the game so that players cannot re-use integers?
	
	For example, two players might take turns drawing from a common pool of numbers of 1..15 
	without replacement until they reach a total >= 100.
	
	Given an integer maxChoosableInteger and another integer desiredTotal, determine if 
	the first player to move can force a win, assuming both players play optimally.
	
	You can always assume that maxChoosableInteger will not be larger than 20 and 
	desiredTotal will not be larger than 300.
	
	Example
	
	Input:
	maxChoosableInteger = 10
	desiredTotal = 11
	
	Output:
	false
	
	Explanation:
	No matter which integer the first player choose, the first player will lose.
	The first player can choose an integer from 1 up to 10.
	If the first player choose 1, the second player can only choose integers from 2 up to 10.
	The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
	Same with other integers chosen by the first player, the second player will always win.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P464_CanIWin.java
 * @type        P464_CanIWin
 * @date        2016年11月20日 下午6:49:31
 * @details     
 */
public class P464_CanIWin {
	public static void main(String[] args) {
		debugSolution1();
	}
	
	/**
	 * @method      debugSolution1
	 * @parameter   
	 * @return      void
	 * @details     
	 */
	private static void debugSolution1() {
		Solution1 s = new Solution1();
		int m = 20;
		int d = 111;
		System.out.println(s.canIWin(m, d));
	}

	/**
	 * @author      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     leetcode
	 * @file        P464_CanIWin.java
	 * @type        Solution1
	 * @date        2016年11月20日 下午6:49:57
	 * @details     Solution1
	 */
	static class Solution1 {
	    public boolean canIWin(int m, int d) {
	    	if (d <= m) {
	    		return true;
	    	} else if (d > (m + 1) * m / 2) {
	    		return false;
	    	}
	    	boolean[] used = new boolean[m + 1];
	    	boolean[] status = new boolean[]{false, false};
	    	for (int i = used.length - 1; i > 0 ; i --) {
	    		status[0] = false;
	    		status[1] = false;
	    		used[i] = true;
	    		seach1(used, d - i, status);
	    		if (! status[1]) {
	    			return true;
	    		}
	    	}
	        return false;
	    }

		/**
		 * @method      seach0
		 * @parameter   
		 * @return      void
		 * @details     
		 */
		private void seach0(boolean[] used, int d, boolean[] status) {
			if (status[1]) {
				return;
			}
			int add = 0;
			for (int i = used.length - 1; i > 0 ; i --) {
				if (! used[i] && d - i <= 0) {
					add += i;
					status[0] = true;
					return;
				}
			}
			if (add < d) {
				return;
			}
			for (int i = used.length - 1; i > 0 ; i --) {
				if (! used[i]) {
					used[i] = true;
					seach1(used, d, status);
					if (! status[1]) {
						break;
					}
					used[i] = false;
				}
			}
			
		}

		/**
		 * @method      seach1
		 * @parameter   used		---	纪录是否已经被使用
		 * @parameter   d			---	还剩下多少
		 * @return      void
		 * @details     这里是第1个人的选择
		 */
		private void seach1(boolean[] used, int d, boolean[] status) {
			if (status[1]) {
				return;
			}
			int add = 0;
			for (int i = used.length - 1; i > 0 ; i --) {
				if (! used[i] && d - i <= 0) {
					add += i;
					status[1] = true;
					return;
				}
			}
			if (add < d) {
				return;
			}
			for (int i = used.length - 1; i > 0 ; i --) {
				if (! used[i]) {
					used[i] = true;
					seach0(used, d, status);
					if (status[1]) {
						return;
					}
					used[i] = false;
				}
			}
		}		
	}
	
}
