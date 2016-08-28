package leetcode;

/*
 * 	Given N axis-aligned rectangles where N > 0, determine 
 * 	if they all together form an exact cover of a rectangular region.

	Each rectangle is represented as a bottom-left point and a top-right point.
	For example, a unit square is represented as [1,1,2,2].
	(coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).
 */

public class P391_PerfectRectangle {
	public static void main(String[] args) {
//		System.out.println(new Solution().isRectangleCover(new int[][]{
//			{1,1,3,3},
//			{3,1,4,2},
//			{3,2,4,4},
//			{1,3,2,4},
//			{2,3,3,4}
//		}));
//		System.out.println(new Solution().isRectangleCover(new int[][]{
//			{1,1,2,3},
//			{1,3,2,4},
//			{3,1,4,2},
//			{3,2,4,4}
//		}));
//		System.out.println(new Solution().isRectangleCover(new int[][]{
//			{1,1,3,3},
//			{3,1,4,2},
//			{1,3,2,4},
//			{3,2,4,4}
//		}));
//		System.out.println(new Solution().isRectangleCover(new int[][]{
//			{1,1,3,3},
//		  	{3,1,4,2},
//		  	{1,3,2,4},
//		  	{2,2,4,4}
//		}));
		
	}
	static class Solution {
		int[] sign = null;
		int row = 0, col = 0;
	    public boolean isRectangleCover(int[][] rectangles) {
	    	if (rectangles == null || rectangles.length < 1 || rectangles[0].length < 1)
	    		return false;
	    	int maxI = 0, minI = 0, minSum = rectangles[0][0] + rectangles[0][1], maxSum = rectangles[0][2] + rectangles[0][3];
	        long sumSquare = (rectangles[0][2] - rectangles[0][0]) * (rectangles[0][3] - rectangles[0][1]);
	    	//先进行面积统计
	    	for (int i = 1; i != rectangles.length; i ++) {
	    		sumSquare += (rectangles[i][2] - rectangles[i][0]) * (rectangles[i][3] - rectangles[i][1]);
	    		if (rectangles[i][0] + rectangles[i][1] < minSum) {
	    			minSum = rectangles[i][0] + rectangles[i][1];
	    			minI = i;
	    		}
	    		if (rectangles[i][2] + rectangles[i][3] > maxSum) {
	    			maxSum = rectangles[i][2] + rectangles[i][3];
	    			maxI = i;
	    		}
	    	}
	    	col = rectangles[maxI][2] - rectangles[minI][0];
	    	row = rectangles[maxI][3] - rectangles[minI][1];
	    	sign = new int[(int)(((long)col) * row + 32) >> 5];
	    	if (sumSquare != ((long)rectangles[maxI][2] - rectangles[minI][0]) * (rectangles[maxI][3] - rectangles[minI][1]))
	    		return false;
	    	for (int i = 0; i != rectangles.length; i ++) {
	    		for (int j = rectangles[i][2] - rectangles[minI][0] - 1; j >= rectangles[i][0] - rectangles[minI][0]; j --) {
	    			for (int k = rectangles[i][3] - rectangles[minI][1] - 1; k >= rectangles[i][1] - rectangles[minI][1]; k --) {
//	    				System.out.println(k +"..."+j);
//	    				if (k == 0 && j == 1)
//	    					System.out.println();
	    				if(! setIndex(k, j))
	    					return false;
	    			}
	    		}
	    	}
	    	return true;
	    }
	    private boolean setIndex(int i, int j) {
	    	int len = row * i + j;
	    	int index = len >> 5;
	    	int bit = len - (index << 5);
	    	sign[index] = sign[index] ^ (1 << bit);
	    	return ((sign[index] >>> bit) & 0x1) == 1;
	    }
	}
}
