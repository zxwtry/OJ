/*
    url: leetcode.com/problems/climbing-stairs
    AC 0ms 46.50%
*/

int climbStairs(int n) {
    int* h, i = 0;
    if (n < 3) return n;
    h = (int*) malloc(sizeof(int) * n);
    h[0] = 1;
    h[1] = 2;
    for (i = 2; i < n; i ++) {
        h[i] = h[i-1] + h[i-2];
    }
    i = h[n-1];
    free(h);
    return i;
}