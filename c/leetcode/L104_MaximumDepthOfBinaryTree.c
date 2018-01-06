/*
    url: leetcode.com/problems/maximum-depth-of-binary-tree
    AC 3ms 75.91%
*/


struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

int _max(int a, int b) {
    return a < b ? b : a;
}

void search(struct TreeNode* root, int cnt, int* ans) {
    if (root == NULL) {
        *ans = _max(cnt, *ans);
    } else {
        search(root->left, cnt+1, ans);
        search(root->right, cnt+1, ans);
    }
}

int maxDepth(struct TreeNode* root) {
    int ans = 0;
    search(root, 0, &ans);
    return ans;
}