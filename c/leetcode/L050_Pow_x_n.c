/*
    url: leetcode.com/problems/powx-n/
    AC 6ms 40.00%
*/

#include <stdio.h>
#include <stdlib.h>

double myPow(double x, int n) {
    int bits[32], i = 0, nc = 0, bn = 0, sign = n < 0;
    double* d = NULL, ans = 1;
    n = n < 0 ? -n : n;
    for (bn = 0, nc = n; nc != 0 && bn < 32; bn ++) {
        bits[bn] = nc % 2;
        nc = nc / 2;
    }
    d = (double*) malloc(sizeof(double) * bn);
    d[0] = x;
    for (i = 1; i < bn; i ++)
        d[i] = d[i - 1] * d[i - 1];
    for (i = 0; i < bn; i ++) {
        if (bits[i])
            ans *= d[i];
    }
    return sign ? 1.0 / ans : ans;
}

int main() {
    printf("%f\r\n", myPow(34.00515, -3));
    return 0;
}