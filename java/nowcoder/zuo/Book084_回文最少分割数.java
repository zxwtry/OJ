package nowcoder.zuo;

/**
 * 	给定一个字符串s，返回s全部切割成回文子串的最小分割数
 * 	举例：
 * 		s="ABA"，返回0
 * 		s="ACDCDCDAD"，返回2   "A"、"CDCDC"、"DAD"
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book084_回文最少分割数.java
 * @type        Book084_回文最少分割数
 * @date        2016年12月21日 下午6:08:24
 * @details     
 */
public class Book084_回文最少分割数 {
	static class Solution {
		public int minCut(String s) {
			if (s == null || s.length() == 0) return 0;
			int[] dp = new int[s.length() + 1];
			dp[dp.length - 1] = -1;
			boolean[][] p = new boolean[dp.length - 1][dp.length - 1];
			for (int i = dp.length - 2; i > -1; i --) {
				dp[i] = Integer.MAX_VALUE;
				for (int j = i; j < dp.length - 1; j ++) {
					if (s.charAt(i) == s.charAt(j) && (j - i < 2 || p[i+1][j-1])) {
						p[i][j] = true;
						dp[i] = Math.min(dp[i], dp[j+1] + 1);
					}
				}
			}
			return dp[0];
		}
	}
}
