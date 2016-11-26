package nowcoder.zuo;

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
	}
	
	static void debugSolution1() {
		int[] arr = new int[] {1, 2, 5};
		int aim = 8;
		Solution1 s = new Solution1();
		System.out.println(s.numOfWays(arr, aim));
	}

	static class Solution1 {
		public int numOfWays(int[] arr, int aim) {
			if (arr == null || arr.length < 1 || aim < 0)	return 0;
			if (aim == 0)	return 1;
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
}
