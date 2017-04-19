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
	
	//AC 25ms 57.34%
	static class Solution2 {
	    public int largestRectangleArea(int[] h) {
	        if (h == null || h.length == 0)  return 0;
	        int a = 0, hn = h.length;
	        Stack<Integer> s = new Stack<Integer>();
	        for (int i = 0; i <= hn; i ++) {
	            int hv = i == hn ? 0 : h[i];
	            if (s.isEmpty() || hv > h[s.peek()]) {
	                s.add(i);
	            } else {
	                int j = s.pop();
	                int l = s.isEmpty() ? i : i-1-s.peek();
	                a = Math.max(a, h[j] * l);
	                i --;
	            }
	        }
	        return a;
	    }
	}
	//AC 7ms 91.14%
	static class Solution3 {
	    public int largestRectangleArea(int[] h) {
	        if (h == null || h.length == 0)  return 0;
            int a = 0, hn = h.length, mi = 0;
            int[] m = new int[hn];
            for (int i = 0; i <= hn; i ++) {
                int hv = i == hn ? 0 : h[i];
                if (mi == 0 || hv > h[m[mi - 1]]) {
                    m[mi ++] = i;
                } else {
                    int j = m[mi-1];
                    mi --;
                    int l = mi == 0 ? i : i - 1 - m[mi - 1];
                    a = Math.max(a, h[j] * l);
                    i --;
                }
            }
            return a;
	    }
	}
}