/*
    url: leetcode.com/problems/unique-paths
    AC 3ms 2.33%
*/

int uniquePaths(int m, int n) {
    int* dp = (int*) malloc(sizeof(int) * n);
    int i = 0, j = 0;
    for (i = n-1; i > -1; i --) {
        dp[i] = 1;
    }
    for (j = m-2; j > -1; j --) {
        for (i = n-2; i > -1; i --) {
            dp[i] += dp[i+1];
        }
    }
    i = dp[0];
    free(dp);
    return i;
}

int main() {

}