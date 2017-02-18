package leetcode;

import java.util.LinkedList;

/**
 * 	You are given coins of different denominations and a total amount of money amount. 
 * 	Write a function to compute the fewest number of coins that you need to make up that amount.
 *  If that amount of money cannot be made up by any combination of the coins, return -1.
 * 	
 * 	Example 1:
 * 	coins = [1, 2, 5], amount = 11
 * 	return 3 (11 = 5 + 5 + 1)
 * 	
 * 	Example 2:
 * 	coins = [2], amount = 3
 * 	return -1.
 * 	
 * 	Note:
 * 	You may assume that you have an infinite number of each kind of coin
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P322_CoinChange.java
 * @type        P322_CoinChange
 * @date        2017年1月9日 下午9:09:00
 * @details     Solution1: TLE
 */
public class P322_CoinChange {
	static class Solution1 {
		int len = 0;
		int use = 0;
	    public int coinChange(int[] c, int a) {
	        if (a < 1) return 0;
	        if (c == null || c.length == 0) return -1;
	        len = c.length;
	        use = Integer.MAX_VALUE;
	        search(c, a, len - 1, 0);
	        return use;
	    }
		private void search(int[] c, int a, int index, int nowUse) {
			if (nowUse >= use) return;
			for (int i = index; i > -1; i --) {
				if (a - c[i] > 0) {
					search(c, a - c[i], index, nowUse + 1);
				} else if (a - c[i] == 0) {
					use = Math.min(use, nowUse + 1);
				}
			}
		}
	}
}
