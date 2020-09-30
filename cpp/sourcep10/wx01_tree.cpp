#include <algorithm>
#include <iostream>
#include <map>
#include <stack>
#include <stdio.h>
#include <string>
#include <vector>
#include <queue>

using namespace std;

struct TreeNode
{
    int value;
    struct TreeNode *left;
    struct TreeNode *right;
};

// 一层一层找　
int countNodeLayer(TreeNode *root)
{
    int n = 0;
    queue<TreeNode *> q;
    TreeNode *t = root;
    if (NULL != t)
    {
        q.push(t);
    }
    while (!q.empty())
    {
        ++n;
        t = q.front();
        q.pop();
        if (t->left)
        {
            q.push(t->left);
        }
        if (t->right)
        {
            q.push(t->right);
        }
    }
    return n;
}

// 树深
int getDepth(TreeNode *r)
{
    int depth = 0;
    while (NULL != r)
    {
        depth++;
        r = r->left;
    }
    return depth;
}

//  分治迭代  有爆栈风险 由于是完全树，还好
int countNodeSplit(TreeNode *root)
{
    if (NULL == root)
        return 0;
    int ld = getDepth(root->left);
    int rd = getDepth(root->right);
    if (ld == rd)
    {
        return (1 << ld) + countNodeSplit(root->right);
    }
    else
    {
        return (1 << rd) + countNodeSplit(root->left);
    }
}

int main()
{
    TreeNode *root = new (TreeNode);
    root->value = 20;
    root->left = new (TreeNode);
    root->left->value = 10;
    root->left->left = NULL;
    root->left->right = NULL;
    root->right = new (TreeNode);
    root->right->value = 30;
    root->right->left = NULL;
    root->right->right = NULL;
    cout << countNodeLayer(root) << endl;
    cout << countNodeSplit(root) << endl;
}