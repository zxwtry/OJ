package stl;

import java.util.Arrays;

public class Book1_2_3_动态规划_2_3_1_最长公共子序列问题 {
	public static void main(String[] args) {
		str1 = "abcd";
		str2 = "becd";
		System.out.println(solve());
	}
	static int N, M, dp[][];
	static String str1, str2;
	static int solve() {
		N = str1.length(); M = str2.length();
		dp = new int[N+1][M+1];
		Arrays.fill(dp[0], 0);
		for (int mi = 0; mi < M; mi ++)
			dp[mi][0] = 0;
		for (int ni = 0; ni < N; ni ++)
			for (int mi = 0; mi < M; mi ++)
				if (str1.charAt(ni) == str2.charAt(mi))
					dp[ni+1][mi+1] = dp[ni][mi] + 1;
				else
					dp[ni+1][mi+1] = Math.max(dp[ni][mi+1], dp[ni+1][mi]);
		return dp[N][M];
	}
}