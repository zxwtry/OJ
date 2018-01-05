package stl;

import java.util.Arrays;

/*
 * 	给定值，给定个数，求和为特定值
 */

public class Book1_2_3_动态规划_2_3_2_多重部分和问题 {
	static int[] a = {3, 5, 8};		// 值
	static int[] m = {3, 2, 2};		// 个数
	static int N = a.length;		// 数组长度
	static int K = 17;				// 目标值
	public static void main(String[] args) {
		System.out.println(dpBoolean());
		System.out.println(dpINT());
	}
	// 第一种DP  时间复杂度是 O(K*sigma(m))
	// DP求取boolean结果一般效率不高
	static boolean[][] dpb = new boolean[N+1][K+1];
	static boolean dpBoolean() {
		dpb[0][0] = true;
		for (int i = 0; i < N; i ++)
			for (int j = 0; j <= K; j ++)
				for (int k = 0; k <= m[i] && k*a[i] <= j; k ++)
					dpb[i+1][j] |= dpb[i][j-k*a[i]];
		return dpb[N][K];
	}
	// 第二种DP	时间复杂度O(N*K)　空间复杂度O(N*K)
	// 在DP中常见的一个问题：空间能否优化
	static int [][] dpi = new int[N+1][K+1];
	static boolean dpInt() {
		for (int ini = 0; ini < dpi.length; ini ++)
			Arrays.fill(dpi[ini], -1);
		for (int ini = 0; ini < N; ini ++)
			for (int inj = 0; inj < K; inj ++)
				if (dpi[ini][inj] >= 0)
					System.out.println();
		return dpi[N][K] >= 0;
	}
	// 第三种DP　时间复杂度O(N*K) 空间复杂度O(K)
	// 
	static int[] dpI = new 	int[K+1];
	static boolean dpINT() {
		Arrays.fill(dpI, -1);
		dpI[0] = 0;
		for (int inn = 0; inn < N; inn ++)
			for (int ink = 0; ink <= K; ink ++)
				if (dpI[ink] >= 0)
					dpI[ink] = m[inn];
				else if (ink < a[inn] || dpI[ink-a[inn]] <= 0)
					dpI[ink] = -1;
				else
					dpI[ink] = dpI[ink-a[inn]] - 1;
		return dpI[K] >= 0;
	}
}