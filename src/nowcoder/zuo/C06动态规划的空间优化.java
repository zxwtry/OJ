package nowcoder.zuo;

import java.util.Arrays;

/*
 * 	题目：
 * 		给定一个矩阵m，从左上角开始每次只能向右或者向下走，
 * 		最后到达右下角的位置。路径上所有的数字累加起来就是路径和。
 * 		求最小路径和。
 * 	实例：
 * 		给定m矩阵如下：
 * 			1 3 5 9
 * 			8 1 3 4
 * 			5 0 6 1
 * 			8 8 4 0
 * 		那么路径1 3 1 0 6 1 0就是所有路径中路径和最小的，故返回12
 * 	要求：
 * 		如果举证大小是M*N，时间复杂度是O(M*N)，空间复杂度O(min{M,N})
 * 
 */

public class C06动态规划的空间优化 {
	public static void main(String[] args) {
		int[][] mat={
				{1, 3, 5, 9},
				{8, 1, 3, 4},
				{5, 0, 6, 1},
				{8, 8, 4, 0}
		};
		System.out.println(dpMinSpace(mat));
	}
	static int dpMinSpace(int[][] mat) {
		if (mat == null || mat.length == 0 || mat[0].length == 0)
			return 0;
		int M = mat.length, N = mat[0].length;
		// 现在假定 M < N
		int[] arr = new int[M];
		Arrays.fill(arr, Integer.MAX_VALUE);
		arr[0] = mat[0][0];
		for (int mIn = 1; mIn < M; mIn ++)
			arr[mIn] = mat[mIn][0] + arr[mIn-1];
		for (int nIn = 1; nIn < N; nIn ++) {
			arr[0] += mat[0][nIn];
			for (int mIn = 1; mIn < M; mIn ++)
				arr[mIn] = Math.min(arr[mIn-1], arr[mIn])+mat[mIn][nIn];
		}
		return arr[M-1];
	}
}
