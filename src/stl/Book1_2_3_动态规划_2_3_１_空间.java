package stl;

public class Book1_2_3_动态规划_2_3_１_空间 {
	public static void main(String[] args) {
		ws = new int[]{2,3,2,4,8,6};
		vs = new int[]{3,7,6,2,4,6};
		W = 13;
		// #0-1
//		System.out.println(solve_01());
		
		// #完全背包
		System.out.println(solve_cmp());
	}
	static int N, W, ws[], vs[], dp[];
	static int solve_cmp() {
		N = ws.length;
		dp = new int[W+1];
		for (int ni = 0; ni < N; ni ++)
			for (int wn = ws[ni]; wn <= W; wn ++) {
				int dpn = dp[wn - ws[ni]] + vs[ni];
				if (dpn > dp[wn])
					dp[wn] = dpn;
			}
		return dp[W];
	}
	
	static int solve_01() {
		N = ws.length;
		dp = new int[W+1];
		for (int ni = 0; ni < N; ni ++)
			for (int wn = W; wn >= ws[ni]; wn --) {
					int dpn = dp[wn - ws[ni]] + vs[ni];
					if (dpn > dp[wn])
						dp[wn] = dpn;
				}
		return dp[W];
	}
}
