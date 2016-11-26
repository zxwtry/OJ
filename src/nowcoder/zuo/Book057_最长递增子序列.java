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
		testMyAndBook1();
	}
	
	static void testMyAndBook1() {
		boolean isAllTrue = true;
		for (int times = 0; times < 100; times ++) {
			int n = times;
			int min = 1;
			int max = n * n  + 12;
			int[] arr = tools.Random随机生成器.A_生成一个不重复随机数据(n, min, max);
			arr = new int[] {1};
			MySolution s1 = new MySolution();
			BookSolution1 s2 = new BookSolution1();
			int a1 = s1.maxSequence(arr);
			int a2 = s2.maxSequence(arr);
			isAllTrue &= a1 == a2;
			if (a1 != a2) {
				StringBuilder st = new StringBuilder();
				for (int v : arr)
					st.append(v + " ");
				tools.FileUtils.B_纪录String_append("D:/file/temp/20161126_Book057_最长递增子序列.txt", st.toString());
			}
			System.out.printf("%d...%d\r\n", a1, a2);
		}
		System.out.println(isAllTrue);
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
			if (arr == null || arr.length < 1)	return 0;
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
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book057_最长递增子序列.java
	 * @type        BookSolution1
	 * @date        2016年11月26日 下午9:28:37
	 * @details     书上的第一种O(N^2)方法
	 */
	static class BookSolution1 {
		public int maxSequence(int[] arr) {
			int[] dp = new int[arr.length];
			int max = 0;
			for (int i = 0; i < arr.length; i ++) {
				dp[i] = 1;
				for (int j = 0; j < i; j ++) {
					if (arr[i] > arr[j])
						dp[i] = Math.max(dp[i] , dp[j] + 1);
				}
				max = Math.max(max, dp[i]);
			}
			return max;
		}
	}
	
}
