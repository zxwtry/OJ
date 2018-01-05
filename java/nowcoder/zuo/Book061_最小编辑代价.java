package nowcoder.zuo;

/**
 * 	给定两个字符串s1和s2，
 * 	给定int ic代表插入的代价
 * 	给定int dc代表删除的代价
 * 	给定int rc代表替换的代价
 * 	将s1编辑成s2的最小代价
 * 	举例：
 * 		s1="abc" s2="adc", ic=5, dc=3, rc=2  返回2
 * 		s1="abc" s2="adc", ic=5, dc=3, rc=200  返回8
 * 		s1="abc" s2="abc", ic=5, dc=3, rc=200  返回0
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
			for (int i = 1; i < col; i ++) dp[i] = ic * i;
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
	
	static class Solution3 {
		public int minEditCost(String s1, String s2, int ic, int dc, int rc) {
			if (s1 == null || s2 == null) return 0;
			char[] c1 = s1.toCharArray();
			char[] c2 = s2.toCharArray();
			char[] longs = c1.length >= c2.length ? c1 : c2;
			char[] shorts = c1.length < c2.length ? c1 : c2;
			if (c1.length < c2.length) {
				int tmp = ic;
				ic = dc;
				dc = tmp;
			}
			int[] dp = new int[shorts.length + 1];
			for (int i = 1; i <= shorts.length; i ++)
				dp[i] = ic * i;
			for (int i = 1; i <= longs.length; i ++) {
				int pre = dp[0];
				dp[0] = dc * i;
				for (int j = 1; j <= shorts.length; j ++) {
					int tmp = dp[j];
					if (longs[i - 1] == shorts[j - 1]) {
						dp[j] = pre;
					} else {
						dp[j] = pre + rc;
					}
					dp[j] = Math.min(dp[j], dp[j - 1] + ic);
					dp[j] = Math.min(dp[j], tmp + dc);
					pre = tmp;
				}
			}
			return dp[shorts.length];
		}
	}
	
}
