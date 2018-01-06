/*
    url: leetcode.com/problems/container-with-most-water/
    13ms 18.14%
*/

#include <stdio.h>
#include <stdlib.h>

int maxArea(int* h, int s) {
    int l = 0, r = s - 1, a = 0, k = 0;
    while (l < r) {
        a = max(a, min(*(h + l), *(h + r)) * (r - l));
        if (*(h + l) > *(h + r)) {
            do {
                r --;
            } while (r > l && *(h + r) <= *(h + r + 1));
        } else {
            do {
                l ++;
            } while (l < r && *(h + l) <= *(h + l - 1));
        }
    }
    return a;
}
int main() {
    int h[] = {0, 5, 2, 3, 4, 1};
    int s = 6;
    printf("answer is %d\r\n", maxArea(h,s));
}