/*
    url: leetcode.com/problems/jump-game/
    AC 6ms 31.34%
*/

#include <stdio.h>
#include <stdlib.h>

#define bool int

int _max(int a, int b) {
    return a < b ? b : a;
}

bool canJump(int* n, int nn) {
    int mti = 0, i = 0;
    for (i = 0; i < nn; i ++) {
        if (mti < i) return 0;
        mti = _max(mti, n[i] + i);
        if (mti > nn-1) return 1;
    }
    return 1;
}

int main() {
    int n[] = {3,2,1,0,4};
    int nn = 5;
    printf("answer is %d\r\n", canJump(n, nn));
}