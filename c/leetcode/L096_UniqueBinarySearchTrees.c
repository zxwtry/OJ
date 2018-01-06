/*
    url: leetcode.com/problems/unique-binary-search-trees
    AC 0ms 48.89%
*/

#include <stdio.h>
#include <stdlib.h>

int numTrees(int n) {
    int ans = 0;
    int i = 0, j = 0, k = 0;
    int* r = (int*) malloc(sizeof(int) * (n+1));
    for (i = 1; i <= n; i ++) r[i] = 0;
    r[0] = 1;
    for (j = 1; j <= n; j ++) {
        for (i = 0; i < j; i ++) {
           r[j] += r[i] * r[j-1-i];
        }
    }
    ans = r[n];
    free(r);
    return ans;
}

int main() {
    printf("answer is %d\r\n", numTrees(2));
    return 0;
}