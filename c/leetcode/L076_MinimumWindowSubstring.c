/*
    url: leetcode.com/problems/minimum-window-substring/
    minWindow TLE
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

char* minWindow(char* s, char* t) {
    int m[128], sn = strlen(s), tn = strlen(t);
    int cnt = tn, i = 0, j = 0, d = INT_MAX, h = 0;
    char *ans = NULL;
    for (i = 0; i < 128; i ++) m[i] = 0;
    for (i = 0; i < tn ;i ++) m[t[i]] ++;
    i = 0;
    while (j < sn) {
        if (m[s[j++]] -- > 0) cnt --;
        while (cnt == 0) {
            if (j-i < d) {
                d = j - i;
                h = i;
            }
            if (m[s[i++]]++ == 0) cnt ++;
        }
    }
    d = d == INT_MAX ? 0 : d;
    ans = (char*) malloc(sizeof(char) * (d+1));
    for (i = 0; i < d; i ++) ans[i] = s[i+h];
    ans[d] = '\0';
    return ans;
}

int main() {
    char* s = "cabwefgewcwaefgcf";
    char* t = "caz";
    char* ans = minWindow(s, t);
    printf("answer is %s\r\n", ans);
    free(ans);
    return 0;
}