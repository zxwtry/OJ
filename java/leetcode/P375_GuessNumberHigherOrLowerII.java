package leetcode;

public class P375_GuessNumberHigherOrLowerII {
    public static void main(String[] args) {
        System.out.println(new Solution2().getMoneyAmount(100));
    }
    static class Solution {
        public int getMoneyAmount(int n) {
            int[][] dp = new int[n + 1][n + 1];
            return search(dp, 1, n);
        }

        private int search(int[][] dp, int i, int j) {
            if (i >= j) return 0;
            if (dp[i][j] != 0) return dp[i][j];
            int ans = Integer.MAX_VALUE;
            for (int x = i; x <= j; x ++) {
                ans = Math.min(ans, x + Math.max(search(dp, i, x - 1) , search(dp, x + 1, j)));
            }
            dp[i][j] = ans;
            return ans;
        }
    }
    static class Solution2 {
        public int getMoneyAmount(int n) {
            int[][] dp = new int[n + 1][n + 1];
            for (int range = 1; range < n; range ++) {
                for (int i = 1; i <= n - range; i ++) {
                    int j = i + range;
                    int ansIJ = Integer.MAX_VALUE;
                    for (int x = i; x <= j; x ++) {
                        ansIJ = Math.min(ansIJ, x + Math.max(getV(dp, i, x-1), getV(dp, x+1, j)));
                    }
                    dp[i][j] = ansIJ; 
                }
            }
            return dp[1][n];
        }
        public int getV(int[][] dp, int i, int j) {
            if (i >= j) return 0;
            return dp[i][j];
        }
    }
}
