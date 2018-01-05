package nowcoder.zuo;

import java.util.LinkedList;

public class Book010_最大值减去最小值小于或等于num的子数组数量 {
	public static void main(String[] args) {
		test();
	}
	private static void test() {
		int len = 1000;
		int min = 0;
		int max = len * len;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(len, min, max);
		int num = len + 2;
		int standardAnswer = getStandardAnswer(arr, num);
		Solution s = new Solution();
		int solutionAnswer = s.getNum(arr, num);
		
		System.out.println(standardAnswer +"..."+ solutionAnswer);
	}
	private static int getStandardAnswer(int[] arr, int num) {
		int standardAnswer = 0;
		for (int i = 0; i < arr.length; i ++) {
			int min = arr[i];
			int max = arr[i];
			for (int j = i; j < arr.length; j ++) {
				max = Math.max(arr[j], max);
				min = Math.min(arr[j], min);
				if (max - min > num) {
					break;
				} else {
					standardAnswer ++;
				}
			}
		}
		return standardAnswer;
	}
	static class Solution {
		public int getNum(int[] arr, int num) {
			if (arr == null || arr.length == 0) {
				return 0;
			}
			LinkedList<Integer> qmin = new LinkedList<>();
			LinkedList<Integer> qmax = new LinkedList<>();
			int i = 0;
			int j = 0;
			int res = 0;
			while (i < arr.length) {
				while (j < arr.length) {
					while (! qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j]) {
						qmin.pollLast();
					}
					qmin.addLast(j);
					while (! qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j]) {
						qmax.pollLast();
					}
					qmax.addLast(j);
					if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
						break;
					}
					j ++;
				}
				if (qmin.peekFirst() == i) {
					qmin.pollFirst();
				}
				if (qmax.peekFirst() == i) {
					qmax.pollFirst();
				}
				res += j - i;
				i ++;
			}
			return res;
		}
	}
}
