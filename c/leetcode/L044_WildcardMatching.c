/*
    url: leetcode.com/problems/wildcard-matching/
    AC 56ms 9.01%
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define bool int

bool isMatch(char* s, char* p) {
    int sn = s == NULL ? 0 : strlen(s);
    int pn = p == NULL ? 0 : strlen(p);
    char** m = (char**) malloc(sizeof(char*) * (sn + 2));
    char* mt = NULL;
    int si = 0, pi = 0;
    //if (sn == 0 || pn == 0) return sn == 0 && pn == 0;
    for (si = 0; si < sn + 2; si ++) {
        *(m + si) = malloc(sizeof(char) * (pn + 2));
        memset(*(m + si), '0', pn + 2);
    }
    m[0][0] = '1';
    for (pi = 0; pi < pn; pi ++) {
        if (m[0][pi] == '1' && p[pi] == '*')
            m[0][pi + 1] = '1';
    }
    for (si = 0; si < sn; si ++) {
        for (pi = 0; pi < pn; pi ++) {
            if (p[pi] == '?' || p[pi] == s[si]) {
                m[si + 1][pi + 1] = m[si][pi];
            } 
            if (p[pi] == '*') {
                if (m[si + 1][pi] == '1') {
                    m[si + 1][pi + 1] = '1';
                    continue;
                }
                if (m[si][pi] == '1') {
                    m[si + 1][pi + 1] = '1';
                    continue;
                }
                if (m[si][pi + 1] == '1') {
                    m[si + 1][pi + 1] = '1';
                    continue;
                }
            }
        }
    }
    //ans
    pi = m[sn][pn] - '0';
    for (si = 0; si < sn + 2; si ++) {
        free(*(m + si));
    }
    free(m);
    return pi;
}

int main() {
    char* s = "";
    char* p = "*";
    printf("answer is %d\r\n", isMatch(s, p));
    return 0;
}