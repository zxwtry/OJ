/*
1.在二叉排序树上面找出第N大的节点。(左边小,右边大)

struct node_t
{
    int value;
    struct node_t * left, * right;
};

node_t* findNthMax(node_t* root, int n) {

}
*/
#include <algorithm>
#include <iostream>
#include <map>
#include <stdio.h>
#include <string>
#include <vector>

struct node_t {
    int value;
    struct node_t *left, *right;
};

node_t *findNthMaxRecur(node_t *root, int n, int &cnt) {
    if (NULL == root) {
        return NULL;
    }
    node_t *right = findNthMaxRecur(root->right, n, cnt);
    if (NULL != right) {
        return right;
    }
    cnt++;
    if (cnt == n) {
        return root;
    }
    node_t *left = findNthMaxRecur(root->left, n, cnt);
    if (NULL != left) {
        return left;
    }
    return NULL; //没有找到，可能是n过大
}

// 递归版本
node_t *findNthMax(node_t *root, int n) {
    if (n < 0) {
        return NULL;
    }
    if (1 == n) {
        return root;
    }
    if (NULL == root) {
        return root;
    }
    int cnt = 0;
    return findNthMaxRecur(root, n, cnt);
}

int main() { node_t *root = new (node_t); }