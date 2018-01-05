package leetcode;

/*
 * 	Given an integer n, generate a square matrix filled with elements 
 * 	from 1 to n2 in spiral order.

	For example,
	Given n = 3,
	
	You should return the following matrix:
	[
	 [ 1, 2, 3 ],
	 [ 8, 9, 4 ],
	 [ 7, 6, 5 ]
	]
 */

public class P059_SpiralMatrixII {
	public static void main(String[] args) {
		int[][] ans = new Solution().generateMatrix(9);
		tools.Utils.A_打印二维数组(ans);
	}
	/*
	 * 	有一次试错(null和int[0]。。。)
	 * 	很快写完，套路的感觉太好了
	 * 	1 ms
	 * 	2.83% 
	 */
	static class Solution {
		int[][] ans = null;
		int count = 0;
	    public int[][] generateMatrix(int n) {
	    	if (n < 1)
	    		return new int[0][0];
	    	ans = new int[n][n];
	    	int eni = n >> 1;
	    	for (int i = 0; i != eni; i ++)
	    		fourEdge(i, n - 1 - i, n - 1 - i);
	    	if ((n & 0x1) == 1)
	    		ans[eni][eni] = ++count;
	    	return ans;
	    }
		private void fourEdge(int i, int j, int k) {
			int t = 0;
			for (t = i; t < k; t ++)
				ans[i][t] = ++ count;
			for (t = i; t < j; t ++)
				ans[t][k] = ++ count;
			for (t = k; t > i; t --)
				ans[j][t] = ++ count;
			for (t = j; t > i; t --)
				ans[t][i] = ++ count;
		}
	}
}
