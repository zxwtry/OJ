/*
    url: leetcode.com/problems/distinct-subsequences
    unrecur:  AC 6ms 33.33%
    unrecur2: AC 3ms 58.33%
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//TLE
int recur(char* s, char* t) {
    if (*t == '\0') return 1;
    if (*s == '\0') return 0;
    if (*s == *t) return recur(s+1, t) + recur(s+1, t+1);
    else return recur(s+1, t);
}

int unrecur(char* s, char* t) {
    int sn = s == NULL ? 0 : strlen(s);
    int tn = t == NULL ? 0 : strlen(t);
    int i = 0, j = 0, ans = 0;
    int** m = (int**) malloc(sizeof(int*) * (sn+1));
    for (i = 0; i <= sn; i ++) {
        m[i] = (int*) malloc(sizeof(int) * (tn+1));
        for (j = 0; j <= tn; j ++) m[i][j] = 0;
    }
    for (i = 0, j = tn; i <= sn; i ++) m[i][j] = 1;
    for (i = sn-1; i > -1; i --) {
        for (j = tn-1; j > -1; j --) {
            if (s[i] == t[j]) {
                m[i][j] = m[i+1][j] + m[i+1][j+1];
            } else {
                m[i][j] = m[i+1][j];
            }
        }
    }
    ans = m[0][0];
    for (i = 0; i <= sn; i ++) free(m[i]);
    free(m);
    return ans;
}

int unrecur2(char* s, char* t) {
    int sn = s == NULL ? 0 : strlen(s);
    int tn = t == NULL ? 0 : strlen(t);
    int i = 0, j = 0, ans = 0, pre = 0, tmp;
    int* m = (int*) malloc(sizeof(int) * (tn));
    for (j = 0; j < tn; j ++) m[j] = 0;
    for (i = sn-1; i > -1; i --) {
        pre = 1;
        for (j = tn-1; j > -1; j --) {
            tmp = m[j];
            if (s[i] == t[j]) {
                m[j] += pre;
            }
            pre = tmp;
        }
    }
    ans = m[0];
    free(m);
    return ans;
}

int numDistinct(char* s, char* t) {
    return unrecur2(s, t);
}

int main() {
    char* s = "rraaat";
    char* t = "rat";
    printf("%d\r\n", unrecur2(s, t));
    printf("%d\r\n", unrecur(s, t));
}