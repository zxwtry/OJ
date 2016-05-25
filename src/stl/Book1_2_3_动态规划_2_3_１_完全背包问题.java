package stl;

import java.util.Arrays;

public class Book1_2_3_动态规划_2_3_１_完全背包问题 {
	public static void main(String[] args) {
		ws = new int[]{2,3,2,4,8,6};
		vs = new int[]{3,7,6,2,4,6};
		W = 13;
		
		// #使用三个循环完成O(NWW)
//		System.out.println(solve());
		
		// #使用两个循环完成O(NW)
		System.out.println(solve_ONW());
	}
	static int N, W, ws[], vs[], dp[][];
	static int solve_ONW() {
		N = ws.length;
		dp = new int[N+1][W+1];
		Arrays.fill(dp[0], 0);
		for (int ni = 1; ni < N; ni ++)
			dp[ni][0] = 0;
		for (int ni = 0; ni < N; ni ++)
			for (int wn = 0; wn <= W; wn ++)
				if (wn < ws[ni])
					dp[ni+1][wn] = dp[ni][wn];
				else
					dp[ni+1][wn] = Math.max(dp[ni][wn],(wn>=ws[ni]
							? dp[ni+1][wn-ws[ni]]+vs[ni] : 0));
		return dp[N][W];
	}
	static int solve() {
		N = ws.length;
		dp = new int[N+1][W+1];
		for (int ni = 0; ni < N; ni ++)
			for (int wi = 0; wi <= W; wi ++)
				for (int k = 0; k*ws[ni] <= wi; k ++) {
					int dpn = dp[ni][wi-k*ws[ni]] + k*vs[ni];
					if (dp[ni+1][wi] < dpn)
						dp[ni+1][wi] = dpn;
				}
		return dp[N][W];
	}
}