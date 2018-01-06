/*
    url: leetcode.com/problems/binary-tree-maximum-path-sum
    AC 15ms 59.26%
*/

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

typedef struct TreeNode stn;
typedef struct TreeNode * ptn;

int _max(int a, int b) {
    return a < b ? b : a;
}

int search(ptn n, int* m) {
    int lv = 0, rv = 0;
    if (n == NULL) return 0;
    lv = search(n->left, m);
    rv = search(n->right, m);
    *m = _max(*m, lv + rv + n->val);
    return _max(_max(_max(lv, rv), 0) + n->val, 0);
}

int maxPathSum(ptn n) {
    int m = INT_MIN;
    if (n == NULL) return 0;
    search(n, &m);    
    return m;
}