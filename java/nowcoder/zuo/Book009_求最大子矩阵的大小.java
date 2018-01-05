package nowcoder.zuo;

import java.util.Stack;

/*
 * 	书上有一种解法，不是非常懂。
 * 	自己也有一种解法，可以比较一下。
 * 	map 是 0 1 矩阵，其中	
 * 	1 代表可框选的
 * 	0 代表不可框选的
 */

public class Book009_求最大子矩阵的大小 {
	public static void main(String[] args) {
		
	}
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
