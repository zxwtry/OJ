/*
    url: leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
    AC 13ms 14.29%
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
    ptn t = (ptn) malloc(sizeof(stn));
    t->val = val;
    t->left = NULL;
    t->right = NULL;
    return t;
}

ptn search(int* p, int pi, int pj, int* i, int ii, int ij) {
    int lt = 0, rt = 0;
    ptn n = NULL;
    if (pi > pj) return NULL;
    while(i[ii+lt] != p[pi]) lt ++;
    rt = pj-pi-lt;
    n = tn_init(p[pi]);
    n->left = search(p, pi+1, pi+lt, i, ii, ii+lt-1);
    n->right = search(p, pj-rt+1, pj, i, ij-rt+1, ij);
    return n;
}

ptn buildTree(int* p, int pn, int* i, int in) {
    return search(p, 0, pn-1, i, 0, in-1);
}