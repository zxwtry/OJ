package leetcode;

/*
 * 	Say you have an array for which the ith element is the price of 
 * 	a given stock on day i.

	If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
	
	Example 1:
	Input: [7, 1, 5, 3, 6, 4]
	Output: 5
	
	max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
	Example 2:
	Input: [7, 6, 4, 3, 1]
	Output: 0
	
	In this case, no transaction is done, i.e. max profit = 0.
 */

public class P121_BestTimetoBuyandSellStock {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.maxProfit(new int[] {1, 2, 3, 4, 5}));
		System.out.println(s.maxProfit(new int[] {5, 4, 3, 2, 1}));
		System.out.println(s.maxProfit(new int[] {7, 1, 5, 3, 6, 4}));
	}
	/*
	 * 	AC
	 * 	3 ms
	 */
	static class Solution {
	    public int maxProfit(int[] prices) {
	    	if (prices == null) {
	    		return 0;
	    	}
	    	int min = Integer.MAX_VALUE;
	    	int ans = 0;
	    	for (int i = 0; i < prices.length; i ++) {
	    		min = Math.min(min, prices[i]);
	    		ans = Math.max(ans, prices[i] - min);
	    	}
	        return ans;
	    }
	}
}
