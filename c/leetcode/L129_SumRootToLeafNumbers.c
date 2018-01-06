/*
    url: leetcode.com/problems/sum-root-to-leaf-numbers
    AC 3ms 0.00%
*/

#include <stdio.h>
#include <stdlib.h>

struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

void search(struct TreeNode* root, int * sum, int t) {
    int l = 0, r = 0;
    if (root == NULL) return;
    t = t*10+root->val;
    search(root->left, sum, t);
    search(root->right, sum, t);
    if (root->left == NULL && root->right == NULL) 
        *sum += t;
}

int sumNumbers(struct TreeNode* root) {
    int sum = 0;
    search(root, &sum, 0);
    return sum;
}