/*
    url: leetcode.com/problems/interleaving-string
    recur: TLE
    dp/dp2: AC 3ms 42.86%
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define bool int

bool cmp_str(char* s1, int i1, int n1, char* s2, int i2, int n2) {
    if (n1-i1 != n2-i2) return 0;
    while (i1 < n1) {
        if (s1[i1] != s2[i2]) return 0;
        i1 ++;
        i2 ++;
    }
    return 1;
}

bool search(char* s1, int i1, int n1, char* s2, int i2, int n2, char* s3, int i3, int n3) {
    if (i1 == n1) return cmp_str(s2, i2, n2, s3, i3, n3);
    if (i2 == n2) return cmp_str(s1, i1, n1, s3, i3, n3);
    if (s1[i1] == s3[i3] && search(s1, i1+1, n1, s2, i2, n2, s3, i3+1, n3))
        return 1;
    if (s2[i2] == s3[i3] && search(s1, i1, n1, s2, i2+1, n2, s3, i3+1, n3))
        return 1;
    return 0;
}

bool isInterleave_recur(char* s1, char* s2, char* s3) {
    int n1 = s1 == NULL ? 0 : strlen(s1);
    int n2 = s2 == NULL ? 0 : strlen(s2);
    int n3 = s3 == NULL ? 0 : strlen(s3);
    if (n1+n2 != n3) return 0;
    return search(s1, 0, n1, s2, 0, n2, s3, 0, n3);
}

bool isInterleave_dp(char* s1, char* s2, char* s3) {
    int n1 = s1 == NULL ? 0 : strlen(s1);
    int n2 = s2 == NULL ? 0 : strlen(s2);
    int n3 = s3 == NULL ? 0 : strlen(s3);
    int i1 = 0, i2 = 0, ans = 0;
    char** m = NULL;
    if (n1+n2 != n3) return 0;
    m = (char**) malloc(sizeof(char*) * (n1+1));
    for (i1 = 0; i1 <= n1; i1 ++) {
        m[i1] = (char*) malloc(sizeof(char) * (n2 + 1));
        memset(m[i1], '0', n2+1);
    }
    m[0][0] = '1';
    for (i1 = 1; i1 <= n1; i1 ++) {
        if (s1[i1-1] != s3[i1-1]) break;
        m[i1][0] = '1';
    }
    for (i2 = 1; i2 <= n2; i2 ++) {
        if (s2[i2-1] != s3[i2-1]) break;
        m[0][i2] = '1';
    }
    for (i1 = 1; i1 <= n1; i1 ++) {
        for (i2 = 1; i2 <= n2; i2 ++) {
            if (s1[i1-1] == s3[i1+i2-1]) {
                if (m[i1-1][i2] == '1')
                    m[i1][i2] = '1';
            }
            if (s2[i2-1] == s3[i1+i2-1]) {
                if (m[i1][i2-1] == '1')
                    m[i1][i2] = '1';
            }
        }
    }
    ans = m[n1][n2] == '1';
    for (i1 = 0; i1 <= n1; i1 ++) free(m[i1]);
    return ans;
}

bool isInterleave_dp2(char* s1, char* s2, char* s3) {
    int n1 = s1 == NULL ? 0 : strlen(s1);
    int n2 = s2 == NULL ? 0 : strlen(s2);
    int n3 = s3 == NULL ? 0 : strlen(s3);
    int i1 = 0, i2 = 0, ans = 0;
    char* m = NULL;
    if (n1+n2 != n3) return 0;
    m = (char*) malloc(sizeof(char) * (n2 + 2));
    memset(m, '0', n2+2);
    m[n2+1] = '\0';
    m[0] = '1';
    for (i2 = 1; i2 <= n2; i2 ++) {
        if (s2[i2-1] != s3[i2-1]) break;
        m[i2] = '1';
    }
    for (i1 = 1; i1 <= n1; i1 ++) {
        if (m[0] == '1' && s1[i1-1] != s3[i1-1])
            m[0] = '0';
        for (i2 = 1; i2 <= n2; i2 ++) {
            if (s2[i2-1] == s3[i1+i2-1] && m[i2-1] == '1') {
                m[i2] = '1';
            } else if (s1[i1-1] == s3[i1+i2-1] && m[i2] == '1') {
                m[i2] = '1';
            } else  m[i2] = '0';
        }
    }
    ans = m[n2] == '1';
    free(m);
    return ans;
}

bool isInterleave(char* s1, char* s2, char* s3) {
    return isInterleave_dp2(s1, s2, s3);
}

int main() {
    char* s1 = "aacaac";
    char* s2 = "aacaaeaac";
    char* s3 = "aacaaeaaeaacaac";
    printf("answer is %d\r\n", isInterleave_dp(s1, s2, s3));
    printf("answer is %d\r\n", isInterleave_recur(s1, s2, s3));    
    printf("answer is %d\r\n", isInterleave_dp2(s1, s2, s3));
    return 0;
}