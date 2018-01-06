/*
    url: leetcode.com/problems/divide-two-integers/
    16ms 48.96%
*/

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

//for vc6   printf use I64d
#define LL __int64


int _d(LL a, LL b) {
    int count = 0;
    LL add1 = 0;
    int ans = 0;
    LL add = 0;
    if (b == 1) return a;
    while (add <= a) {
        add1 = b;
        count = 1;
        while (add + add1 + add1 < a) {
            add1 = add1 + add1;
            count = count + count;
        }
        add += add1;
        
        ans += count;
    }   
    return ans - 1;
}

int divide(int a, int b) {
    int sign = 0, ans = 0;
    LL aa = a;
    LL bb = b;
    if (b == 0 || (a == INT_MIN && b == -1))
        return INT_MAX;
    sign = (a ^ b) >> 31;
    aa = aa < 0 ? -aa : aa;
    bb = bb < 0 ? -bb : bb;
    //printf("%I64d\t%I64d\r\n", aa, bb);
    return sign ? -_d(aa, bb) : _d(aa, bb);
}

int main() {
    printf("%d\r\n", divide(34, 4));
}

