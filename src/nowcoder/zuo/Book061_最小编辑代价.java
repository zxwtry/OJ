package nowcoder.zuo;

/**
 * 	
 * 
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book061_最小编辑代价.java
 * @type        Book061_最小编辑代价
 * @date        2016年12月10日 下午12:09:44
 * @details     
 */
public class Book061_最小编辑代价 {
	public static void main(String[] args) {
	}
	
	static class Solution1 {
		public int minEditCost(String s1, String s2, int ic, int dc, int rc) {
			int row = s1.length() + 1, col = s2.length() + 1;
			int[][] dp = new int[row][col];
			for (int i = 1; i < row; i ++) {
				dp[i][0] = i * dc;
			}
			for (int j = 1; j < col; j ++) {
				dp[0][j] = j * ic;
			}
			for (int i = 1; i < row; i ++) {
				for (int j = 1; j < col; j ++) {
					if (s1.charAt(i-1) == s2.charAt(j-1)) {
						dp[i][j] = dp[i-1][j-1];
					} else {
						dp[i][j] = dp[i-1][j-1] + rc;
					}
					dp[i][j] = Math.min(dp[i][j],  dp[i-1][j] + dc);
					dp[i][j] = Math.min(dp[i][j],  dp[i][j-1] + ic);
				}
			}
			return dp[row-1][col-1];
		}
	}
	
}
