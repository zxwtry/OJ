/*
    url: leetcode.com/problems/flatten-binary-tree-to-linked-list
    search: AC 6ms 12.50%
    solve:  AC 3ms 42.50%
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

void search(ptn root, ptn* pre) {
    if (root == NULL) return;
    search(root->right, pre);
    search(root->left, pre);
    root->right = *pre;
    *pre = root;
    root->left = NULL;
}

void solve(ptn root) {
    ptn n = root, p = NULL;
    while (n != NULL) {
        if (n->left != NULL) {
            p = n->left;
            while (p->right != NULL)
                p = p->right;
            p->right = n->right;
            n->right = n->left;
            n->left = NULL;
        }
        n = n->right;
    }
}


void flatten(ptn root) {
    //ptn pre = NULL;
    //search(root, &pre);
    solve(root);
}