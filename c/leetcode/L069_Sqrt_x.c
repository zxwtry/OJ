/*
    url: leetcode.com/problems/sqrtx
    mySqrt: AC 3ms 46.59%
    mySqrt2: AC 3ms 46.59%
*/

#include <math.h>
#include <stdio.h>
#include <stdlib.h>


int mySqrt(int x) {
    return (int) sqrt(x);
}

int cmp2(int sqrt_val, int x) {
    int val = sqrt_val * sqrt_val;
    if (val < 0) return 1;
    if (val < x) return -1;
    if (val > x) return 1;
    return 0;
}

int mySqrt2(int x) {
    int bit_1 = 0, xc = x;
    int sqrt_min = 0, sqrt_max = 0;
    int sqrt_mid = 0;
    
    if (x == 0) return 0;

    while (xc != 0) {
        bit_1 ++;
        xc = xc >> 1;
    }
    
    sqrt_max = 1 << ((bit_1+1) / 2);
    sqrt_min = 1 << ((bit_1-1) / 2);

    while (sqrt_min < sqrt_max) {
        sqrt_mid = (sqrt_max + sqrt_min) / 2;
        if (cmp2(sqrt_mid, x) <= 0) {
            if (cmp2(sqrt_mid+1, x) > 0)
                return sqrt_mid;
            sqrt_min = sqrt_mid + 1;
        } else {
            sqrt_max = sqrt_mid - 1;
        }
    }
    return sqrt_min;
}

int main() {
    printf("answer is %d\r\n", mySqrt2(2147395599));
    return 0;
}
