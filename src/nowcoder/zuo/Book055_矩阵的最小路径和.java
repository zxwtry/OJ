package nowcoder.zuo;

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book055_矩阵的最小路径和.java
 * @type        Book055_矩阵的最小路径和
 * @date        2016年11月25日 上午10:41:52
 * @details     给定一个矩阵m，从左上角，每次只能向右或者向下走，
 * @details     最后到达右下角的位置，路径上所有的数字累加起来就是路径和。
 * @details     返回所有的路径中最小的路径和
 */
public class Book055_矩阵的最小路径和 {
	public static void main(String[] args) {
		debugMySolution();
	}
	
	static void debugMySolution() {
		int n = 10;
		int min = 0;
		int max = 1000;
		int[][] m = new int[][] {
			tools.Random随机生成器.A_生成一个随机数据(n, min, max),
			tools.Random随机生成器.A_生成一个随机数据(n, min, max),
			tools.Random随机生成器.A_生成一个随机数据(n, min, max),
			tools.Random随机生成器.A_生成一个随机数据(n, min, max),
		};
		MySolution s = new MySolution();
		MySolution2 s2 = new MySolution2();
		System.out.println(s.minPath(m) + "..." + s2.minPath(m));
	}

	static class MySolution {
		public int minPath(int[][] m) {
			if (m == null || m.length < 1 || m[0] == null || m[0].length < 1)	return 0;
			int[][] dp = new int[m.length][m[0].length];
			dp[0][0] = m[0][0];
			for (int i = 1; i < m.length; i ++)
				dp[i][0] = m[i][0] + dp[i-1][0];
			for (int j = 1; j < m[0].length; j ++)
				dp[0][j] = m[0][j] + dp[0][j-1];
			for (int i = 1; i < m.length; i ++)
				for (int j = 1; j < m[0].length; j ++)
					dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + m[i][j];
			return dp[m.length - 1][m[0].length - 1];
		}
	}
	
	static class MySolution2 {
		public int minPath(int[][] m) {
			if (m == null || m.length < 1 || m[0] == null || m[0].length < 1)	return 0;
			int[] dp = new int[m.length];
			dp[0] = m[0][0];
			for (int i = 1; i < m.length; i ++)
				dp[i] = dp[i - 1] + m[i][0];
			for (int j = 1; j < m[0].length; j ++) {
				dp[0] = dp[0] + m[0][j];
				for (int i = 1; i < m.length; i ++) {
					dp[i] = Math.min(dp[i], dp[i - 1]) + m[i][j];
				}
			}
			return dp[m.length - 1];
		}
	}
	
}
