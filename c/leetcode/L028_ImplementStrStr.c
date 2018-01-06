/*
    url: leetcode.com/problems/implement-strstr/
    strStr_back:    6ms 36.36%
    strStr_kmp:     3ms 49.82%
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int* _get_next(char* p, int pn) {
    int bi = 0, fi = 0;
    int * next = (int *) malloc(sizeof(int) * (pn + 1));
    for (bi = 1; bi <= pn; bi ++)
        *(next + bi) = 0;
    *(next + 0) = -1;
    for (bi = -1, fi = 0; fi < pn; fi ++) {
        if (bi == -1 || *(p + bi) == *(p + fi)) {
            bi ++;
            fi ++;
            *(next + fi) = bi;
        } else {
            bi = *(next + bi);
        }
    }
    *(next + 0) = 0;
    return next;
}

void print_array(int * nums, int numsSize) {
    int i = 0;
    for (i = 0; i < numsSize; i ++)
        printf("%d ", *(nums + i));
    printf("\r\n");
}

int strStr_kmp(char* s, char* p) {
    int sn = strlen(s), pn = strlen(p);
    int * next = _get_next(p, pn);
    int si = 0, pi = 0;
    //print_array(next, pn + 1);
    for (si = 0; si <= sn - pn; si ++) {
        while (pi < pn && *(s + si + pi) == *(p + pi)) pi ++;
        if (pi == pn) {
            free(next);
            return si;
        }
        pi = next[pi];
    }
    free(next);
    return -1;
}

int strStr_back(char* s, char* p) {
    int i = 0, j = 0;
    char sc = '\0', pc = '\0';
    if (s == NULL || p == NULL)
        return s == NULL && p == NULL;
    while (1) {
        sc = *(s + i);
        j = 0;
        while (1) {
            pc = *(p + j);
            sc = *(s + i + j);
            if (pc == '\0') break;
            if (sc == '\0') break;
            if (pc != *(s + i + j)) break;
            j ++;
        }
        if (pc == '\0') return i;
        if (sc == '\0') return -1;
        i ++;
    }
    return -1;
}

int strStr(char* s, char* p) {
    return strStr_kmp(s, p);
}

int main() {
    char *s = "CKQYYHAHUYYCDBDFGPBXDJJKPBWLGCNGVAONMAFHRXASGEVEKQFKGQJOSTXN";
    char *p = "GP";
    printf("answer is %d\r\n", strStr(s, p));
}
