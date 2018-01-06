/*
    url: leetcode.com/problems/best-time-to-buy-and-sell-stock
    onn: AC 573ms 11.84%
    on:  AC   6ms 29.39%
    on_1: AC  3ms 49.12%
*/

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

int onn(int* p, int pn) {
    int m = 0, i = 0, j = 0;
    for (i = 0; i < pn; i ++) {
        for (j = i+1; j < pn; j ++) {
            m = m < p[j]-p[i] ? p[j]-p[i] : m;
        }
    }
    return m;
}

int on(int* p, int pn) {
    int* m = NULL, i = 0, a = 0, mi = -1;
    if (pn == 0) return a;
    m = (int*) malloc(sizeof(int) * (pn+1));
    for (i = 0; i < pn; i ++) {        
        while (mi != -1 && m[mi] > p[i]) {
            mi --;
        }
        if (mi == -1) {
            m[++ mi] = p[i];
        } else {
            a = a < p[i] - m[mi] ? p[i] - m[mi] : a;
        }
    }
    free(m);
    return a;
}

int on_1(int* p, int pn) {
    int mv = INT_MAX, a = 0, i = 0, v = 0;
    for (i = 0; i < pn; i ++) {
        v = p[i];
        mv = mv < v ? mv : v;
        a = a > v - mv ? a : v - mv;
    }
    return a;
}

int maxProfit(int* p, int pn) {
    return on_1(p, pn);
}

int main() {
    int p[] = {4, 0, 1, 3, 5, 6, 2};
    int pn = 7;
    printf("answer is %d\n", maxProfit(p, pn));
}
