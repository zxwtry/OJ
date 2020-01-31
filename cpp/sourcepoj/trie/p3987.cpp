// AC 717MS	37360K
#include <cstring>
#include <iostream>
#include <queue>
#include <stdio.h>

using namespace std;

const int MAXINPUT = 5100010;
const int MAXN = 251000;
const int NEXTLEN = 26;
char input[MAXINPUT];
char expand[MAXINPUT];
int expandLen;

struct TrieNode {
    int trie[MAXN][NEXTLEN];
    int value[MAXN];
    int suffix[MAXN];
    char zeroChar;
    int nn;

    void init(char c) {
        zeroChar = c;
        memset(trie, 0, sizeof(trie));
        memset(value, 0, sizeof(value));
        memset(suffix, 0, sizeof(suffix));
        nn = 0;
    }

    void insert(char *s) {
        int nextIndex = 0;
        int rootNow = 0;
        for (int si = 0; s[si]; si++) {
            nextIndex = s[si] - zeroChar;
            if (!trie[rootNow][nextIndex]) {
                trie[rootNow][nextIndex] = ++nn;
            }
            rootNow = trie[rootNow][nextIndex];
        }
        value[rootNow]++;
    }

    void build() {
        queue<int> qt;
        for (int nexti = 0; nexti < NEXTLEN; nexti++) {
            if (trie[0][nexti]) {
                suffix[trie[0][nexti]] = 0;
                qt.push(trie[0][nexti]);
            }
        }
        while (!qt.empty()) {
            int rootNow = qt.front();
            int rootSuffix = suffix[rootNow];
            qt.pop();
            for (int nexti = 0; nexti < NEXTLEN; nexti++) {
                if (trie[rootNow][nexti]) {
                    suffix[trie[rootNow][nexti]] = trie[rootSuffix][nexti];
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
} trieNode;

void inputExpand() {
    expandLen = 0;
    char c;
    int times = 0;
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
        freopen("p3987.in", "r", stdin);
        freopen("p3987.out", "w", stdout);
    }
    int testCase;
    scanf("%d", &testCase);
    while (testCase--) {
        int n;
        scanf("%d", &n);
        trieNode.init('A');
        while (n--) {
            scanf("%s\n", input);
            trieNode.insert(input);
        }
        trieNode.build();
        scanf("%s\n", input);
        inputExpand();
        printf("%lld\n", trieNode.query(expand, expandLen));
    }
    return 0;
}