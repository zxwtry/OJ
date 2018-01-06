/*
    url: leetcode.com/problems/word-break
    AC 3ms 73.91%
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define bool int

bool cmp(char* s, int i, char* t, int tn) {
    int si = i, ti = 0;
    for (; ti < tn; ti ++) {
        if (s[si++] != t[ti])
            return 0;
    }
    return 1;
}
bool wordBreak(char* s, char** d, int dn) {
    int  sn = s == 0 ? 0 : strlen(s);
    int wn = 0, i = 0, ans = 0, j = 0;
    char* dp = (char*)malloc(sizeof(char) * (sn+1));
    int* ws = (int*)malloc(sizeof(int) * (dn));
    for (j = 0; j < dn; j ++) ws[j] = strlen(d[j]);
    memset(dp, 0, sn+1);
    dp[0] = 1;
    for (i = 0; i < sn; i ++) {
        if (dp[i] == 0) continue;
        for (j = 0; j < dn; j ++) {            
            wn = ws[j];
            if (i+wn <= sn && (dp[i+wn] == 0) && cmp(s, i, d[j], wn))
                dp[i+wn] = 1;
        }
    }
    ans = dp[sn];
    free(dp);
    free(ws);
    return ans;
}

int main() {
    char* s = "leetcode";
    char* d1 = "leet";
    char* d2 = "code";
    char** d = (char**) malloc(sizeof(char*) * 2);
    int dn = 2;
    d[0] = d1;
    d[1] = d2;
    printf("ans is %d\n", wordBreak(s, d, dn));
    free(d);
    return 0;
}