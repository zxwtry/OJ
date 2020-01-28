#include<bits/stdc++.h>

using namespace std;

const int MAXM = 10008;
const int MAXN = 1000008;
int arrM[MAXM];
int arrN[MAXN];
int i, m, n;
int nxt[MAXM];

void getNext() {
    int bi = -1;
    int fi = 0;
    nxt[0] = -1;
    while (fi < m) {
        if (bi == -1 || arrM[fi] == arrM[bi]) {
            fi ++;
            bi ++;
            nxt[fi] = bi;
        } else {
            bi = nxt[bi];
        }
    }
}

int kmp() {
    getNext();
    int mi = 0;
    int ni = 0;
    while (ni != n) {
        if (mi == -1 || arrM[mi] == arrN[ni]) {
            mi ++;
            ni ++;
        } else {
            mi = nxt[mi];
        }
        if (ni == n) {
            return ni - m;
        }
    }
    return -1;
}

void process() {
    int res = kmp();
    printf("%d\n", res);
}

int main() {
    if (getenv("ZXWPC")) {
        freopen("h1711.in", "r", stdin);
        freopen("h1711.out", "w", stdout);
    }
    scanf("%d", &i);
    while (i --) {
        scanf("%d", &n);
        scanf("%d", &m);
        for (int ni = 0; ni < n; ni ++) {
            scanf("%d", &(arrN[ni]));
        }
        for (int mi = 0; mi < m; mi ++) {
            scanf("%d", &(arrM[mi]));
        }
        process();
    }
    return 0;
}




