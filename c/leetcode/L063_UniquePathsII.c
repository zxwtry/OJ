/*
    url: leetcode.com/problems/unique-paths-ii
*/

int uniquePathsWithObstacles(int** g, int m, int n) {
    int* dp = (int*) malloc(sizeof(int) * n);
    int i = 0, j = 0;
    for (j = n-1; j > -1; j --) {
        dp[j] = (j == n-1 || dp[j+1] == 1) && g[m-1][j] == 0 ? 1 : 0;
    }
    for (i = m-2; i > -1; i --) {
        if (g[i][n-1] == 1) dp[n-1] = 0;
        for (j = n-2; j > -1; j --) {
            if (g[i][j] == 1) dp[j] = 0;
            else dp[j] += g[i][j+1] == 1 ? 0 : dp[j+1];
        }
    }
    i = dp[0];
    free(dp);
    return i;
}