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
		testSolution123();
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
	 * @details     
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
	
}
