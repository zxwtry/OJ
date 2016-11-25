package nowcoder.zuo;

import java.util.ArrayList;

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book056_换钱的最少货币数.java
 * @type        Book056_换钱的最少货币数
 * @date        2016年11月25日 上午11:28:57
 */
public class Book056_换钱的最少货币数 {
	public static void main(String[] args) {
		debugStandardJudgeRepeated();
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
		Solution s = new Solution();
		int[] arr = new int[] {5, 2, 3};
		int aim = 20;
		System.out.println(s.minNum(arr, aim));
	}

	/**
	 * @auther      zxwtry
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
	static class Solution {
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
	
}
