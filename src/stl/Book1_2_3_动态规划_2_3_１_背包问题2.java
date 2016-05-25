package stl;

import java.util.Arrays;

/*
 * 	限制条件：
 * 		1 <=  n  <= 100
 * 		1 <=  wi <= 10E7
 * 		1 <=  vi <= 100
 * 		1 <=  W  <= 10E9
 * 	说明：
 * 		之前方法的时间复杂度: O(NW)
 * 		现在不行。
 * 	定义：
 * 		dp[i+1][j]: 前i个物品中挑选出价值总和为j时
 * 					总重量的最小值。(不存在：INF)
 * 	初始化：
 * 		dp[0][0] = 0
 * 		dp[0][j] = INF
 * 	递归关系：
 * 		1,	前 i-1 个物品中挑选价值总和为 j 的部分
 * 		2,	前 i-1 个物品中挑选价值总和为 j-v[i] 的部分，
 * 			然后再选中第 i 个物品
 * 	递归方程:
 * 		dp[i+1][j] = min(dp[i][j], dp[i][j-v[i]]+w[i])
 * 	最终答案:
 * 		dp[n][j] <= W 的最大 j
 */


public class Book1_2_3_动态规划_2_3_１_背包问题2 {
	final static int INF = Integer.MAX_VALUE >> 2;
	public static void main(String[] args) {
		ws = new int[]{2,1,3,2};
		vs = new int[]{3,2,4,2};
		W = 5;
		N = ws.length;
		MAX_V = Integer.MIN_VALUE;
		for (int vi = 0; vi < N; vi ++)
			if (MAX_V < vs[vi])
				MAX_V = vs[vi];
		System.out.println(solve());
	}
	static int N, W, vs[], ws[], dp[][], MAX_V;
	static int solve() {
		dp = new int[N+1][N*MAX_V+1];
		Arrays.fill(dp[0], INF);
		dp[0][0] = 0;
		for (int ni = 0; ni < N; ni ++)
			for (int wi = 0; wi < dp[0].length; wi ++) {
				if (wi < vs[ni])
					dp[ni+1][wi] = dp[ni][wi];
				else
					dp[ni+1][wi] = Math.min(dp[ni][wi], dp[ni][wi-vs[ni]]+ws[ni]);
			}
		int ans = Integer.MIN_VALUE;
		for (int wi = 0; wi < dp[0].length; wi ++)
			if (dp[N][wi] <= W)
				ans = wi;
		return ans;
	}
}