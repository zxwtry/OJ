package nowcoder.zuo;

public class Book059_最长公共子序列 {
	public static void main(String[] args) {
	}

	static void testMySolution_递归版本和MySolution_DP() {
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(10, 0, 25);
		char[] c1 = new char[arr.length];
		for (int index = 0; index < arr.length; index ++)
			c1[index] = (char) ('A' + arr[index]);
		arr = tools.Random随机生成器.A_生成一个随机数据(10, 0, 25);
		char[] c2 = new char[arr.length];
		for (int index = 0; index < arr.length; index ++)
			c2[index] = (char) ('A' + arr[index]);
		MySolution_DP s1 = new MySolution_DP();
		MySolution_递归版本 s2 = new MySolution_递归版本();
		System.out.println(s1.maxCommonSequence(c1, c2) == s2.maxCommonSequence(c1, c2));
	}

	static void debugMySolution_DP() {
		MySolution_DP s = new MySolution_DP();
		char[] c1 = "1A2C3D4B56".toCharArray();
		char[] c2 = "1B1D23A45B6A".toCharArray();
		System.out.println(s.maxCommonSequence(c1, c2));
	}

	static void debugMySolution_递归版本() {
		MySolution_递归版本 s = new MySolution_递归版本();
		char[] c1 = "1A2C3D4B56".toCharArray();
		char[] c2 = "1B1D23A45B6A".toCharArray();
		System.out.println(s.maxCommonSequence(c1, c2));
	}

	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book059_最长公共子序列.java
	 * @type        MySolution_递归版本
	 * @date        2016年11月30日 下午8:03:15
	 * @details     最基本的递归版本，一定要写出来，这个是DP的基础。
	 */
	static class MySolution_递归版本 {
		public int maxCommonSequence(char[] c1, char[] c2) {
			if (c1 == null || c1.length == 0 || c2 == null || c2.length == 0)	return 0;
			return maxCommonSequence(c1, c1.length - 1, c2, c2.length - 1);
		}
		int maxCommonSequence(char[] c1, int i1, char[] c2, int i2) {
			if (i1 == -1 || i2 == -1)	return 0;
			if (c1[i1] == c2[i2]) {
				return maxCommonSequence(c1, i1 - 1, c2, i2 - 1) + 1;
			} else {
				return Math.max(maxCommonSequence(c1, i1 - 1, c2, i2), maxCommonSequence(c1, i1, c2, i2 - 1));
			}
		}
	}
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book059_最长公共子序列.java
	 * @type        MySolution_DP
	 * @date        2016年11月30日 下午8:08:11
	 * @details     MySolution_递归版本 的 DP版本
	 */
	static class MySolution_DP {
		public int maxCommonSequence(char[] c1, char[] c2) {
			if (c1 == null || c1.length == 0 || c2 == null || c2.length == 0)	return 0;
			int[][] dp = new int[c1.length + 1][c2.length + 1];
			for (int i = 1; i <= c1.length; i ++) {
				for (int j = 1; j <= c2.length; j ++) {
					if (c1[i - 1] == c2[j - 1]) {
						dp[i][j] = dp[i - 1][j - 1] + 1;
					} else {
						dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
					}
				}
			}
			return dp[c1.length][c2.length];
		}
	}
}
