package leetcode;

import java.util.Stack;

/*
 * 	Given a 2D binary matrix filled with 0's and 1's, 
 * 	find the largest rectangle containing only 1's and return its area.

	For example, given the following matrix:
	
	1 0 1 0 0
	1 0 1 1 1
	1 1 1 1 1
	1 0 0 1 0
	Return 6.
 */

public class P085_MaximalRectangle {
	public static void main(String[] args) {
//		System.out.println(new Solution().maxArea(new int[] {1, 2, 3, 2, 1}));
		System.out.println(new Solution().maximalRectangle(new char[][] {
			{'1','0','1','0','0'},
			{'1','0','1','1','1'},
			{'1','1','1','1','1'},
			{'1','0','0','1','0'}
		}));
		System.out.println(new Solution().maximalRectangle(new char[][] {
			{'1'}
		}));
		System.out.println(new Solution().maximalRectangle(new char[][] {
			{'0', '1'},
			{'1', '0'}
		}));
	}
	/*
	 * 	AC
	 * 	需要多学习栈
	 * 	38 ms
	 */
	static class Solution {
	    public int maximalRectangle(char[][] matrix) {
	    	if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
	    		return 0;
	    	int[] heights = new int[matrix[0].length];
	    	int maxArea = 0;
	    	for (int i = 0; i != matrix.length; i ++) {
	    		for (int j = 0; j != heights.length; j ++) {
	    			heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0;
	    		}
    			maxArea = Math.max(maxArea, maxArea(heights));
	    	}
	        return maxArea;
	    }
	    int maxArea(int[] heights) {
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
	    		stk.add(i);
	    	}
	    	while (! stk.isEmpty()) {
	    		int j = stk.pop();
	    		int k = stk.isEmpty() ? -1 : stk.peek();
	    		maxArea = Math.max(maxArea, (heights.length - k - 1) * heights[j]);
	    	}
	    	return maxArea;
	    }
	}
}
