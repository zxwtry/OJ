// AC 32704K 485MS
#include <cstring>
#include <iostream>
#include <queue>
#include <stdio.h>

using namespace std;

const int MAXN = 5100010; // 输入串
const int N = 251000;     // 匹配串，最多250 * 1000长度
char input[MAXN];
char expand[MAXN];
int expandLen;
const int NEXTLEN = 26;

struct Dfa {
    int trie[N][NEXTLEN]; // 存储next
    int nn;               // 叫nn更加合适
    int value[N];
    int suffix[N];
    char zeroChar;

    void init(char c) {
        zeroChar = c;
        nn = 0;
        memset(trie, 0, sizeof(trie));
        memset(value, 0, sizeof(value));
        memset(suffix, 0, sizeof(suffix));
    }

    void insert(char *s) {
        int rootNow = 0;
        for (int si = 0; s[si]; si++) {
            int nexti = s[si] - zeroChar;
            if (!trie[rootNow][nexti]) {
                trie[rootNow][nexti] = ++nn;
            }
            rootNow = trie[rootNow][nexti];
        }
        value[rootNow]++;
    }

    void build() {
        queue<int> qt;
        for (int nexti = 0; nexti < NEXTLEN; nexti++) {
            if (trie[0][nexti]) {
                suffix[trie[0][nexti]] = 0; // 指向root
                qt.push(trie[0][nexti]);
            }
        }

        while (!qt.empty()) {
            int rootNow = qt.front();
            qt.pop();
            int rootSuffix = suffix[rootNow];
            for (int nexti = 0; nexti < NEXTLEN; nexti++) {
                if (trie[rootNow][nexti]) {
                    suffix[trie[rootNow][nexti]] =
                        trie[rootSuffix][nexti]; // 指向回溯节点的nexti
                    qt.push(trie[rootNow][nexti]);
                } else {
                    trie[rootNow][nexti] = trie[rootSuffix][nexti];
                }
            }
        }
    }

    long long query(char *s, int sn) {
        long long ans = 0;
        int rootNow = 0;
        for (int si = 0; s[si]; si++) {
            rootNow = trie[rootNow][s[si] - zeroChar];
            for (int rootCount = rootNow;
                 rootCount > 0 && value[rootCount] != -1;
                 rootCount = suffix[rootCount]) {
                ans += value[rootCount];
                value[rootCount] = -1;
            }
        }
        rootNow = 0;
        for (int si = sn - 1; si >= 0; si--) {
            rootNow = trie[rootNow][s[si] - zeroChar];
            for (int rootCount = rootNow;
                 rootCount > 0 && value[rootCount] != -1;
                 rootCount = suffix[rootCount]) {
                ans += value[rootCount];
                value[rootCount] = -1;
            }
        }
        return ans;
    }

} dfa;

void expandInput() {
    expandLen = 0;
    int times = 0;
    char c = '\0';
    for (int inputIndex = 0; input[inputIndex]; inputIndex++) {
        c = input[inputIndex];
        if (c == '[' || c == ']') {
        } else if (c >= '0' && c <= '9') {
            times = times * 10 + c - '0';
        } else if (c >= 'A' && c <= 'Z') {
            times = times == 0 ? 1 : times;
            while (times--) {
                expand[expandLen++] = c;
            }
            times = 0;
        }
    }
    expand[expandLen] = '\0';
}

int main() {
    if (getenv("ZXWPC")) {
        freopen("w004_ac.in", "r", stdin);
        freopen("w004_ac.out", "w", stdout);
    }
    int testCase;
    long long ans;
    scanf("%d", &testCase);
    while (testCase--) {
        dfa.init('A');
        int n;
        scanf("%d", &n);
        for (int ni = 0; ni < n; ni++) {
            scanf("%s\n", input);
            dfa.insert(input);
        }
        dfa.build();
        scanf("%s\n", input);
        expandInput();
        ans = dfa.query(expand, expandLen);
        printf("%lld\n", ans);
    }
    return 0;
}