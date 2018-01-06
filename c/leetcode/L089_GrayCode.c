/*
    url: leetcode.com/problems/gray-code
    AC 3ms 26.92%
*/

#include <stdio.h>
#include <stdlib.h>

int* grayCode(int n, int* rn) {
    int* a = NULL, ai = 0;
    int* r = NULL, ri = 0, v = 0, *t = NULL;
    int an = 1 << n;
    if (n < 1) {
        *rn = 1;
        a = (int*) malloc(sizeof(int) * 1);
        a[0] = 0;
        return a;
    }
    a = (int*) malloc(sizeof(int) * an);
    r = (int*) malloc(sizeof(int) * n);
    t = (int*) malloc(sizeof(int) * n);
    v = 2;
    for (ri = n-1; ri > -1; ri --) {
        r[ri] = v;
        t[ri] = -v/2;
        v <<= 1;
    }
    while (1) {
        v = 0;
        for (ri = 0; ri < n; ri ++) 
            if (t[ri] > 0) v += r[ri]/2;
        a[ai ++] = v;
        if (ai == an) break;
        for (ri = 0; ri < n; ri ++) {
            if (t[ri] > 0) {
                t[ri] --;
                if (t[ri] == 0) t[ri] = -r[ri];
            } else {
                t[ri] ++;
                if (t[ri] == 0) t[ri] = r[ri];
            }
        }
    }
    *rn = an;
    return a;
}

int main() {
    int n = 3;
    int rn = 0;
    int* a = grayCode(n, &rn);
    int i = 0;
    for (i = 0; i < rn; i ++)
        printf("%d\r\n", a[i]);
    free(a);
    return 0;
}