#include <cstdio>
#include <iostream>
using namespace std;
#define N 15010
int n, m, v[N], c[N], f[N];
int main() {
    if (getenv("ZXWPC")) {
        freopen("p3624.in", "r", stdin);
        freopen("p3624.out", "w", stdout);
    }
    scanf("%d%d", &n, &m);
    for (int i = 1; i <= n; i++)
        scanf("%d%d", &v[i], &c[i]);
    for (int i = 1; i <= n; i++) {
        for (int j = m; j >= v[i]; j--) {
            f[j] = max(f[j], f[j - v[i]] + c[i]);
        }
    }
    printf("%d\n", f[m]);
    return 0;
}