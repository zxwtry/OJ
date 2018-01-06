/*
    url: leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
    AC 19ms 6.25%
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

ptn search(int* i, int ii, int ij, int* p, int pi, int pj) {
    int lt = 0, rt = 0;
    ptn n = NULL;
    if (ii > ij) return NULL;
    while (i[ii+lt] != p[pj]) lt ++;
    rt = ij - ii - lt;
    n = tn_init(p[pj]);
    n->left = search(i, ii, ii+lt-1, p, pi, pi+lt-1);
    n->right = search(i, ij-rt+1, ij, p, pj-rt, pj-1);
    return n;
}

ptn buildTree(int* i, int in, int* p, int pn) {
    return search(i, 0, in-1, p, 0, pn-1);
}