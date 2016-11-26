package nowcoder.zuo;

import java.util.Arrays;

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book057_换钱的方法数.java
 * @type        Book057_换钱的方法数
 * @date        2016年11月26日 上午10:45:08
 * @details     给定一个arr，arr中的数都是整数
 * @details     给定一个aim，代表需要换的钱
 * @details     arr中的每一位，数量都足够
 * @details     求换钱的方法数
 */
public class Book057_换钱的方法数 {
	public static void main(String[] args) {
		debugSolution6动态规划();
	}
	
	static void debugSolution6动态规划() {
		for (int times = 0; times < 100; times ++) {
			int n = times;
			int min = 1;
			int max = n * n + 12;
			int[] arr = tools.Random随机生成器.A_生成一个不重复随机数据(n, min, max);
			int aim = (int) (Math.random() * max);
			Solution3 s3 = new Solution3();
			Solution6动态规划 s6 = new Solution6动态规划();
			int a3 = s3.numOfWays(arr, aim);
			int a6 = s6.numOfWays(arr, aim);
			System.out.printf("%d...%d\r\n", a3, a6);
		}
	}

	static void debugSolution5动态规划() {
		for (int times = 0; times < 100; times ++) {
			int n = times;
			int min = 1;
			int max = n * n + 12;
			int[] arr = tools.Random随机生成器.A_生成一个不重复随机数据(n, min, max);
			int aim = (int) (Math.random() * max);
			Solution3 s3 = new Solution3();
			Solution5动态规划 s5 = new Solution5动态规划();
			int a3 = s3.numOfWays(arr, aim);
			int a5 = s5.numOfWays(arr, aim);
			System.out.printf("%d...%d\r\n", a3, a5);
		}
	}

	static void debugSolution4动态规划() {
		for (int times = 0; times < 100; times ++) {
			int n = times;
			int min = 1;
			int max = n * n + 12;
			int[] arr = tools.Random随机生成器.A_生成一个不重复随机数据(n, min, max);
			int aim = (int) (Math.random() * max);
			Solution3 s3 = new Solution3();
			Solution4动态规划 s4 = new Solution4动态规划();
			int a3 = s3.numOfWays(arr, aim);
			int a4 = s4.numOfWays(arr, aim);
			System.out.printf("%d...%d\r\n", a3, a4);
		}
	}

	static void testSolution123() {
		for (int times = 0; times < 100; times ++) {
			int n = times;
			int min = 1;
			int max = n * n + 12;
			int[] arr = tools.Random随机生成器.A_生成一个不重复随机数据(n, min, max);
			int aim = (int) (Math.random() * max);
			Solution1 s1 = new Solution1();
			Solution2 s2 = new Solution2();
			Solution3 s3 = new Solution3();
			int a1 = s1.numOfWays(arr, aim);
			int a2 = s2.numOfWays(arr, aim);
			int a3 = s3.numOfWays(arr, aim);
			System.out.printf("%d...%d...%d\r\n", a1, a2, a3);
		}
	}

	static void debugSolution2() {
		int[] arr = new int[] {1, 2, 5};
		int aim = 8;
		Solution2 s = new Solution2();
		System.out.println(s.numOfWays(arr, aim));
	}

	static void debugSolution1() {
		int[] arr = new int[] {1, 2, 5};
		int aim = 8;
		Solution1 s = new Solution1();
		System.out.println(s.numOfWays(arr, aim));
	}

	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book057_换钱的方法数.java
	 * @type        Solution1
	 * @date        2016年11月26日 上午11:09:51
	 * @details     最慢的方法，但是这种方法是DP的前提
	 */
	static class Solution1 {
		public int numOfWays(int[] arr, int aim) {
			if (arr == null || arr.length < 1 || aim < 0)	return 0;
			return numOfWays_internal(arr, 0, aim);
		}
		private int numOfWays_internal(int[] arr, int index, int aim) {
			if (index == arr.length)	return aim == 0 ? 1 : 0;
			int res = 0;
			for (int i = 0; i * arr[index] <= aim; i ++) {
				res += numOfWays_internal(arr, index + 1, aim - i * arr[index]);
			}
			return res;
		}
	}
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book057_换钱的方法数.java
	 * @type        Solution2
	 * @date        2016年11月26日 上午11:47:05
	 * @details     是对Solution1的纪录优化
	 * @details     时间复杂度O(N*aim*aim)
	 */
	static class Solution2 {
		public int numOfWays(int[] arr, int aim) {
			if (arr == null || arr.length < 1 || aim < 0)	return 0;
			int[][] map = new int[arr.length + 1][aim + 1];
			for (int i = 0; i < map.length; i ++)
				Arrays.fill(map[i], -1);
			return numOfWays_internal(arr, 0, aim, map);
		}
		private int numOfWays_internal(int[] arr, int index, int aim, int[][] map) {
			int res = 0;
			if (index == arr.length) {
				res = aim == 0 ? 1 : 0;
			} else {
				for (int i = 0; i * arr[index] <= aim; i ++) {
					int mapValue = map[index + 1][aim - i * arr[index]];
					if (mapValue == -1) {
						res += numOfWays_internal(arr, index + 1, aim - i * arr[index], map);
					} else {
						res += mapValue;
					}
				}
			}
			map[index][aim] = res;
			return res;
		}
	}
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book057_换钱的方法数.java
	 * @type        Solution3
	 * @date        2016年11月26日 下午8:23:21
	 * @details     对Solution2的逻辑简化
	 * @details     时间复杂度O(N*aim*aim)
	 */
	static class Solution3 {
		public int numOfWays(int[] arr, int aim) {
			if (arr == null || arr.length < 1 || aim < 0)	return 0;
			int[][] map = new int[arr.length + 1][aim + 1];
			for (int index = 0; index < map.length; index ++)
				Arrays.fill(map[index], -1);
			return numOfWays_internal(arr, 0, aim, map);
		}
		private int numOfWays_internal(int[] arr, int index, int aim, int[][] map) {
			int res = 0;
			if (index == arr.length) {
				res = aim == 0 ? 1 : 0;
			} else {
				for (int i = 0; i * arr[index] <= aim; i ++) {
					int mapValue = map[index + 1][aim - i * arr[index]];
					if (mapValue == -1) {
						res += numOfWays_internal(arr, index + 1, aim - i * arr[index], map);
					} else {
						res += mapValue;
					}
				}
			}
			map[index][aim] = res;
			return res;
		}
	}
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book057_换钱的方法数.java
	 * @type        Solution4动态规划
	 * @date        2016年11月26日 下午8:33:03
	 * @details     时间复杂度O(N*aim*aim)
	 */
	static class Solution4动态规划 {
		public int numOfWays(int[] arr, int aim) {
			if (arr == null || arr.length < 1 || aim < 0)	return 0;
			int[][] dp = new int[arr.length][aim + 1];
			for (int i = 0; i < arr.length; i ++)	dp[i][0] = 1;
			for (int j = 1; j * arr[0] <= aim; j ++)	dp[0][j * arr[0]] = 1;
			int num = 0;
			for (int i = 1; i < arr.length; i ++) {
				for (int j = 1; j <= aim; j ++) {
					num = 0;
					for (int k = 0; j - arr[i] * k >= 0; k ++)
						num += dp[i - 1][j - arr[i] * k];
					dp[i][j] = num;
				}
			}
			return dp[arr.length - 1][aim];
		}
	}
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book057_换钱的方法数.java
	 * @type        Solution5动态规划
	 * @date        2016年11月26日 下午8:34:20
	 * @details     时间复杂度O(N*aim)
	 */
	static class Solution5动态规划 {
		public int numOfWays(int[] arr, int aim) {
			if (arr == null || arr.length < 1 || aim < 0)	return 0;
			int[][] dp = new int[arr.length][aim + 1];
			for (int i = 0; i < arr.length; i ++)	dp[i][0] = 1;
			for (int j = 1; j * arr[0] <= aim; j ++)	dp[0][j * arr[0]] = 1;
			for (int i = 1; i < arr.length; i ++)
				for (int j = 1; j <= aim; j ++) {
					dp[i][j] = dp[i - 1][j];
					dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
				}
			return dp[arr.length - 1][aim];
		}
	}
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book057_换钱的方法数.java
	 * @type        Solution6动态规划
	 * @date        2016年11月26日 下午8:41:27
	 * @details     对Solution5动态规划的空间优化
	 * @details     目前的最优解，时间O(N*aim) 空间O(aim)
	 */
	static class Solution6动态规划 {
		public int numOfWays(int[] arr, int aim) {
			if (arr == null || arr.length < 1 || aim < 0)	return 0;
			int[] dp = new int[aim + 1];
			for (int j = 0; j * arr[0] <= aim; j ++)	dp[j * arr[0]] = 1;
			for (int i = 1; i < arr.length; i ++)
				for (int j = 1; j <= aim; j ++) {
					dp[j] += j - arr[i] >= 0 ? dp[j - arr[i]] : 0;
				}
			return dp[aim];
		}
	}
	
}
