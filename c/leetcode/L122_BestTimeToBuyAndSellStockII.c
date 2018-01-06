/*
    url: leetcode.com/problems/best-time-to-buy-and-sell-stock-ii
    AC 6ms 2.24%
*/

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

int maxProfit(int* p, int pn) {
    int i = 0, a = 0;
    for (i = 1; i < pn; i ++) {
        if (p[i] > p[i-1]) {
            a += p[i] - p[i-1];
        }
    }
    return a;
}

int main() {
    int p[] = {1, 5, 2, 4};
    int pn = 4;
    printf("answer is %d\n", maxProfit(p, pn));
}