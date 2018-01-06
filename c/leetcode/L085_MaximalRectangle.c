/*
    url: leetcode.com/problems/maximal-rectangle
    AC 6ms 61.54%
*/

#include <stdio.h>
#include <stdlib.h>


int _max(int a, int b) {
    return a < b ? b : a;
}

int rect(int* h, int* s, int n) {
    int si = 0, i = 0, hv = 0, j = 0, l = 0, a = 0;
    for (i = 0; i <= n; i ++) {
        hv = i == n ? 0 : h[i];
        if (si == 0 || hv > h[s[si - 1]]) {
            s[si ++] = i;
        } else {
            j = s[si - 1];
            si --;
            l = si == 0 ? i : i - 1 - s[si - 1];
            a = _max(a, h[j] * l);
            i --;
        }
    }
    return a;
}

int maximalRectangle(char** m, int rn, int cn) {
    int* h = (int*) malloc(sizeof(int) * cn);
    int i = 0, j = 0, a = 0;
    int* s = (int*) malloc(sizeof(int) * cn);
    for (j = 0; j < cn; j ++) h[j] = 0;
    for (i = 0; i < rn; i ++) {
        for (j = 0; j < cn; j ++)
            h[j] = m[i][j] == '0' ? 0 : h[j] + 1;
        for (j = 0; j < cn; j ++) s[j] = 0;
        a = _max(a, rect(h, s, cn));
    }
    free(h);
    free(s);
    return a;
}

int main() {
    char** m = (char**) malloc(sizeof(char*) * 4);
    int rn = 4, cn = 5;
    m[0] = "10100";
    m[1] = "10111";
    m[2] = "11111";
    m[3] = "10010";
    printf("answer is %d\r\n", maximalRectangle(m, rn, cn));
    free(m);
    return 0;
}

