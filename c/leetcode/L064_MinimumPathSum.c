/*
    url: leetcode.com/problems/minimum-path-sum
    AC 6ms 18.18%
*/

int _min(int a, int b) {
    return a < b ? a : b;
}

int minPathSum(int** g, int rn, int cn) {
    int* h = (int*) malloc(sizeof(int) * cn);
    int i = 0, j = 0;
    for (j = cn-1; j > -1; j --)
        h[j] = (j==cn-1?0:h[j+1])+g[rn-1][j];
    for (i = rn-2; i > -1; i --) {
        h[cn-1] += g[i][cn-1];
        for (j = cn-2; j > -1; j --) {
            h[j] = _min(h[j], h[j+1]) + g[i][j];
        }
    }
    i = h[0];
    free(h);
    return i;
}

