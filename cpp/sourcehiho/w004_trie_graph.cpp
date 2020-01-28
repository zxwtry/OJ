#include <bits/stdc++.h>
using namespace std;

struct Trie {
    int flag;
    struct Trie *next[26];
    struct Trie *suffix;
    Trie() {
        flag = 0;
        memset(next, NULL, sizeof(next));
        suffix = NULL;
    }
};

int main() {}