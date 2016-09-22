package leetcode;

import java.util.ArrayList;

/*
 * 	Say you have an array for which the ith element is the price of
 * 	 a given stock on day i.

	Design an algorithm to find the maximum profit. You may complete
	 at most two transactions.
	
	Note:
	You may not engage in multiple transactions at the same time 
	(ie, you must sell the stock before you buy again).
 */

public class P123_BestTimetoBuyandSellStockIII {
	public static void main(String[] args) {
		Solution s = new Solution();
//		System.out.println(s.maxProfit(new int[] {7, 1, 5, 3, 6, 4}));
//		System.out.println(s.maxProfit(new int[] {9, 1, 2, 3, 4, 2, 3, 4, 3, 4}));
//		System.out.println(s.maxProfit(new int[] {1, 2, 0, 3}));
//		System.out.println(s.maxProfit(new int[] {1, 2, 3, 2, 7, 0, 100}));
//		System.out.println(s.maxProfit(new int[] {1, 2, 3, 2, 7, 0, 4, 3, 100}));
//		System.out.println(s.maxProfit(new int[] {9, 8, 7, 6}));
		System.out.println(s.maxProfit(new int[] {1, 2}));
	}
	/*
	 * 	一次WA
	 * 	AC
	 * 	O(N ^ 3)
	 * 	56 ms
	 */
	static class Solution {
	    public int maxProfit(int[] prices) {
	    	int ans = 0;
	    	ArrayList<Integer> add = new ArrayList<Integer>();
	    	ArrayList<Integer> pre = new ArrayList<Integer>();
	    	int index = - 1;
	    	for (int i = 0; i < prices.length; i ++) {
	    		if (index == -1) {
	    			add.add(i);
	    			pre.add(0);
	    			index ++;
	    			continue;
	    		}
	    		if (prices[i] > prices[add.get(index)]) {
	    			add.set(index, i);
	    		} else if (prices[i] < prices[add.get(index)]) {
	    			if (i == pre.get(index) + 1) {
	    				add.remove(index);
	    				pre.remove(index);
	    				add.add(i);
	    				pre.add(i);
	    			} else {
	    				add.add(i);
	    				pre.add(i);
	    				index ++;
	    			}
	    		}
	    	}
	    	if (index == - 1) {
	    		return 0;
	    	}
	    	if (prices.length == pre.get(index) + 1) {
	    		add.remove(index);
	    		pre.remove(index);
	    	}
	    	int i_pre_min = Integer.MAX_VALUE;
	    	for (int i = 0; i < add.size(); i ++) {
    			i_pre_min = Math.min(i_pre_min, prices[pre.get(i)]);
    			int i_val = prices[add.get(i)] - i_pre_min;
    			ans = Math.max(ans, i_val);
	    		for (int j = i + 1; j < add.size(); j ++) {
	    			if (add.get(i) >= pre.get(j)) {
	    				continue;
	    			}
	    			int j_pre_min = prices[pre.get(j)];
	    			for (int k = i + 1; k < j; k ++) {
	    				j_pre_min = Math.min(j_pre_min, prices[pre.get(k)]);
	    			}
	    			ans = Math.max(ans, i_val + prices[add.get(j)] - j_pre_min);
	    		}
	    	}
	        return ans;
	    }
	}
}
