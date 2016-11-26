package nowcoder.zuo;

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book057_最长递增子序列.java
 * @type        Book057_最长递增子序列
 * @date        2016年11月26日 下午8:56:55
 * @details     
 */
public class Book057_最长递增子序列 {
	public static void main(String[] args) {
		debugMySolution();
	}
	
	static void debugMySolution() {
		int[] arr = new int[] {2, 1, 5, 3, 6, 4, 8, 9, 7};
//		arr = new int[] {1, 2, 3, 4};
//		arr = new int[] {4, 3, 2, 1};
		System.out.println(new MySolution().maxSequence(arr));
	}

	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book057_最长递增子序列.java
	 * @type        MySolution
	 * @date        2016年11月26日 下午9:23:02
	 * @details     时间复杂度：O(N^2)
	 */
	static class MySolution {
		public int maxSequence(int[] arr) {
			int[] dp = new int[arr.length];
			dp[0] = 1;
			int max = 0;
			for (int index = 1; index < arr.length; index ++) {
				int preIndex = index;
				while (preIndex > 0 && arr[preIndex - 1] > arr[index]) {
					preIndex --;
				}
				dp[index] = preIndex == 0 ? 1 : dp[preIndex - 1] + 1;
				max = Math.max(max, dp[index]);
			}
			return max;
		}
	}
	
}
