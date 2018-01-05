package stl;

public class Book1_2_3_动态规划_2_3_2_DP数组的再利用 {
	public static void main(String[] args) {
		ws = new int[]{2,3,2,4,8,6};
		vs = new int[]{3,7,6,2,4,6};
		W = 13;
		N = ws.length;
		System.out.println(solve());
	}
	static int N, W, vs[], ws[], dp[][];
	static int solve() {
		dp = new int[2][W+1];
		for (int ni = 0; ni < N; ni ++)
			for (int wn = 0; wn <= W; wn ++)
				if (wn < ws[ni])
					dp[(ni+1)&1][wn] = dp[ni & 1][wn];
				else
					dp[(ni+1)&1][wn] = Math.max(dp[ni&1][wn], dp[(ni+1)&1][wn-ws[ni]]+vs[ni]);
		return dp[N & 1][W];
	}
}