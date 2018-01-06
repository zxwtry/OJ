/*
    url: leetcode.com/problems/triangle
    AC 6ms 4.76%
*/

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

long _min(long* m, int i, int j, int k) {
    if (i == -1) return m[j];
    if (j >= k) return m[i];
    return m[i] < m[j] ? m[i] : m[j];
}

int minimumTotal(int** t, int rn, int *cn) {
    int i = 0, j = 0;
    long ans = LONG_MAX;
    long* m = NULL;
    if (rn < 1) return 0;
    m = (long*) malloc(sizeof(long) * cn[rn-1]);
    for (j = 0; j < cn[rn-1]; j ++) m[j] = 0l;
    m[0] = t[0][0];
    for (i = 1; i < rn; i ++) {
        for (j = cn[i]-1; j > -1; j --)
            m[j] = t[i][j] + _min(m, j-1, j, cn[i-1]);
    }
    for (j = 0; j < cn[rn-1]; j ++)
        ans = ans > m[j] ? m[j] : ans;
    free(m);
    return ans;
}