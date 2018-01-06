/*
    url: leetcode.com/problems/recover-binary-search-tree
    both: AC 12ms 100.00%
*/

#include <stdio.h>
#include <stdlib.h>

typedef struct TreeNode stn;
typedef struct TreeNode * ptn;

struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

typedef ptn T;
typedef struct al sal;
typedef struct al * pal;

struct al {
    int capacity;
    int size;
    T* arr;
};

pal al_init(int capacity) {
    pal l = (pal) malloc(sizeof(sal));
    if (capacity < 1) return NULL;
    l->arr = (T*) malloc(sizeof(T) * capacity);
    l->capacity = capacity;
    l->size = 0;
    return l;
}

void al_expand_capacity(pal l) {
    T* new_arr = (T*) malloc(sizeof(T) * (l->capacity * 2 + 1));
    int i = 0;
    for (i = 0; i < l->capacity; i ++)
        new_arr[i] = l->arr[i];
    free(l->arr);
    l->arr = new_arr;
    l->capacity = l->capacity * 2 + 1;
}

void al_add_last(pal l, T v) {
    if (l->capacity == l->size) al_expand_capacity(l);
    l->arr[l->size] = v;
    l->size ++;
}

T* al_convert_to_array_free_l(pal l) {
    T* arr = l->arr;
    free(l);
    return arr;
}

void pre_order(pal l, ptn root) {
    if (root == NULL) return;
    pre_order(l, root->left);
    al_add_last(l, root);
    pre_order(l, root->right);
}

void swap(int* a, int* b) {
    int t = *a;
    *a = *b;
    *b = t;
}

void recoverTree_n(ptn root) {
    int i = 0, c = 0;
    pal l = al_init(16);
    int w1 = 0, w2 = 0;
    int size = 0;
    ptn * arr = NULL;
    pre_order(l, root);
    size = l->size;
    arr = al_convert_to_array_free_l(l);
    for (i = 1; i < size; i ++) {
        if (arr[i]->val < arr[i-1]->val) {
            if (c == 0) {
                w1 = i;
                c ++;
            } else if (c == 1) {
                w2 = i;
                c ++;
            }
        }
    }
    if (c == 2) {
        swap(&(arr[w1-1]->val), &(arr[w2]->val));
    }
    if (c == 1) {
        swap(&(arr[w1]->val), &(arr[w1-1]->val));
    }
}

void pre_order_1(ptn root, ptn* p, ptn* ps) {
    if (root == NULL) return;
    pre_order_1(root->left, p, ps);
    if (*p != NULL)
    if (root->val <= (*p)->val) {
        if (ps[0] == NULL) {
            ps[0] = *p;
        }
        ps[1] = root;
    }
    *p = root;
    pre_order_1(root->right, p, ps);
}

void recoverTree_1(ptn root) {
    ptn p = NULL;
    ptn ps[2] = {NULL, NULL};
    pre_order_1(root, &p, ps);
    swap(&(ps[0]->val), &(ps[1]->val));
}

void recoverTree(ptn root) {
    recoverTree_n(root);
}



