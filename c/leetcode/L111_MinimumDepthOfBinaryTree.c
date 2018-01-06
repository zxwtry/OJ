/*
    url: leetcode.com/problems/minimum-depth-of-binary-tree
    AC 6ms 28.17%
*/

#include <stdio.h>
#include <stdlib.h>

struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

typedef struct TreeNode stn;
typedef struct TreeNode * ptn;

ptn tn_init(int val) {
    ptn n = (ptn) malloc(sizeof(stn));
    n->val = val;
    n->left = NULL;
    n->right = NULL;
    return n;
}

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

void al_free_all(pal l) {
    free(l->arr);
    free(l);
}

void swap_al(pal* l1, pal* l2) {
    pal t = *l1;
    *l1 = *l2;
    *l2 = t;
}

int minDepth(ptn n) {
    pal l1 = NULL, l2 = NULL;
    int cnt = 0, i = 0, sign = 0;
    ptn tn = NULL;
    if (n == NULL) return 0;
    l1 = al_init(16);
    l2 = al_init(16);
    al_add_last(l1, n);
    while (l1->size != 0) {
        l2->size = 0;
        cnt ++;
        sign = 1;
        for (i = l1->size-1; sign && i >-1; i --) {
            tn = l1->arr[i];
            sign = !(tn->left == NULL && tn->right == NULL);
            if (tn->left != NULL) al_add_last(l2, tn->left);
            if (tn->right != NULL) al_add_last(l2, tn->right);
        }
        if (! sign) break;
        swap_al(&l1, &l2);
    }
    al_free_all(l1);
    al_free_all(l2);
    return cnt;
}