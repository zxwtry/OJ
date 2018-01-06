/*
    url: leetcode.com/problems/validate-binary-search-tree
*/

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

#define bool int

struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

long _min(long a, long b) {
    return a < b ? a : b;
}

long _max(long a, long b) {
    return a > b ? a : b;
}

bool search(struct TreeNode* root, long min, long max) {
    long v = 0;
    if (root == NULL) return 1;
    v = root->val;
    if (v < min || v > max) return 0;
    //printf("%ld %ld\n", min, max);
    return search(root->left, min, _min(max, v-1)) && 
        search(root->right, _max(min, v+1), max);
}

bool isValidBST(struct TreeNode* root) {
    return search(root, INT_MIN, INT_MAX);
}