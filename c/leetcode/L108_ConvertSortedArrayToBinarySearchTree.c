/*
    url: leetcode.com/problems/convert-sorted-array-to-binary-search-tree
    AC 6ms 36.54%
*/

#include <stdio.h>
#include <stdlib.h>

struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

typedef struct TreeNode stn;
typedef struct TreeNode * ptn;

ptn tn_init(int val) {
    ptn n = (ptn) malloc(sizeof(stn));
    n->left = NULL;
    n->right = NULL;
    n->val = val;
    return n;
}

ptn search(int* n, int ni, int nj) {
    ptn root = NULL;
    int nm = (ni+nj)/2;
    if (ni > nj) return NULL;
    root = tn_init(n[nm]);
    root->left = search(n, ni, nm-1);
    root->right = search(n, nm+1, nj);
    return root;
}

ptn sortedArrayToBST(int* n, int nn) {
    return search(n, 0, nn-1);
}