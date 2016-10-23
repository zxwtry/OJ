package nowcoder.zuo;

import java.util.LinkedList;

public class Book007_生成窗口最大值数组 {
	public static void main(String[] args) {
//		debugSolution();
		test();
	}
	static void test() {
		int len = 100;
		int max = 1000;
		int min = 0;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(len, min, max);
		int w = 0;
		boolean isAllTrue = true;
		for (; w <= arr.length; w ++) {
			int[] standardAns = standardAnswer(arr, w);
			Solution s = new Solution();
			int[] solutionAns = s.getMaxWindow(arr, w);
			boolean isTrue = true;
			for (int i = 0; i < solutionAns.length; i ++) {
				isTrue &= standardAns[i] == solutionAns[i];
			}
//			System.out.println(isTrue);
			isAllTrue &= isTrue;
		}
		System.out.println(isAllTrue);
	}
	private static int[] standardAnswer(int[] arr, int w) {
		int[] ans = new int[arr.length - w + 1];
		for (int i = 0; i < ans.length; i ++) {
			int max = Integer.MIN_VALUE;
			for (int j = 0; j < w; j ++) {
				max = Math.max(max, arr[i + j]);
			}
			ans[i] = max;
		}
		return ans;
	}
	static void debugSolution() {
		int[] arr = new int[]{4, 3, 5, 4, 3, 3, 6, 7};
		int w = 3;
		Solution s = new Solution();
		int[] ans = s.getMaxWindow(arr, w);
		tools.Utils.printArray(ans, 100);
	}
	static class Solution {
		public int[] getMaxWindow(int[] arr, int w) {
			if (arr == null || w < 1 || arr.length < w) {
				return new int[]{};
			}
			LinkedList<Integer> qmax = new LinkedList<>();
			int[] res = new int[arr.length - w + 1];
			int index = 0;
			for (int i = 0; i < arr.length; i ++) {
				while ( ! qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
					qmax.pollLast();
				}
				qmax.addLast(i);
				if (qmax.peekFirst() == i - w) {
					qmax.pollFirst();
				}
				if (i >= w - 1) {
					res[index ++] = arr[qmax.peekFirst()];
				}
			}
			return res;
		}
	}
}
