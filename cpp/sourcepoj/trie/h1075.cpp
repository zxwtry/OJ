#include <bits/stdc++.h>
using namespace std;

// AC  577MS   120372K
const int NEXT_LEN = 26;

struct TrieNode {
    char *replace;
    int replaceLen;
    struct TrieNode *next[NEXT_LEN];
    TrieNode() {
        replace = NULL;
        replaceLen = 0;
        memset(next, 0, sizeof(next));
    }
};

void buildTrie(struct TrieNode *root, char *find, char *replace) {
    int findLen = strlen(find);
    int replaceLen = strlen(replace);
    struct TrieNode *rootNow = root;
    for (int findIndex = 0; findIndex < findLen; findIndex++) {
        int nextIndex = find[findIndex] - 'a';
        if (!rootNow->next[nextIndex]) {
            rootNow->next[nextIndex] = new TrieNode();
        }
        rootNow = rootNow->next[nextIndex];
    }
    rootNow->replace = (char *)malloc(sizeof(char) * (replaceLen + 1));
    strcpy_s(rootNow->replace, replaceLen + 1, replace);
}

void queryTrie(struct TrieNode *root, char *find) {
    int findLen = strlen(find);
    struct TrieNode *rootNow = root;
    for (int findIndex = 0; findIndex < findLen; findIndex++) {
        int nextIndex = find[findIndex] - 'a';
        if (rootNow->next[nextIndex]) {
            rootNow = rootNow->next[nextIndex];
        } else {
            printf("%s", find);
            return;
        }
    }
    if (rootNow->replace != NULL) {
        printf("%s", rootNow->replace);
    } else {
        printf("%s", find);
    }
}

bool checkCharNormal(char c) {
    if (c >= 'a' && c <= 'z') {
        return true;
    }
    return false;
}

const int LEN = 10000;
char findStr[LEN];
char replaceStr[LEN];
char END[] = "END";

int main() {
    if (getenv("ZXWPC")) {
        freopen("h1075.in", "r", stdin);
        freopen("h1075.out", "w", stdout);
    }
    struct TrieNode *root = new TrieNode();
    scanf("%s\n", findStr);
    while (true) {
        scanf("%s %s\n", replaceStr, findStr);
        if (strcmp(replaceStr, END) == 0) {
            break;
        }
        buildTrie(root, findStr, replaceStr);
    }
    int findLen;
    while (true) {
        cin.getline(findStr, LEN, '\n');
        if (strcmp(findStr, END) == 0) {
            break;
        }
        // 这里有了一行一行数据
        int findIndex = 0;
        int replaceIndex = 0;
        char c;
        while (true) {
            c = findStr[findIndex++];
            if (c == '\0') {
                break;
            }
            if (!checkCharNormal(c)) {
                replaceStr[replaceIndex++] = '\0';
                queryTrie(root, replaceStr);
                if (c != '\n')
                    printf("%c", c);
                replaceIndex = 0;
                continue;
            }
            replaceStr[replaceIndex++] = c;
        }
        replaceStr[replaceIndex++] = '\0';
        queryTrie(root, replaceStr);
        printf("\n");
    }
}
