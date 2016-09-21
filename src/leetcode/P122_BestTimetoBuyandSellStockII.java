package leetcode;


/*
 * 	Say you have an array for which the ith element is the price of
 *  a given stock on day i.

	Design an algorithm to find the maximum profit. 
	You may complete as many transactions as you like 
	(ie, buy one and sell one share of the stock multiple times). 
	However, you may not engage in multiple transactions at the same time 
	(ie, you must sell the stock before you buy again).
 */

public class P122_BestTimetoBuyandSellStockII {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.maxProfit(new int[] {1, 2, 3, 4, 5}));
		System.out.println(s.maxProfit(new int[] {5, 4, 3, 2, 1}));
		System.out.println(s.maxProfit(new int[] {7, 1, 5, 3, 6, 4}));
	}
	/*
	 * 	AC
	 * 	2 ms
	 */
	static class Solution {
	    public int maxProfit(int[] prices) {
	    	if (prices == null) {
	    		return 0;
	    	}
	        int min = Integer.MAX_VALUE;
	        int ans = 0;
	        for (int i = 0; i < prices.length; i ++) {
	        	if (prices[i] > min) {
	        		ans += prices[i] - min;
	        		min = prices[i];
	        	} else {
	        		min = prices[i];
	        	}
	        }
	        return ans;
	    }
	}
}
