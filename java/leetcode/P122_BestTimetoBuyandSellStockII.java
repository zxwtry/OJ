package leetcode;

/**
    Say you have an array for which the ith element is the price of
    a given stock on day i.

	Design an algorithm to find the maximum profit. 
	You may complete as many transactions as you like 
	(ie, buy one and sell one share of the stock multiple times). 
	However, you may not engage in multiple transactions at the same time 
	(ie, you must sell the stock before you buy again).
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P122_BestTimetoBuyandSellStockII.java
 * @type        P122_BestTimetoBuyandSellStockII
 * @date        2017年5月8日 下午2:14:39
 * @details     AC 2ms 10.44%
 */
public class P122_BestTimetoBuyandSellStockII {
	static class Solution {
	    public int maxProfit(int[] prices) {
	    	if (prices == null) {
	    		return 0;
	    	}
	        int ans = 0;
	        for (int i = prices.length-1; i > 0; i --) 
	            ans += Math.max(0, prices[i] - prices[i-1]);
	        return ans;
	    }
	}
}
