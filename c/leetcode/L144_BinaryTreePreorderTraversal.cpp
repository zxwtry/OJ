/*
    url: leetcode.com/problems/binary-tree-preorder-traversal
    AC 3ms 2.43%
*/

#include <iostream>
#include <vector>
#include <list>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:
    vector<int > preorderTraversal(TreeNode* root) {
        vector<int > ans;
        TreeNode * n = NULL;
        if (root == 0) return ans;
        list<TreeNode * > l;
        l.push_front(root);
        while (l.size() != 0) {
            n = l.front();
            l.pop_front();
            ans.push_back(n->val);
            if (n->right != NULL) l.push_front(n->right);
            if (n->left != NULL) l.push_front(n->left);
        }
        return ans;
    }
};
