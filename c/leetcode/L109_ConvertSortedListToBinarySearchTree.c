/*
*/

#include <stdio.h>
#include <stdlib.h>

struct ListNode {
    int val;
    struct ListNode *next;
};


struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

typedef struct ListNode sln;
typedef struct ListNode * pln;
typedef struct TreeNode stn;
typedef struct TreeNode * ptn;

ptn search(int* a, int ai, int aj) {
    int am = (ai + aj + 1) / 2;
    ptn n = NULL;
    if (ai > aj) return NULL;
    n = (ptn) malloc(sizeof(stn));
    n->left = search(a, ai, am-1);
    n->right = search(a, am+1, aj);
    n->val = a[am];
    return n;
}

ptn sortedListToBST(pln h) {
    int hn = 0, *a = NULL, i = 0;
    pln t = h;
    ptn ans = NULL;
    while (t != NULL) {
        t = t->next;
        hn ++;
    }
    a = (int*) malloc(sizeof(int) * hn);
    t = h;
    while (t != NULL) {
        a[i] = t->val;
        t = t->next;
        i ++;
    }
    ans = search(a, 0, hn-1);
    free(a);
    return ans;
}