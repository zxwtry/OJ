package leetcode;

/**
 * 	Given N axis-aligned rectangles where N > 0, determine 
 * 	if they all together form an exact cover of a rectangular region.
 * 	
 * 	Each rectangle is represented as a bottom-left point and a top-right point.
 * 	For example, a unit square is represented as [1,1,2,2].
 * 	(coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).
 */

public class P391_PerfectRectangle {
	/*
	 * 	对普通用例没有计算错误
	 * 	大数字用例，MLE
	 * 	主要是在判断上使用bit，还是不够，应该使用更加省内存的方法
	 */
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
	    				if(! setIndex(k, j))
	    					return false;
	    			}
	    		}
	    	}
	    	return true;
	    }
	    private boolean setIndex(int k, int j) {
	    	int len = col * k + j;
	    	int index = len >> 5;
	    	int bit = len - (index << 5);
	    	sign[index] = sign[index] ^ (1 << bit);
	    	return ((sign[index] >>> bit) & 0x1) == 1;
	    }
	}
	/*
	 * 	不使用bit，使用一维数组计数的方式
	 * 	210 ms
	 * 	逻辑还是很有点复杂，不过AC了
	 */
	static class Solution2 {
		int[] sign = null;
		int row = 0, col = 0;
	    public boolean isRectangleCover(int[][] rectangles) {
	    	if (rectangles == null || rectangles.length < 1 || rectangles[0].length < 1)
	    		return false;
	    	int maxI = 0, minI = 0, minSum = rectangles[0][0] + rectangles[0][1], maxSum = rectangles[0][2] + rectangles[0][3];
	        long sumSquare = (rectangles[0][2] - rectangles[0][0]) * (rectangles[0][3] - rectangles[0][1]);
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
	    	sign = new int[col];
	    	row = rectangles[maxI][3] - rectangles[minI][1];
	    	boolean is00Twice = false;
	    	if (sumSquare != ((long)rectangles[maxI][2] - rectangles[minI][0]) * (rectangles[maxI][3] - rectangles[minI][1]))
	    		return false;
	    	for (int i = 0; i != rectangles.length; i ++) {
	    		if (rectangles[i][0] == rectangles[minI][0] && rectangles[i][1] == rectangles[minI][0]) {
    				if (is00Twice)
    					return false;
    				is00Twice = true;
    			}
	    		for (int j = rectangles[i][2] - rectangles[minI][0] - 1; j >= rectangles[i][0] - rectangles[minI][0]; j --)
	    			sign[j] += rectangles[i][3] - rectangles[i][1];
	    	}
	    	boolean isOK = true;
	    	for (int i = 0; i != sign.length; i ++)
	    		isOK &= sign[i] == row;
	    	if (!isOK)
	    		return false;
	    	sign = new int[row];
	    	for (int i = 0; i != rectangles.length; i ++)
	    		for (int j = rectangles[i][3] - rectangles[minI][1] - 1; j >= rectangles[i][1] - rectangles[minI][1]; j --)
	    			sign[j] += rectangles[i][2] - rectangles[i][0];
	    	isOK = true;
	    	for (int i = 0; i != sign.length; i ++)
	    		isOK &= sign[i] == col;
	    	return isOK;
	    }
	}
}
