/*
    url: leetcode.com/problems/valid-palindrome
    AC 3ms 55.29%
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

#define bool int

int cmp(char a, char b) {
    if (a >= 'A' && a <= 'Z') a = (char)(a - 'A' + 'a');
    if (b >= 'A' && b <= 'Z') b = (char)(b - 'A' + 'a');
    if (!((a >= 'a' && a <= 'z') || (a >= '0' && a <= '9'))) return INT_MIN;
    if (!((b >= 'a' && b <= 'z') || (b >= '0' && b <= '9'))) return INT_MAX;
    return a - b;
}

bool isPalindrome(char* s) {
    int l = 0, r = (s==NULL?0:strlen(s))-1, j = 0;
    while (l < r) {
        j = cmp(s[l], s[r]);
        if (j == INT_MIN) l ++;
        else if (j == INT_MAX) r --;
        else if (j != 0) return 0;
        else {
            l ++;
            r --;
        }
    }
    return 1;
}

int main() {
    char* s = "0P";
    printf("answer is %d\n", isPalindrome(s));
}
