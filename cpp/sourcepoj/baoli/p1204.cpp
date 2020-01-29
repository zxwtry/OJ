//#include <bits/stdc++.h>
#include <cstring>
#include <iostream>
#include <stdio.h>
using namespace std;

// TLE
char s[1008];

bool check(char **m, int maxi, int maxj, char *s, int mi, int mj, int di,
           int dj) {
    int si = 0;
    while (s[si] != '\0') {
        if (mi >= maxi || mj >= maxj || mi < 0 || mj < 0) {
            return false;
        }
        if (s[si] != m[mi][mj]) {
            return false;
        }
        mi += di;
        mj += dj;
        si++;
    }
    return true;
}

void solve(char **m, char *s, int l, int c) {
    int sn = strlen(s) - 1;
    for (int li = 0; li < l; li++) {
        for (int ci = 0; ci < c; ci++) {
            if (m[li][ci] != s[0]) {
                continue;
            }
            if (li - sn >= 0 && check(m, l, c, s, li, ci, -1, 0)) {
                printf("%d %d A\n", li, ci);
                return;
            }
            if (li - sn >= 0 && ci + sn < c &&
                check(m, l, c, s, li, ci, -1, 1)) {
                printf("%d %d B\n", li, ci);
                return;
            }
            if (ci + sn < c && check(m, l, c, s, li, ci, 0, 1)) {

                printf("%d %d C\n", li, ci);
                return;
            }
            if (li + sn < l && ci + sn < c && check(m, l, c, s, li, ci, 1, 1)) {

                printf("%d %d D\n", li, ci);
                return;
            }
            if (li + sn < l && check(m, l, c, s, li, ci, 1, 0)) {

                printf("%d %d E\n", li, ci);
                return;
            }
            if (li + sn < l && ci - sn >= 0 &&
                check(m, l, c, s, li, ci, 1, -1)) {

                printf("%d %d F\n", li, ci);
                return;
            }
            if (ci - sn >= 0 && check(m, l, c, s, li, ci, 0, -1)) {

                printf("%d %d G\n", li, ci);
                return;
            }
            if (li - sn >= 0 && ci - sn >= 0 &&
                check(m, l, c, s, li, ci, -1, -1)) {

                printf("%d %d H\n", li, ci);
                return;
            }
        }
    }
}

int main() {
    if (getenv("ZXWPC")) {
        freopen("p1204.in", "r", stdin);
        freopen("p1204.out", "w", stdout);
    }
    int l, c, w;
    scanf("%d %d %d\n", &l, &c, &w);
    char **m;
    m = (char **)malloc(sizeof(char *) * l);
    for (int i = 0; i < l; i++) {
        m[i] = (char *)malloc(sizeof(char) * (c + 1));
        scanf("%s\n", m[i]);
    }
    while (w--) {
        scanf("%s\n", s);
        solve(m, s, l, c);
    }
}