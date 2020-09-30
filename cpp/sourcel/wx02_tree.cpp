/*
1.在二叉排序树上面找出第N大的节点。(左边小,右边大)

struct node_t
{
    int value;
    struct node_t * left, * right;
};

node_t* findNthMax(node_t* root, int n) {

}

2、给定的二叉树，将其变换为源二叉树的镜像
二叉树的镜像定义：源二叉树
                8
               /  \
              6   10
             / \  / \
            5  7 9 11
            镜像二叉树
                8
               /  \
              10   6
             / \  / \
            11 9 7  5
*/
#include <algorithm>
#include <iostream>
#include <map>
#include <stack>
#include <stdio.h>
#include <string>
#include <vector>

struct node_t
{
    int value;
    struct node_t *left, *right;
};

node_t *findNthMaxRecur(node_t *root, int n, int &cnt)
{
    // printf("n[%d] cnt[%d]\n", n, cnt);
    if (NULL == root)
    {
        return NULL;
    }
    node_t *right = findNthMaxRecur(root->right, n, cnt);
    if (NULL != right)
    {
        return right;
    }
    cnt++;
    if (cnt == n)
    {
        return root;
    }
    node_t *left = findNthMaxRecur(root->left, n, cnt);
    if (NULL != left)
    {
        return left;
    }
    return NULL; //没有找到，可能是n过大
}

// 递归版本
node_t *findNthMax(node_t *root, int n)
{
    if (n <= 0)
    {
        return NULL;
    }
    if (NULL == root)
    {
        return root;
    }
    int cnt = 0;
    return findNthMaxRecur(root, n, cnt);
}

node_t *findNthMaxNoRecur(node_t *root, int n)
{
    if (n <= 0)
    {
        return NULL;
    }
    if (NULL == root)
    {
        return NULL;
    }
    int cnt = 0;
    std::stack<node_t *> stk;
    node_t *cur = root;
    while (cur != NULL || !stk.empty())
    {
        while (cur != NULL)
        {
            stk.push(cur);
            cur = cur->right;
        }
        if (!stk.empty())
        {
            cur = stk.top();
            cnt++;
            if (cnt == n)
            {
                return cur;
            }
            stk.pop();
            cur = cur->left;
        }
    }
    return NULL; //没有找到，可能是n过大
}

// 镜像
void treeMirror(node_t *root)
{
    if (NULL == root)
    {
        return;
    }
    // 叶子
    if (NULL == root->left && NULL == root->right)
    {
        return;
    }

    // 镜像
    node_t *treeNode = root->left;
    root->left = root->right;
    root->right = treeNode;

    if (NULL != root->left)
    {
        treeMirror(root->left);
    }
    if (NULL != root->right)
    {
        treeMirror(root->right);
    }
}

// 镜像（非递归）
void treeMirrorNoRecur(node_t *root)
{
    std::stack<node_t *> stk;
    if (NULL == root)
    {
        return;
    }
    stk.push(root);
    while (!stk.empty())
    {
        node_t *cur = stk.top();
        stk.pop();
        node_t *treeNode = cur->left;
        cur->left = cur->right;
        cur->right = treeNode;
        if (cur->left)
        {
            stk.push(cur->left);
        }
        if (cur->right)
        {
            stk.push(cur->right);
        }
    }
}

int main()
{
    node_t *root = new (node_t);
    root->value = 20;
    root->left = new (node_t);
    root->left->value = 10;
    root->left->left = NULL;
    root->left->right = NULL;
    root->right = new (node_t);
    root->right->value = 30;
    root->right->left = NULL;
    root->right->right = NULL;
    // root->left = NULL;
    // root->right = NULL;
    // root = NULL;

    for (int i = 0; i < 10; i++)
    {
        node_t *max0 = findNthMaxNoRecur(root, i);
        if (NULL == max0)
        {
            printf("i[%d] th max is null\n", i);
        }
        else
        {
            printf("i[%d] th max is %d\n", i, max0->value);
        }
    }

    treeMirrorNoRecur(root);

    if (NULL != root)
    {
        printf("root[%d]\n", root->value);
        if (NULL != root->left)
        {
            printf("left[%d]\n", root->left->value);
        }
        if (NULL != root->right)
        {
            printf("right[%d]\n", root->right->value);
        }
    }
}