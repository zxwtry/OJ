// AC 14268K	1344MS
#include <cstring>
#include <iostream>
#include <stdio.h>
using namespace std;

const int MAXN = 1008;
const int NEXTLEN = 26;
char m[MAXN][MAXN];
int l, c, w;
char s[MAXN];
int ans[MAXN][3];
int dl[] = {-1, -1, 0, 1, 1, 1, 0, -1};
int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};

struct TrieNode {
    struct TrieNode *next[NEXTLEN];
    int id;
    TrieNode() {
        memset(next, 0, sizeof(next));
        id = -1;
    }
};

void buildTrie(struct TrieNode *root, char *s, int id) {
    struct TrieNode *rootNow = root;
    int si = 0;
    char c;
    int nextIndex = 0;
    while (true) {
        c = s[si++];
        if (c == '\0') {
            break;
        }
        nextIndex = c - 'A';
        if (!rootNow->next[nextIndex]) {
            rootNow->next[nextIndex] = new TrieNode();
        }
        rootNow = rootNow->next[nextIndex];
    }
    rootNow->id = id;
}

void search(int li, int ci, struct TrieNode *root) {
    for (int di = 0; di < 8; di++) {
        int lj = li;
        int cj = ci;
        struct TrieNode *rootNow = root;
        while (lj >= 0 && lj < l && cj >= 0 && cj < c) {
            char c = m[lj][cj];
            int nextIndex = c - 'A';
            if (rootNow->next[nextIndex]) {
                rootNow = rootNow->next[nextIndex];
                if (rootNow->id != -1) {
                    ans[rootNow->id][0] = li;
                    ans[rootNow->id][1] = ci;
                    ans[rootNow->id][2] = di;
                }
            } else {
                break;
            }
            lj += dl[di];
            cj += dc[di];
        }
    }
}

int main() {
    if (getenv("ZXWPC")) {
        freopen("p1204.in", "r", stdin);
        freopen("p1204.out", "w", stdout);
    }
    scanf("%d %d %d\n", &l, &c, &w);
    for (int i = 0; i < l; i++) {
        scanf("%s\n", m[i]);
    }

    struct TrieNode *root = new TrieNode();
    for (int wi = 0; wi < w; wi++) {
        scanf("%s\n", s);
        buildTrie(root, s, wi);
    }

    for (int li = 0; li < l; li++) {
        for (int ci = 0; ci < c; ci++) {
            search(li, ci, root);
        }
    }

    for (int wi = 0; wi < w; wi++) {
        printf("%d %d %c\n", ans[wi][0], ans[wi][1], (char)(ans[wi][2] + 'A'));
    }
}