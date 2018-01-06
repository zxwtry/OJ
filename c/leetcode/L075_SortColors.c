/*
    url: leetcode.com/problems/sort-colors/
    setColors: AC 3ms 0.00%
    setColors2: AC 3ms 0.00%
*/

#include <stdio.h>
#include <stdlib.h>

void sortColors(int* n, int nn) {
    int cnt_0 = 0, cnt_1 =0, cnt_2 = 0;
    int i = 0, ni = 0;
    for (i = 0; i < nn; i ++) {
        if (n[i] == 0) cnt_0 ++;
        if (n[i] == 1) cnt_1 ++;
        if (n[i] == 2) cnt_2 ++;
    }
    for (i = 0; i < cnt_0; i ++)
        n[ni ++] = 0;
    for (i = 0; i < cnt_1; i ++)
        n[ni ++] = 1;
    for (i = 0; i < cnt_2; i ++)
        n[ni ++] = 2;
}

void swap(int* a, int *b) {
    int t = *a;
    *a = *b;
    *b = t;
}

void sortColors2(int* n, int nn) {
    int i = 0, j = nn - 1;
    int i2 = nn-1, i0 = 0;
    for (i = 0; i <= i2; i ++) {
        while (i<i2 && n[i]==2) swap(n+i, n+(i2--));
        while (i>i0 && n[i]==0) swap(n+i, n+(i0++));
    }
}
