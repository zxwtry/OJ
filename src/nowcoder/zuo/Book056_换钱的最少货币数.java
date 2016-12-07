package nowcoder.zuo;

import java.util.ArrayList;
/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book056_换钱的最少货币数.java
 * @type        Book056_换钱的最少货币数
 * @date        2016年11月25日 上午11:28:57
 * @date        11 30 13 12 33 17 #29
 */
public class Book056_换钱的最少货币数 {
	public static void main(String[] args) {
	}
	
	static void testSolution2补充问题和Solution3补充问题() {
		for (int times = 1; times < 999; times ++) {
			int n = (int) ( Math.random() * times );
			int min = 1;
			int max = n * n;
			int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
			int aim = (int) ( Math.random() * max );
			Solution2补充问题 s2 = new Solution2补充问题();
			Solution3补充问题 s3 = new Solution3补充问题();
 			int n2 = s2.getMinNum(arr, aim);
			int n3 = s3.getMinNum(arr, aim);
			System.out.println(n2 + "..." + n3);
			if (n2 != n3) {
				StringBuilder st = new StringBuilder();
				for (int val : arr)
					st.append(val + " ");
				tools.FileUtils.B_纪录String_append("D:/file/temp/20161126testSolution2补充问题和Solution3补充问题.txt", st.toString() + "#" + aim);
			}
		}
	}

	static void testSolution2补充问题和Solution4补充问题() {
		for (int times = 1; times < 999; times ++) {
			int n = (int) ( Math.random() * times );
			int min = 1;
			int max = n * n;
			int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
			int aim = (int) ( Math.random() * max );
			Solution2补充问题 s2 = new Solution2补充问题();
			Solution4补充问题 s4 = new Solution4补充问题();
			int n2 = s2.getMinNum(arr, aim);
			int n4 = s4.getMinNum(arr, aim);
			System.out.println(n2 + "..." + n4);
			if (n2 != n4) {
				StringBuilder st = new StringBuilder();
				for (int val : arr)
					st.append(val + " ");
				tools.FileUtils.B_纪录String_append("D:/file/temp/20161126testSolution2补充问题和Solution4补充问题.txt", st.toString() + "#" + aim);
			}
		}
	}

	/**
	 * @method      debugSolution1补充问题
	 * @parameter   
	 * @return      void
	 * @details     还是有问题
	 */
	static void debugSolution1补充问题() {
		for (int times = 1; times < 99; times ++) {
			int n = (int)(Math.random() * 15);
			int min = 1;
			int max = n * n;
			int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
			int aim = (int) ( Math.random() * max );
			StandardJudge s1 = new StandardJudge();
			Solution2补充问题 s3 = new Solution2补充问题();
			int a1 = s1.minNum(arr, aim);
			int a3 = s3.getMinNum(arr, aim);
			System.out.println(a1 + "..." + a3);
			if (a1 != a3) {
				StringBuilder st = new StringBuilder();
				for (int val : arr)
					st.append(val +  " ");
				tools.FileUtils.B_纪录String_append("D:/file/temp/161126debugSolution1补充问题.txt", st.toString() + " #### " + aim);
			}
		}
	}

	static void testSolution1AndSolution2() {
		for (int times = 1; times < 99; times ++) {
			int n = 100;
			int min = 1;
			int max = n * n * n;
			int[] arr = tools.Random随机生成器.A_生成一个不重复随机数据(n, min, max);
			int aim = (int) ( Math.random() * max );
			Solution1 s1 = new Solution1();
			Solution2 s2 = new Solution2();
			System.out.println(s1.minNum(arr, aim) == s2.getMinNum(arr, aim));
		}
	}

	static void debugStandardJudgeRepeated() {
		StandardJudgeRepeated s = new StandardJudgeRepeated();
		int[] arr = new int[] {5, 4, 3};
		int aim = 14;
		System.out.println(s.minNum(arr, aim));
	}

	static void debugStandardJudge() {
		StandardJudge s = new StandardJudge();
		int[] arr = new int[] {5, 2, 3};
		int aim = 7;
		System.out.println(s.minNum(arr, aim));
	}

	static void debugSolution() {
		Solution1 s = new Solution1();
		int[] arr = new int[] {5, 2, 3};
		int aim = 20;
		System.out.println(s.minNum(arr, aim));
	}

	/**
	 * @author      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book056_换钱的最少货币数.java
	 * @type        Solution
	 * @date        2016年11月25日 下午4:06:20
	 * @details     给定数组arr，arr中所有的值都为正数且不重复
	 * @details     每个值代表一种面值的货币
	 * @details     每种面值的货币可以使用任意张
	 * @details     给定一个正数aim，代表要找的钱数
	 * @details     求组成aim的最少货币币数
	 * @details     arr=[5,2,3]  aim=20		---  4
	 * @details     arr=[5,2,3]  aim=0		---  0
	 * @details     arr=[5,3]    aim=20		--- -1
	 */
	static class Solution1 {
		public int minNum(int[] arr, int aim) {
			if (arr == null || arr.length < 1 || aim < 0)	return -1;
			int[][] dp = new int[arr.length][aim + 1];
			for (int j = 1; j <= aim; j ++) {
				dp[0][j] =Integer.MAX_VALUE;
				if (j - arr[0] >= 0 && dp[0][j - arr[0]] !=Integer.MAX_VALUE)
					dp[0][j] = dp[0][j - arr[0]] + 1;
			}
			int left = 0;
			for (int i = 1; i < arr.length; i ++) {
				for (int j = 1; j <= aim; j ++) {
					left =Integer.MAX_VALUE;
					if (j - arr[i] >= 0 && dp[i][j - arr[i]] !=Integer.MAX_VALUE) 
						left = dp[i][j - arr[i]] + 1;
					dp[i][j] = Math.min(left, dp[i - 1][j]);
				}
			}
			return dp[arr.length - 1][aim] != Integer.MAX_VALUE ? dp[arr.length - 1][aim] : -1;
		}
	}
	
	static class StandardJudgeRepeated {
		public int minNum(int[] arr, int aim) {
			if (aim < 1)	return 0;
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int val : arr) {
				if (val != 0) {
					int times = (aim / val) + 1;
					for (int i = 0; i < times ; i ++)
						list.add(val);
				}
			}
			int[] arr_new = new int[list.size()];
			for (int i = 0; i < arr_new.length; i ++) {
				arr_new[i] = list.get(i);
			}
			boolean[] isFound = new boolean[]{false};
			for (int k = 1; k <= arr_new.length; k ++) {
				isFound[0] = false;
				allPerm(k, 0, arr_new, aim, new int[] {0}, isFound);
				if (isFound[0])
					return k;
			}
			return -1;
		}
		private void allPerm(int k, int index, int[] arr, int aim, int[] arrIndex, boolean[] isFound) {
			if (isFound[0]) {
				return;
			}
			if (index == arr.length) {
				int sum = 0;
				for (int i = 0; i < k; i ++)
					sum += arr[i];
				if (sum == aim)
					isFound[0] = true;
				return;
			}
			for (int newIndex = index; newIndex < arr.length; newIndex ++) {
				swap(arr, index, newIndex);
				allPerm(k, index + 1, arr, aim, arrIndex, isFound);
				swap(arr, index, newIndex);
			}
		}
		private void swap(int[] arr, int index, int newIndex) {
			int tmp = arr[index];
			arr[index] = arr[newIndex];
			arr[newIndex] = tmp;
		}
	}
	
	static class StandardJudge {
		public int minNum(int[] arr, int aim) {
			if (aim < 1)	return 0;
			boolean[] isFound = new boolean[]{false};
			for (int k = 1; k <= arr.length; k ++) {
				isFound[0] = false;
				allPerm(k, 0, arr, aim, new int[] {0}, isFound);
				if (isFound[0])
					return k;
			}
			return -1;
		}
		private void allPerm(int k, int index, int[] arr, int aim, int[] arrIndex, boolean[] isFound) {
			if (isFound[0]) {
				return;
			}
			if (index == arr.length) {
				int sum = 0;
				for (int i = 0; i < k; i ++)
					sum += arr[i];
				if (sum == aim)
					isFound[0] = true;
				return;
			}
			for (int newIndex = index; newIndex < arr.length; newIndex ++) {
				swap(arr, index, newIndex);
				allPerm(k, index + 1, arr, aim, arrIndex, isFound);
				swap(arr, index, newIndex);
			}
		}
		private void swap(int[] arr, int index, int newIndex) {
			int tmp = arr[index];
			arr[index] = arr[newIndex];
			arr[newIndex] = tmp;
		}
	}
	
	/**
	 * @author      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book056_换钱的最少货币数.java
	 * @type        Solution2
	 * @date        2016年11月25日 下午8:03:38
	 * @details     和Solution一样，只是空间少了点
	 */
	static class Solution2 {
		public int getMinNum(int[] arr, int aim) {
			if (arr == null || arr.length < 1 || aim < 1)	return 0;
			int[] dp = new int[aim + 1];
			for (int j = 1; j <= aim; j ++) {
				dp[j] = Integer.MAX_VALUE;
				if (j - arr[0] >= 0 && dp[j - arr[0]] != Integer.MAX_VALUE)
					dp[j] = dp[j - arr[0]] + 1;
			}
			for (int i = 1; i < arr.length; i ++) {
				for (int j = 1; j <= aim; j ++) {
					int left = Integer.MAX_VALUE;
					if (j - arr[i] >= 0 && dp[j - arr[i]] != Integer.MAX_VALUE)
						left = dp[j - arr[i]] + 1;
					dp[j] = Math.min(left, dp[j]);
				}
			}
			return dp[aim] == Integer.MAX_VALUE ? -1 : dp[aim];
		}
	}
	
	/**
	 * @author      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book056_换钱的最少货币数.java
	 * @type        Solution2补充问题
	 * @date        2016年11月25日 下午8:42:13
	 * @details     给定数组arr，arr中所有的值都为正数。
	 * @details     每一个值仅代表一张钱的面值，再给定一个整数aim
	 * @details     代表要找的钱数，求组成aim的最少货币数
	 */
	static class Solution2补充问题 {
		public int getMinNum (int[] arr, int aim) {
			if (arr == null || arr.length < 1 || aim < 1)	return 0;
			int[][] dp = new int[arr.length][aim + 1];
			for (int j = 1; j <= aim; j ++)
				dp[0][j] = j == arr[0] ? 1 : Integer.MAX_VALUE;
			int leftUp = 0;
			for (int i = 1; i < arr.length; i ++) {
				if (arr[i] <= aim)
					dp[i][arr[i]] = 1;
				for (int j = 1; j <= aim; j ++) {
					leftUp = Integer.MAX_VALUE;
					if (j - arr[i] >= 0 && dp[i - 1][j - arr[i]] != Integer.MAX_VALUE)
						leftUp = dp[i - 1][j - arr[i]] + 1;
					dp[i][j] = Math.min(leftUp, dp[i - 1][j]);
				}
			}
			return dp[arr.length - 1][aim] == Integer.MAX_VALUE ? -1 : dp[arr.length - 1][aim];
		}
	}
	
	/**
	 * @author      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book056_换钱的最少货币数.java
	 * @type        Solution3补充问题
	 * @date        2016年11月25日 下午8:52:57
	 * @details     书中代码是这样的，但是好像代码有问题。
	 * @details     在这个情形中，空间好像不能压缩
	 * @details     int[] arr = new int[] {22,17,31,14,6,30};
	 * @details     int aim = 12;
	 * @details     Solution3补充问题 得出的结果是2
	 * @details     而正确的结果应该是-1
	 */
	static class Solution3补充问题 {
		public int getMinNum (int[] arr, int aim) {
			if (arr == null || arr.length < 1 || aim < 1)	return 0;
			int[] dp = new int[aim + 1];
			for (int j = 1; j <= aim; j ++)
				dp[j] = j == arr[0] ? 1 : Integer.MAX_VALUE;
			int leftUp = 0;
			for (int i = 1; i < arr.length; i ++) {
				for (int j = 1; j <= aim; j ++) {
					leftUp = Integer.MAX_VALUE;
					if (j - arr[i] >= 0 && dp[j - arr[i]] != Integer.MAX_VALUE)
						leftUp = dp[j - arr[i]] + 1;
					dp[j] = Math.min(leftUp, dp[j]);
				}
			}
			return dp[aim] == Integer.MAX_VALUE ? -1 : dp[aim];
		}
	}
	
	/**
	 * @author      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book056_换钱的最少货币数.java
	 * @type        Solution4补充问题
	 * @date        2016年11月26日 上午10:26:43
	 * @details     Solution3补充问题 有问题
	 * @details     对Solution3补充问题的改进
	 */
	static class Solution4补充问题 {
		public int getMinNum (int[] arr, int aim) {
			if (arr == null || arr.length < 1 || aim < 1)	return 0;
			int[] dp = new int[aim + 1];
			int[] dp_save = new int[aim + 1];
			for (int j = 1; j <= aim; j ++)
				dp[j] = j == arr[0] ? 1 : Integer.MAX_VALUE;
			int leftUp = 0;
			for (int i = 1; i < arr.length; i ++) {
				System.arraycopy(dp, 0, dp_save, 0, dp.length);
				for (int j = 1; j <= aim; j ++) {
					leftUp = Integer.MAX_VALUE;
					if (j - arr[i] >= 0 && dp_save[j - arr[i]] != Integer.MAX_VALUE)
						leftUp = dp_save[j - arr[i]] + 1;
					dp[j] = Math.min(leftUp, dp[j]);
				}
			}
			return dp[aim] == Integer.MAX_VALUE ? -1 : dp[aim];
		}
	}
	
}
