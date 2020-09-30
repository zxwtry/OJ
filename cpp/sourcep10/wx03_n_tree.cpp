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
    int val;
    vector<TreeNode *> childs;
};

void dfs(TreeNode *root, vector<int> &ans)
{
    if (NULL == root)
    {
        return;
    }
    ans.push_back(root->val);
    for (int i = 0; i < root->childs.size(); i++)
    {
        dfs(root->childs[i], ans);
    }
};

vector<int> preorder(TreeNode *root)
{
    vector<int> ans;
    dfs(root, ans);
    return ans;
}

int main()
{
    const int N = 5;
    TreeNode *root = new (TreeNode);

    int a[N] = {101, 102, 103, 104, 105};
    TreeNode *tn[N];
    for (int i = 0; i < N; i++)
    {
        tn[i] = new (TreeNode);
        tn[i]->val = a[i];
        root->childs.push_back(tn[i]);
    }

    vector<int> ans = preorder(root);
    for (int i = 0; i < ans.size(); i++)
    {
        cout << i << "\t" << ans[i] << endl;
    }
}