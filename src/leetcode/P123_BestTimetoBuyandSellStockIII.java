package leetcode;

/*
    Say you have an array for which the ith element is the price of
    a given stock on day i.

	Design an algorithm to find the maximum profit. You may complete
	at most two transactions.
	
	Note:
	You may not engage in multiple transactions at the same time 
	(ie, you must sell the stock before you buy again).
 */

public class P123_BestTimetoBuyandSellStockIII {
	static class Solution {
	    public int maxProfit(int[] prices) {
	    	if (prices == null || prices.length == 0) {
	    		return 0;
	    	}
	    	int ans = 0;
	    	int[] forward = new int[prices.length];
	    	int forward_min = Integer.MAX_VALUE;
	    	for (int i = 0; i < prices.length; i ++) {
	    		forward_min = Math.min(forward_min, prices[i]);
	    		if (i != 0) {
	    			forward[i] = Math.max(prices[i] - forward_min, forward[i - 1]);
	    		}
	    	}
		    int[] backward = new int[prices.length];
		    int backward_max = Integer.MIN_VALUE;
		    for (int i = prices.length - 1; i > - 1; i --) {
		    	backward_max = Math.max(backward_max, prices[i]);
		    	if (i != prices.length - 1) {
		    		backward[i] = Math.max(backward_max - prices[i], backward[i + 1]);
		    	}
		    }
		    for (int i = 0; i < prices.length; i ++) {
		    	ans = Math.max(ans, forward[i] + backward[i]);
		    }
	    	return ans;
	    }
	}
	static class Solution2 {
	    public int maxProfit(int[] prices) {
	    	if (prices == null || prices.length == 0) {
	    		return 0;
	    	}
	    	int ans = 0;
	    	int[] forward = new int[prices.length];
	    	int forward_min = Integer.MAX_VALUE;
	    	for (int i = 0; i < prices.length; i ++) {
	    		forward_min = Math.min(forward_min, prices[i]);
	    		if (i != 0) {
	    			forward[i] = Math.max(prices[i] - forward_min, forward[i - 1]);
	    		}
	    	}
		    int backward = 0;
		    int backward_max = Integer.MIN_VALUE;
		    for (int i = prices.length - 1; i > - 1; i --) {
		    	backward_max = Math.max(backward_max, prices[i]);
		    	if (i != prices.length - 1) {
		    		backward = Math.max(backward_max - prices[i], backward);
		    	}
		    	ans = Math.max(ans, forward[i] + backward);
		    }
	    	return ans;
	    }
	}
}