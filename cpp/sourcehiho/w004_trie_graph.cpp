#include <bits/stdc++.h>
using namespace std;

struct TrieNode {
    int flag;
    struct TrieNode *next[26];
    struct TrieNode *suffix;
    TrieNode() {
        flag = 0;
        memset(next, 0, sizeof(next));
        suffix = NULL;
    }
};

void buildTrie(struct TrieNode *root, char *s, int sn) {
    struct TrieNode *rootNow = root;
    for (int si = 0; si < sn; si++) {
        int nextIndex = s[si] - 'a';
        if (!rootNow->next[nextIndex]) {
            rootNow->next[nextIndex] = new TrieNode();
        }
        rootNow = rootNow->next[nextIndex];
    }
    rootNow->flag = 1;
}

void buildTrieGraph(struct TrieNode *root) {
    queue<struct TrieNode *> qt;
    // root节点，单独处理
    for (int i = 0; i < 26; i++) {
        if (root->next[i]) {
            // 不是空，设置suffix
            root->next[i]->suffix = root;
            qt.push(root->next[i]);
        } else {
            root->next[i] = root;
        }
    }
    // 同时存在当前节点和suffix节点情况处理。
    struct TrieNode *rootNow;
    struct TrieNode *rootSuffix;
    while (!qt.empty()) {
        rootNow = qt.front();
        qt.pop();
        rootSuffix = rootNow->suffix;
        for (int i = 0; i < 26; i++) {
            if (rootNow->next[i]) {
                rootNow->next[i]->suffix = rootSuffix->next[i];
                qt.push(rootNow->next[i]);
            } else {
                rootNow->next[i] = rootSuffix->next[i];
            }
        }
    }
}

char s[1000008];

int main() {
    if (getenv("ZXWPC")) {
        freopen("w004.in", "r", stdin);
        freopen("w004.out", "w", stdout);
    }
    int n;
    scanf("%d", &n);
    struct TrieNode *root = new TrieNode();
    while (n--) {
        scanf("%s\n", s);
        int sn = strlen(s);
        buildTrie(root, s, sn);
    }
    buildTrieGraph(root);
    scanf("%s\n", s);
    int sn = strlen(s);
    struct TrieNode *rootNow = root;
    for (int si = 0; si < sn; si++) {
        int nextIndex = s[si] - 'a';
        rootNow = rootNow->next[nextIndex];
        if (rootNow->flag) {
            printf("YES\n");
            return 0;
        }
    }
    printf("NO\n");
    return 0;
}
