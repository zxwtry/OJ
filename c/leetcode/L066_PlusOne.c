/*
    url: leetcode.com/problems/plus-one
    AC 
*/

#include <stdio.h>
#include <stdlib.h>

int* plusOne(int* d, int dn, int* rn) {
    int c = 0, i = 0;
    int* r = (int*) malloc(sizeof(int) * (dn + 1));
    
    r[dn] = d[dn-1] + 1;
    c = r[dn] / 10;
    r[dn] = r[dn] % 10;
    for (i = dn-1; i > 0; i --) {
        r[i] = c + d[i-1];
        c = r[i] / 10;
        r[i] = r[i] % 10;
    }
    if (c == 0) {
        *rn = dn;
        return r + 1;
    } else {
        *rn = dn+1;
        r[0] = c;
        return r;
    }
}

int main() {
    int d[] = {9, 9, 8};
    int rn = 0;
    int* a = plusOne(d, 3, &rn);
    int i = 0;
    for (i = 0; i < rn; i ++)
        printf("%d ", a[i]);
    printf("\r\n");
}