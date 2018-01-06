/*
    test url: hihocoder.com/problemset/solution/1004077
    840ms 72MB
*/

#include <stdio.h>
#include <stdlib.h>

#define MSIZE 26

struct Trie {
    int c;
    struct Trie *m[MSIZE];
};

struct Trie* malloc_trie() {
    int i = 0;
    struct Trie *root = (struct Trie *) malloc(sizeof(struct Trie));
    root->c = 0;
    for (i = 0; i < MSIZE; i ++) {
        root->m[i] = NULL;
    }
    return root;
}

void free_trie(struct Trie *root) {
    int i = 0;
    for (i = 0; i < MSIZE; i ++) {
        if (root->m[i]) {
            free_trie(root->m[i]);
        }
    }
    free(root);
}

void solve() {
    int n = 0, i = 0, ans = 0;
    char c = '\0';
    struct Trie *root = malloc_trie();
    struct Trie *root_now = root;
    scanf("%d\n", &n);
    for (i = 0; i < n; i ++) {
        root_now = root;
        while (1) {
            scanf("%c", &c);
            if (c == '\n') break;
            if (root_now->m[c - 'a'] == NULL)
                root_now->m[c - 'a'] = malloc_trie();
            root_now = root_now->m[c - 'a'];
            root_now->c ++;
        }
    }
    scanf("%d\n", &n);
    for (i = 0; i < n; i ++) {
        root_now = root;
        ans = -1;
        while (1) {
            scanf("%c", &c);
            if (c == '\n') break;
            if (ans != 0) {
                if (root_now->m[c - 'a'] == NULL) {
                    ans=0;
                } else { 
                    root_now = root_now->m[c - 'a'];
                    ans = root_now->c;
                }
            }
        }
        printf("%d\n", ans);
    }
    free_trie(root);
}

int main() {
    solve();
    return 0;
}

