package leetcode;

import java.util.Stack;

public class P084_LargestRectangleInHistogram {
	public static void main(String[] args) {
		int[] heights = null;
		heights = new int[] {2, 1, 5, 6, 3};
		System.out.println(new Solution().largestRectangleArea(heights));
	}
	/*
	 * 	对于最好用的附近数据结构，栈的使用还远远不够
	 * 	AC
	 * 	24 ms
	 */
	static class Solution {
	    public int largestRectangleArea(int[] heights) {
	    	if (heights == null || heights.length == 0)
	    		return 0;
	    	int maxArea = 0;
	    	Stack<Integer> stk = new Stack<Integer>();
	    	for (int i = 0; i != heights.length; i ++) {
	    		while (! stk.isEmpty() && heights[i] < heights[stk.peek()]) {
	    			int j = stk.pop();
	    			int k = stk.isEmpty() ? -1 : stk.peek();
	    			maxArea = Math.max(maxArea, (i - k - 1) * heights[j]);
	    		}
	    		stk.push(i);
	    	}
	    	while (! stk.isEmpty()) {
	    		int j = stk.pop();
	    		int k = stk.isEmpty() ? -1 : stk.peek();
    			maxArea = Math.max(maxArea, (heights.length- k - 1) * heights[j]);
	    	}
	        return maxArea;
	    }
	}
}