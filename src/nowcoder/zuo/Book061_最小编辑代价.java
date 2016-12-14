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
//		Solution1 sol1 = new Solution1();
//		Solution2 sol2 = new Solution2();
//		String s1 = tools.StringUtils.A_生成随机数组A_Z(200);
//		String s2 = tools.StringUtils.A_生成随机数组A_Z(300);
		int ic = 5;
		int dc = 3;
		int rc = 200;
//		System.out.println(sol1.minEditCost(s1, s2, ic, dc, rc) +"..."+ sol2.minEditCost(s1, s2, ic, dc, rc));
		System.out.println(new Solution1().minEditCost("abc", "abc", ic, dc, rc));
		
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
	
	static class Solution2 {
		public int minEditCost(String s1, String s2, int ic, int dc, int rc) {
			if (s1 == null || s2 == null) return 0;
			int row = s1.length() + 1;
			int col = s2.length() + 1;
			if (row < col) return minEditCost(s2, s1, dc, ic, rc);
			int[] dp = new int[col];
			for (int i = 1; i < col; i ++) dp[i] = ic * 1;
			for (int i = 1; i < row; i ++) {
				int pre = dp[0];
				dp[0] = dc * i;
				for (int j = 1; j < col; j ++) {
					int tmp = dp[j];
					if (s1.charAt(i-1) == s2.charAt(j-1)) {
						dp[j] = pre;
					} else {
						dp[j] = pre + rc;
					}
					dp[j] = Math.min(dp[j], dp[j-1] + ic);
					dp[j] = Math.min(dp[j], tmp + dc);
					pre = tmp;
				}
			}
			return dp[col - 1];
 		}
	}
	
	
}
