#include <cstdlib>
#include <cstring>
#include <iostream>
#include <queue>

using namespace std;

const int MAXN = 10000000;
const int NEXTLEN = 26;
char s[MAXN];
int ansFlag[1000];

struct TrieNode {
    int flag;
    struct TrieNode *next[NEXTLEN];
    struct TrieNode *suffix;
    TrieNode() {
        flag = -1;
        memset(next, 0, sizeof(next));
        suffix = NULL;
    }
};

void buildTrie(struct TrieNode *root, char *s, int flag) {
    int si = 0;
    char c;
    int nextIndex;
    struct TrieNode *rootNow = root;
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
    rootNow->flag = flag;
}

void buildTrieGraph(struct TrieNode *root) {
    queue<struct TrieNode *> qt;
    //先处理root节点
    for (int nexti = 0; nexti < NEXTLEN; nexti++) {
        if (root->next[nexti]) {
            root->next[nexti]->suffix = root;
            qt.push(root->next[nexti]);
        } else {
            root->next[nexti] = root;
        }
    }

    // 处理 now 和 suffix
    struct TrieNode *rootNow;
    struct TrieNode *rootSuffix;
    while (!qt.empty()) {
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

int queryTrie(struct TrieNode *root, char *s) {
    int si = 0;
    int cnt = 0;
    char c;
    int nextIndex;
    struct TrieNode *rootNow = root;
    while (true) {
        c = s[si++];
        if (c == '\0') {
            return cnt;
        }
        int time;
        if (c == '[') {
            time = 0;
            int k = si;
            while (s[k] != ']') {
                if (s[k] >= '0' && s[k] <= '9')
                    time = time * 10 + s[k] - '0';
                k++;
            }
            si = k + 1;
            c = s[k - 1];

            // printf("time:%d c:%c\n", time, c);
        } else {
            time = 1;
        }
        while (time--) {
            nextIndex = c - 'A';
            if (!rootNow->next[nextIndex]) {
                return cnt;
            }
            rootNow = rootNow->next[nextIndex];
            if (rootNow->flag != -1) {
                ansFlag[rootNow->flag] = 1;
            }
        }
    }
    return cnt;
}

int queryTrieReverse(struct TrieNode *root, char *s) {
    int si = strlen(s) - 1;
    int cnt = 0;
    char c;
    int nextIndex;
    struct TrieNode *rootNow = root;
    while (true) {
        c = s[si--];
        if (c == '\0') {
            return cnt;
        }
        int time;
        if (c == ']') {
            time = 0;
            int k = si;
            while (s[k] != '[') {
                k--;
            }
            for (int t = k + 1; t < si; t++) {
                time = time * 10 + s[t] - '0';
            }
            c = s[si];
            si = k - 1;
        } else {
            time = 1;
        }
        printf("time:%d c:%c\n", time, c);
        while (time--) {
            nextIndex = c - 'A';
            if (!rootNow->next[nextIndex]) {
                return cnt;
            }
            rootNow = rootNow->next[nextIndex];
            if (rootNow->flag != -1) {
                ansFlag[rootNow->flag] = 1;
            }
        }
    }
    return cnt;
}

int main() {
    if (getenv("ZXWPC")) {
        freopen("p3987.in", "r", stdin);
        freopen("p3987.out", "w", stdout);
    }
    int testCase;
    scanf("%d\n", &testCase);
    while (testCase--) {
        struct TrieNode *root = new TrieNode();
        int n;
        int ans = 0;
        scanf("%d\n", &n);
        for (int ni = 0; ni < n; ni++) {
            scanf("%s\n", s);
            buildTrie(root, s, ni);
        }
        memset(ansFlag, 0, sizeof(ansFlag));
        buildTrieGraph(root);
        scanf("%s\n", s);
        queryTrie(root, s);
        queryTrieReverse(root, s);
        for (int ni = 0; ni < n; ni++) {
            if (ansFlag[ni] == 1) {
                ans++;
            }
        }
        printf("%d\n", ans);
    }
}