#include <bits/stdc++.h>
using namespace std;


struct TN {
    int i;
    int n[20];
};

struct TrieNode {
    struct TrieNode * nodes[26];
    int count;
};

struct TrieNode * root;
int n;
char s[100008];

void buildTrie();
void queryTrie();
struct TrieNode * newTrie();
void freeTrie(struct TrieNode *);

int main() {
    if (getenv("ZXWPC")) {
        freopen("w002.in", "r", stdin);
        freopen("w002.out", "w", stdout);
    }
    root = newTrie();

    scanf("%d", &n);
    while (n --) {
        scanf("%s\n", &s);
        buildTrie();
    }

    scanf("%d", &n);
    while (n --) {
        scanf("%s\n", &s);
        queryTrie();
    }

    freeTrie(root);
}

struct TrieNode * newTrie() {
    struct TrieNode * trie = (struct TrieNode *) malloc(sizeof(struct TrieNode));
    trie->count = 0;
    for (int i = 0; i < 26; i ++) {
        trie->nodes[i] = NULL;
    }
    return trie;
}

void freeTrie(struct TrieNode * rootNow) {
    if (rootNow == NULL) {
        return;
    }
    for (int i = 0; i < 26; i ++) {
        freeTrie(rootNow->nodes[i]);
    }
    free(rootNow);
}


void buildTrie() {
    struct TrieNode * rootNow = root;
    for (int i = 0; s[i]; i ++) {
        int nodeIndex = (int)(s[i] - 'a');
        if (rootNow->nodes[nodeIndex] == NULL) {
            rootNow->nodes[nodeIndex] = newTrie();
        }
        rootNow = rootNow->nodes[nodeIndex];
        rootNow->count ++;
    }
}

void queryTrie() {
    struct TrieNode * rootNow = root;
    for (int i = 0; s[i]; i ++) {
        int nodeIndex = (int)(s[i] - 'a');
        rootNow = rootNow->nodes[nodeIndex];
        if (rootNow == NULL) {
            printf("0\n");
            return;
        }
    }
    printf("%d\n", rootNow->count);
}