#include <bits/stdc++.h>
using namespace std;

const int N = (1 << 23) + (1 << 22);
int r[N];
char s[N];
char t[N];

// 简单manacher实现
int main() {
    int i;
    if (getenv("ZXWPC")) {
        freopen("w001.in", "r", stdin);
        freopen("w001.out_1", "w", stdout);
    }
    t[0] = '#';
    scanf("%d", &i);
    while (i --) {
        scanf("%s", s);
        int n = 0;
        t[n ++] = ' ';
        t[n ++] = '#';
        for (int j = 0; s[j]; j ++) {
            t[n ++] = s[j];
            t[n ++] = '#';
        }
        // ti: 圆圈最远的下标
        // ci: 最远下标对应的圆点
        int ans = 1;
        int ci = 0;
        int ti = 0;
        for (int k = 1; k < n; k ++) {
            // 设置r[k]
            if (ti > k) {
                r[k] = min(ti - k, r[2 * ci - k]);
            } else {
                r[k] = 1;
            }
            // r[k]扩大
            while (t[k + r[k]] == t[k - r[k]]) r[k] ++;
            if (k + r[k] > ti) {
                ti = k + r[k];
                ci = k;
            }
            ans = max(ans, r[k]-1);
        }
        printf("%d\n", ans);
    }
}

