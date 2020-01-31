// AC 30740K	672MS
#include <cstring>
#include <iostream>
#include <queue>
#include <stdio.h>

using namespace std;
const int NEXTLEN = 26;
const char ZEROCHAR = 'A';
const int INPUTLEN = 5200000;

char input[INPUTLEN];
char expand[INPUTLEN];
int expandLen = 0;

struct TrieNode {
    int count;
    struct TrieNode *next[NEXTLEN];
    struct TrieNode *suffix;
    struct TrieNode *parent; // 只用于free
};

struct TrieNode *TrieNodeNew(struct TrieNode *parent) {
    struct TrieNode *trieNode =
        (struct TrieNode *)malloc(sizeof(struct TrieNode));
    trieNode->count = 0;
    memset(trieNode->next, 0, sizeof(trieNode->next));
    trieNode->suffix = NULL;
    trieNode->parent = parent;
    return trieNode;
}

void trieNodeInsert(struct TrieNode *root, char *s, int sn) {
    struct TrieNode *rootNow = root;
    for (int si = 0; si < sn; si++) {
        int nextIndex = s[si] - ZEROCHAR;
        if (!rootNow->next[nextIndex]) {
            rootNow->next[nextIndex] = TrieNodeNew(rootNow);
        }
        rootNow = rootNow->next[nextIndex];
    }
    rootNow->count++;
}

void trieNodeBuild(struct TrieNode *root) {
    queue<struct TrieNode *> qt;
    for (int nexti = 0; nexti < NEXTLEN; nexti++) {
        if (root->next[nexti]) {
            root->next[nexti]->suffix = root;
            qt.push(root->next[nexti]);
        } else {
            root->next[nexti] = root;
        }
    }

    while (!qt.empty()) {
        struct TrieNode *rootNow = NULL;
        struct TrieNode *rootSuffix = NULL;
        rootNow = qt.front();
        qt.pop();
        rootSuffix = rootNow->suffix;
        for (int nexti = 0; nexti < NEXTLEN; nexti++) {
            if (rootNow->next[nexti]) {
                rootNow->next[nexti]->suffix = rootSuffix->next[nexti];
                qt.push(rootNow->next[nexti]);
            } else {
                rootNow->next[nexti] = rootSuffix->next[nexti];
            }
        }
    }
}

long long trieNodeQuery(struct TrieNode *root, char *s, int sn) {
    long long ans = 0;
    TrieNode *rootNow = root;
    int nextIndex = 0;
    for (int si = 0; si < sn; si++) {
        nextIndex = s[si] - ZEROCHAR;
        rootNow = rootNow->next[nextIndex];
        for (struct TrieNode *rootSuffix = rootNow;
             rootSuffix != root && rootSuffix != NULL &&
             rootSuffix->count != -1;
             rootSuffix = rootSuffix->suffix) {
            ans += rootSuffix->count;
            rootSuffix->count = -1;
        }
    }
    rootNow = root;
    for (int si = sn - 1; si > -1; si--) {
        nextIndex = s[si] - ZEROCHAR;
        rootNow = rootNow->next[nextIndex];
        for (struct TrieNode *rootSuffix = rootNow;
             rootSuffix != root && rootSuffix != NULL &&
             rootSuffix->count != -1;
             rootSuffix = rootSuffix->suffix) {
            ans += rootSuffix->count;
            rootSuffix->count = -1;
        }
    }
    return ans;
}

void trieNodeFree(struct TrieNode *root) {
    if (!root) {
        return;
    }
    for (int nexti = 0; nexti < NEXTLEN; nexti++) {
        if (root->next[nexti] != NULL && root == root->next[nexti]->parent) {
            trieNodeFree(root->next[nexti]);
            root->next[nexti] = NULL;
        }
    }
    free(root);
    root = NULL;
}

void expandInput() {
    int inputLen = strlen(input);
    char c = '\0';
    int times = 0;
    expandLen = 0;
    for (int inputIndex = 0; inputIndex < inputLen; inputIndex++) {
        c = input[inputIndex];
        if (c == '[' || c == ']') {
        } else if (c >= 'A' && c <= 'Z') {
            times = times == 0 ? 1 : times;
            while (times--) {
                expand[expandLen++] = c;
            }
            times = 0;
        } else if (c >= '0' && c <= '9') {
            times = times * 10 + c - '0';
        }
    }
    expand[expandLen] = '\0';
}

int main() {
    if (getenv("ZXWPC")) {
        freopen("p3987_next.in", "r", stdin);
        freopen("p3987_next.out", "w", stdout);
    }
    int testCase = 0;
    long long ans = 0;
    scanf("%d", &testCase);
    while (testCase--) {
        struct TrieNode *root = TrieNodeNew(NULL);
        int n;
        scanf("%d", &n);
        while (n--) {
            scanf("%s\n", input);
            trieNodeInsert(root, input, strlen(input));
        }
        trieNodeBuild(root);
        scanf("%s\n", input);
        expandInput();
        ans = trieNodeQuery(root, expand, expandLen);
        printf("%d\n", ans);
        trieNodeFree(root);
    }
    return 0;
}