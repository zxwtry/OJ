/*
    url: leetcode.com/problems/same-tree
    AC 0ms 57.14%
*/


struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

bool isSameTree(struct TreeNode* p, struct TreeNode* q) {
    if (p == NULL || q == NULL)
        return p == NULL && q == NULL;
    if (p->val != q->val) return 0;
    return isSameTree(p->left, q->left) && 
        isSameTree(p->right, q->right);
}