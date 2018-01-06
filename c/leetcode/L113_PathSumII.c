/*
    url: leetcode.com/problems/path-sum-ii
    AC 9ms 13.33% 
*/

#include <stdio.h>
#include <stdlib.h>

struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

typedef int bool;

typedef struct TreeNode stn;
typedef struct TreeNode * ptn;


ptn tn_init(int val) {
    ptn n = (ptn) malloc(sizeof(stn));
    n->val = val;
    n->left = NULL;
    n->right = NULL;
    return n;
}

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
    free(l->arr);
    free(l);
}

void swap_al(pal* l1, pal* l2) {
    pal t = *l1;
    *l1 = *l2;
    *l2 = t;
}

int* arr_copy(int* r, int ri) {
    int i = 0, *c = (int*) malloc(sizeof(int)*ri);
    for (i = 0; i < ri; i ++)
        c[i] = r[i];
    return c;

}

T* al_convert_to_array_free_l(pal l) {
    T* arr = l->arr;
    free(l);
    return arr;
}


int* arr_con(int val) {
    int *c = (int*) malloc(sizeof(int));
    c[0] =val;
    return c;
}

void search(ptn n, int c, int s, int* sign, int* r, int ri, pal l, pal ln) {
    if (*sign) return;
    if (n == NULL) return;
    r[ri] = n->val;
    search(n->left, c+n->val, s, sign, r, ri+1, l, ln);
    search(n->right, c+n->val, s, sign, r, ri+1, l, ln);
    if (n->left == NULL && n->right == NULL && c+n->val == s) {
        al_add_last(l, arr_copy(r, ri+1));
        al_add_last(ln, arr_con(ri+1));
    }
}

int** pathSum(struct TreeNode* n, int s, int** cn, int* rn) {
    int c = 0, sign = 0, i = 0;
    int* rec = (int*) malloc(sizeof(int) * 2048);
    pal l = al_init(16);
    pal ln = al_init(16);
    search(n, 0, s, &sign, rec, 0, l, ln);
    *cn = (int*) malloc(sizeof(int) * ln->size);
    for (i = 0; i < ln->size; i ++) {
        (*cn)[i] = ln->arr[i][0];
        free(ln->arr[i]);
    }
    free(ln->arr);
    free(ln);
    *rn = l->size;
    free(rec);
    return al_convert_to_array_free_l(l);
}