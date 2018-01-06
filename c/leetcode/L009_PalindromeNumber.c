/*
    url: leetcode.com/problems/palindrome-number/
    
*/
#include <stdio.h>
#include <stdlib.h>

#define bool int
#define false 0
#define true 1

bool isPalindrome(int x) {
    int arr[14];
    int i = 0;
    int j = 0;
    int v = 0;
    int xx = x;
    if (x < 0) return false;
    while (x != 0) {
        arr[i ++] = x % 10;
        x = x / 10;
    }
    for (j = 0; j < i; j ++) {
        v = v * 10 + arr[j];
    }
    return xx == v;

}

int main() {
    printf("%d\r\n", isPalindrome(6666));
    return 0;
}