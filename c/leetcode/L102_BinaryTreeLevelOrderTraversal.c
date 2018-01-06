/*
    url: leetcode.com/problems/binary-tree-level-order-traversal
    AC 3ms 45.59%
*/

#include <stdio.h>
#include <stdlib.h>

struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

typedef struct TreeNode * ptn;
typedef struct TreeNode stn;

typedef int* T;
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
    int i = 0;
    for (i = 0; i < l->size; i ++)
        free(l->arr[i]);
    free(l->arr);
    free(l);
}

T* al_convert_to_array_free_l(pal l) {
    T* arr = l->arr;
    free(l);
    return arr;
}

typedef ptn V;
typedef struct sl ssl;
typedef struct sl * psl;

struct sl {
    int capacity;
    int size;
    V* arr;
};

psl sl_init(int capacity) {
    psl l = (psl) malloc(sizeof(ssl));
    if (capacity < 1) return NULL;
    l->arr = (V*) malloc(sizeof(V) * capacity);
    l->capacity = capacity;
    l->size = 0;
    return l;
}

void sl_expand_capacity(psl l) {
    V* new_arr = (V*) malloc(sizeof(V) * (l->capacity * 2 + 1));
    int i = 0;
    for (i = 0; i < l->capacity; i ++)
        new_arr[i] = l->arr[i];
    free(l->arr);
    l->arr = new_arr;
    l->capacity = l->capacity * 2 + 1;
}

void sl_add_last(psl l, V v) {
    if (l->capacity == l->size) sl_expand_capacity(l);
    l->arr[l->size] = v;
    l->size ++;
}

void sl_free_all(psl l) {
    free(l->arr);
    free(l);
}


void swap_psl(psl* l1, psl* l2) {
    psl l = *l1;
    *l1 = *l2;
    *l2 = l;
}

int** levelOrder(ptn n, int** cn, int* rn) {
    psl l1 , l2 ;
    int i = 0, i2 = 0, *t = NULL; 
    pal l = NULL, ln = NULL;
    if (n == NULL) return NULL;
    l1 = sl_init(16);
    l2 = sl_init(16);
    l = al_init(16);
    ln = al_init(16);
    sl_add_last(l1, n);
    while (l1->size != 0) {
        t = (int*) malloc(sizeof(int) * l1->size);
        for (i = 0; i < l1->size; i ++)
            t[i] = l1->arr[i]->val;
        al_add_last(l, t);
        t = (int*) malloc(sizeof(int) * 1);
        t[0] = l1->size;
        al_add_last(ln, t);
        l2->size = 0;
        for (i = 0; i < l1->size; i ++) {
            if (l1->arr[i]->left != NULL)
                sl_add_last(l2, l1->arr[i]->left);
            if (l1->arr[i]->right != NULL)
                sl_add_last(l2, l1->arr[i]->right);
        }
        swap_psl(&l1, &l2);
    }
    sl_free_all(l1);
    sl_free_all(l2);
    *cn = (int*) malloc(sizeof(int) * ln->size);
    for (i = 0; i < ln->size; i ++)
        (*cn)[i] = ln->arr[i][0];
    al_free_all(ln);
    *rn = l->size;
    return al_convert_to_array_free_l(l);
}