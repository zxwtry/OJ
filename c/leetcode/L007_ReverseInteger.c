/*
    url: leetcode.com/problems/reverse-integer
    22ms  17.10%
*/

#include <stdio.h>
#include <stdlib.h>

int reverse(int x) {
    int arr[12];
    int i;
    int ans = 0;
    int j = 0;
    int pre = 0;
    int max = 2147483647;
    if (x == -2147483648)
        return 0;
    if (x < 0)
        return -reverse(-x);
    for (i = 0; i < 12 && x != 0; i ++) {
        arr[i] = x % 10;
        x = x / 10;
    }
    for (j = 0; j < i; j ++) {
        pre = ans;
        if (ans > max / 10 || (ans == max / 10 && arr[j] > 7))
            return 0;
        ans = ans * 10 + arr[j];
    }
    return ans;
}

int main() {
    printf("%d\r\n", reverse(1534236469));
}