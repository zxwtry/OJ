/*
    url: leetcode.com/problems/decode-ways
    AC 3ms 22.50%
*/

#include <stdio.h>
#include <stdlib.h>

int numDecodings(char* s) {
    char c;
    int i = 0;
    int cnt = 0, p1 = 0, p2 = 0;
    int v = 0;
    while (1) {
        c = s[i];
        p2 = p1;
        p1 = cnt;
        if (c == '\0') break;
        if (i == 0) {
            if (c == '0') return 0;
            cnt = 1;
        } else {
            v = (s[i-1]-'0') * 10 + s[i] - '0';
            if (i == 1) {
                if (v % 10 == 0) {
                    if (v > 20 || v == 0) return 0;
                } else {
                    if (v > 10 && v < 27) cnt = 2;
                    if (v > 0 && v < 10) cnt = p1;
                }
            } else {
                if (v % 10 == 0) {
                    if (v > 20 || v == 0) return 0;
                    cnt = p2;
                } else {
                    if (v > 10 && v < 27) cnt += p2;
                    if (v > 0 && v < 10) cnt = p1;
                }
            }
        }
        i ++;
    }
    return cnt;
}

int main() {
    char* s = "10";
    printf("answer is %d\r\n", numDecodings(s));
    return 0;
}