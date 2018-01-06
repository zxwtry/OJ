/*
    url: leetcode.com/problems/single-number-ii
    AC 3ms 64.06%
*/

#include <stdio.h>
#include <stdlib.h>

int singleNumber(int* n, int nn) {
    int i = 0, j = 0, sum = 0, ans = 0;
    for (i = 0; i < 32; i ++) {
        sum = 0;
        for (j = 0; j < nn; j ++) {
            sum += ((n[j] >> i) & 1);
        }
        if (sum % 3 == 1) {
            ans += 1 << i;
        }
    }
    return ans;
}

int main() {
    int n[] = {1, 1, 1, -1};
    int nn = 4;
    printf("answer is %d\n", singleNumber(n, nn));
    return 0;
}