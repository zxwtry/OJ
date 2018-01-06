/*
    url: leetcode.com/problems/binary-tree-postorder-traversal
    AC 3ms 2.59%
*/

#include <iostream>
#include <vector>
#include <list>
#include <algorithm>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:
    vector<int> postorderTraversal(TreeNode* root) {
        list<TreeNode * > l;
        vector<int > ans;
        TreeNode * n = NULL;
        if (root == NULL) return ans;
        l.push_back(root);
        while (l.size() != 0) {
            n = l.front();
            l.pop_front();
            ans.push_back(n->val);
            if (n->left != NULL) l.push_front(n->left);
            if (n->right != NULL) l.push_front(n->right);            
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};

int main() {
    int a[] = {0, 1, 2, 3};
    vector<int > v;
    int i = 0, n = 4;
    for (; i < n; i ++) v.push_back(a[i]);
    //v.reserve(v.size());
    reverse(v.begin(), v.end());
    for (i = 0; i < v.size(); i ++)
        printf("%d ", v[i]);
    printf("\n");
    return 0;
}
