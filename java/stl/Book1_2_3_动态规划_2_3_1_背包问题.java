package stl;

import java.util.Arrays;

/*
 * 	
 */

public class Book1_2_3_动态规划_2_3_1_背包问题 {
	static int W, ws[], vs[], N;
	public static void main(String[] args) {
		ws = new int[]{2,1,3,2};
		vs = new int[]{3,2,4,2};
		W = 5;
		N = ws.length;
		
		// #solve_search
//		System.out.println(solve_search(0, W));
		
		// #solve_NM
//		dp_NM  = new int[N+1][W+1];
//		for (int ni = dp_NM.length-1; ni >= 0; ni --)
//			Arrays.fill(dp_NM[ni], -1);
//		System.out.println(solve_NM(0, W));
		
		// #solve_NM_2for
//		System.out.println(solve_NM_2for());
		
		// #solve_NM_0
//		System.out.println(solve_NM_0());
		
	}
	static int solve_NM_0() {
		dp_NM = new int[N+1][W+1];
		Arrays.fill(dp_NM[0], 0);
		for (int ni = 0; ni < N; ni ++)
			for (int wn = 0; wn <= W; wn ++)
				if (wn < ws[ni])
					dp_NM[ni+1][wn] = dp_NM[ni][wn];
				else
					dp_NM[ni+1][wn] = Math.max(dp_NM[ni][wn],
							(wn-ws[ni] >= 0 ? dp_NM[ni][wn-ws[ni]] + vs[ni] : 0));
		return dp_NM[N][W];
	}
	static int solve_NM_2for() {
		dp_NM = new int[N+1][W+1];
		Arrays.fill(dp_NM[N], 0);
		for (int ni = N-1; ni >= 0; ni --)
			for (int wn = 0; wn <= W; wn ++)
				if (wn < ws[ni])
					dp_NM[ni][wn] = dp_NM[ni+1][wn];
				else
					dp_NM[ni][wn] = Math.max(dp_NM[ni+1][wn], dp_NM[ni+1][wn-ws[ni]] + vs[ni]);
		return dp_NM[0][W];
	}
	static int[][] dp_NM;
	static int solve_NM(int ni, int wn) {
		if (dp_NM[ni][wn] >= 0)
			return dp_NM[ni][wn];
		int res;
		if (ni >= N)
			res = 0;
		else if (wn < ws[ni])
			res = solve_NM(ni+1, wn);
		else
			res = Math.max(solve_NM(ni+1, wn), 
					solve_NM(ni+1, wn-ws[ni])+vs[ni]);
		return dp_NM[ni][wn] = res;
	}
	static int solve_search(int ni, int wn) {
		int res;
		if (ni == N)
			res = 0;
		else if (wn < ws[ni])
			res = solve_search(ni+1, wn);
		else {
			res = Math.max(solve_search(ni+1, wn), 
					solve_search(ni+1, wn-ws[ni])+vs[ni]);
		}
		return res;
	}
}
