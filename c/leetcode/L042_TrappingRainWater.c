/*
    url: leetcode.com/problems/trapping-rain-water/
    AC 6ms 50.00%
*/

#include <stdio.h>
#include <stdlib.h>

int trap(int* h, int s) {
    int mi = 0, i = 0;
    int j = 0;
    long ans = 0;
    for (i = 0; i < s; i ++)
        if (h[mi] < h[i])
            mi = i;
    i = 0;
    while (i < mi) {
        for (j = i + 1; j <= mi; j ++) {
            if (h[j] > h[i]) break;
            ans += h[i] - h[j];
        }
        i = j;
    }
    i = s - 1;
    while (i > mi) {
        for (j = i - 1; j >= mi; j --) {
            if (h[j] > h[i]) break;
            ans += h[i] - h[j];
        }
        i = j;
    }
    return (int)ans;
}

int main() {
    int h[] = {0,1,0,2,1,0,1,3,2,1,2,1};
    int s = 12;
    printf("answer is %d\r\n", trap(h, s));
}