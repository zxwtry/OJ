package nowcoder.zuo;

/**
 * @author      zxwtry
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
		
	}
	
	static void testMyAndBook1() {
		boolean isAllTrue = true;
		for (int times = 0; times < 100; times ++) {
			int n = times;
			int min = 1;
			int max = n * n  + 12;
			int[] arr = tools.Random随机生成器.A_生成一个不重复随机数据(n, min, max);
			MySolution s1 = new MySolution();
			BookSolution1 s2 = new BookSolution1();
			int a1 = s1.maxSequence(arr);
			int a2 = s2.maxSequence(arr).length;
			isAllTrue &= a1 == a2;
			if (a1 != a2) {
				StringBuilder st = new StringBuilder();
				for (int v : arr)
					st.append(v + " ");
				tools.FileUtils.B_纪录String_append("D:/file/temp/20161126_Book057_最长递增子序列.txt", st.toString() + "MySolution: " + a1 + "   BookSolution: " + a2);
			}
			System.out.printf("%d...%d\r\n", a1, a2);
		}
		System.out.println(isAllTrue);
	}

	static void debugMySolution() {
		int[] arr = new int[] {2, 1, 5, 3, 6, 4, 8, 9, 7};
//		arr = new int[] {1, 2, 3, 4};
//		arr = new int[] {4, 3, 2, 1};
		arr = new int[] {60,53,3,27,5,2,8,75};
		MySolution s = new MySolution();
		int ans = s.maxSequence(arr);
		System.out.println(ans);
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
			int max = 1;
			for (int index = 1; index < arr.length; index ++) {
				int preMax = 0;
				for (int preIndex = 0; preIndex < index; preIndex ++) {
					if (arr[preIndex] < arr[index]) {
						preMax = Math.max(preMax, dp[preIndex]);
					}
				}
				dp[index] = preMax + 1;
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
		/**
		 * @method      getDP
		 * @parameter   record[0]：保存dp数组的最大值
		 * @parameter   record[1]：dp数组最大值的对应dpIndex
		 * @return      int[]
		 * @details     
		 */
		private int[] getDP(int[] arr, int[] record) {
			int[] dp = new int[arr.length];
			for (int i = 0; i < arr.length; i ++) {
				dp[i] = 1;
				for (int j = 0; j < i; j ++)
					if (arr[i] > arr[j])
						dp[i] = Math.max(dp[i] , dp[j] + 1);
				if (dp[i] > record[0]) {
					record[0] = dp[i];
					record[1] = i;
				}
			}
			return dp;
		}
		private int[] generateLIS(int[] arr, int[] dp, int[] record) {
			int lisIndex = record[0];
			int dpMaxIndex = record[1];
			int[] lis = new int[lisIndex];
			lis[-- lisIndex] = arr[record[1]];
			for (int i = record[1]; i > -1; i --) {
				if (arr[i] < arr[dpMaxIndex] && dp[i] == dp[dpMaxIndex] - 1) {
					lis[-- lisIndex] = arr[i];
					dpMaxIndex = i;
				}
			}
			return lis;
		}
		public int[] maxSequence(int[] arr) {
			if (arr == null || arr.length == 0)		return new int[0];
			int[] record = new int[2];
			int[] dp = getDP(arr, record);
			return generateLIS(arr, dp, record);
		}
	}
}
