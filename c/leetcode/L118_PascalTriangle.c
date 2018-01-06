/*
    url: leetcode.com/problems/pascals-triangle
    AC 3ms 2.35%
*/

int** generate(int rn, int** cn) {
    int i = 0, j = 0, k = 0;
    int** ans = (int**) malloc(sizeof(int*) * rn);
    *cn = (int*) malloc(sizeof(int) * rn);
    for (i = 0; i < rn; i ++) {
        (*cn)[i] = i+1;
        ans[i] = (int*) malloc(sizeof(int) * (i+1));
        for (j = 0; j < i+1; j ++) {
            ans[i][0] = 1;
            ans[i][i] = 1;
            for (k = 1; k < i; k ++)
                ans[i][k] = ans[i-1][k-1] + ans[i-1][k];
        }
    }
    return ans;
}