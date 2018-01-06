/*
    url: leetcode.com/problems/permutation-sequence/
    AC 3ms 54.55%
*/

#include <stdio.h>
#include <stdlib.h>

char* getPermutation(int n, int k) {
    char* ans = (char*) malloc(sizeof(char) * (n+1));
    int* h = (int*) malloc(sizeof(int) * n);
    int* m = (int*) malloc(sizeof(int) * n);
    int ai = 0, ni = 0;
    int i = 0, v = 1, cnt = 0, mi = 0;
    k --;
    for (i = 0; i < n; i ++)
        m[i] = 0;
    for (i = n-1; i > -1; i --) {
        h[i] = v;
        v *= (n - i);
    }
    for (ni = 0; ni < n-1; ni ++) {
        cnt = k / h[ni];
        mi = 0;
        while (cnt > 0) {
            while (m[mi] == 1) mi ++;
            mi ++;
            cnt --;
        }
        while (m[mi] == 1) mi ++;
        ans[ni] = (char) ('1' + mi); 
        m[mi] = 1;
        k = k % h[ni];
    }
    for (i = 0; i < n; i ++)
        if (m[i] == 0) {
            ans[n-1] = (char)('1' + i);
        }
    ans[n] = '\0';
    free(h);
    free(m);
    return ans;
}

int main() {
    int n = 6;
    int k = 1;char* ans = NULL;
    for (k = 1; k <= 6*5*4*3*2; k ++) {
        char* ans = getPermutation(n, k);
        printf("k is %d  answer is %s\r\n", k, ans);
        free(ans);
    }
    return 0;
}