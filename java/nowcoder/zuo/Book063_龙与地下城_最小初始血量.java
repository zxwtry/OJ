package nowcoder.zuo;

/**
 * 	给定一个二维数组m，含义是一张地图，例如：
 * 	-2	-3	3
 * 	-5	-10	1
 * 	0	30	-5
 * 	游戏规则：
 * 		1，从左上角出发，每次只能向右或向下走，最后到达右下角。
 * 		2，m[i][j] 表示血量增减。
 * 		3，当血量小于等于0，这条路径失败。
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book063_龙与地下城_最小初始血量.java
 * @type        Book063_龙与地下城_最小初始血量
 * @date        2016年12月15日 下午9:07:20
 * @details     
 */
public class Book063_龙与地下城_最小初始血量 {
	static class Solution1 {
		public int minHP(int[][] m) {
			if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) return 1;
			int row = m.length, col = m[0].length;
			int[][] dp = new int[row--][col--];
			dp[row][col] = m[row][col] > 0 ? 1 : -m[row][col] + 1;
			for (int j = col - 1; j >= 0; j --) dp[row][j] = Math.max(dp[row][j+1] - m[row][j], 1);
			int r = 0, d = 0;
			for (int i = row - 1; i >= 0; i --) {
				dp[i][col] = Math.max(dp[i+1][col] - m[i][col], 1);
				for (int j = col - 1; j >= 0; j --) {
					r = Math.max(dp[i][j+1] - m[i][j], 1);
					d = Math.max(dp[i+1][j] - m[i][j], 1);
					dp[i][j] = Math.min(r, d);
				}
			}
			return dp[0][0];
		}
	}
	
	static class Solution2 {
		public int minHP(int[][] m) {
			if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) return 1;
			int more = Math.max(m.length, m[0].length);
			int less = Math.min(m.length, m[0].length);
			boolean rowmore = more == m.length;
			int[] dp = new int[less];
			int tmp = m[m.length - 1][m[0].length - 1];
			dp[less - 1] = tmp > 0 ? 1 : -tmp+1;
			int row = 0;
			int col = 0;
			for (int j = less - 2; j >= 0; j --) {
				row = rowmore ? more - 1 : j;
				col = rowmore ? j : more - 1;
				dp[j] = Math.max(dp[j + 1] - m[row][col], 1);
			}
			int c1 = 0;
			int c2 = 0;
			for (int i = more - 2; i >= 0; i --) {
				row = rowmore ? i : less - 1;
				col = rowmore ? less - 1 : i;
				dp[less - 1] = Math.max(dp[less - 1] - m[row][col], 1);
				for (int j = less - 2; j >= 0; j --) {
					row = rowmore ? i : j;
					col = rowmore ? j : i;
					c1 = Math.max(dp[j] - m[row][col], 1);
					c2 = Math.max(dp[j+1] - m[row][col], 1);
					dp[j] = Math.min(c1, c2);
				}
			}
			return dp[0];
		}
	}
}
