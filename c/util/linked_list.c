/*
    linked list
*/

#include <stdio.h>
#include <stdlib.h>

typedef int T;
typedef struct ln sln;
typedef struct ln * pln;
typedef struct ll sll;
typedef struct ll * pll;

//list node
struct ln {
    T val;
    pln nxt;
};

//linked list
struct ll {
    pln first;
    pln last;
    int size;
};

void ll_add_first(pll l, T v) {
    pln t = (pln) malloc(sizeof(sln));
    t->val = v;
    t->nxt = NULL;
    if (l->size == 0) {
        l->first = t;
        l->last = t;
        l->size = 1;
        return;
    }
    t->nxt = l->first;
    l->first = t;
    l->size ++;
}

void ll_add_last(pll l, T v) {
    pln t = (pln) malloc(sizeof(sln));
    t->val = v;
    t->nxt = NULL;
    if (l->size == 0) {
        l->first = t;
        l->last = t;
        l->size = 1;
        return;
    }
    l->last->nxt = t;
    l->last = t;
    l->size ++;
}

void ll_remove_first(pll l) {
    pln t = NULL;
    if (l->first == NULL) return;
    if (l->first == l->last) {
        free(l->first);
        l->first = NULL;
        l->last = NULL;
        l->size = 0;
        return;
    }
    t = l->first->nxt;
    free(l->first);
    l->first = t;
    l->size --;
}

void ll_construct_from_array(pll l, T* arr, int arr_size) {
    int i = 0;
    for (i = 0; i < arr_size; i ++) {
        ll_add_last(l, *(arr + i));
    }
}

T* ll_convert_to_array_free_l(pll l) {
    T* arr = NULL;
    int arr_index = 0;
    pln travel = NULL;
    pln t1 = NULL, t2 = NULL;
    if (l == NULL || l->size == 0) {
        if (l != NULL) free(l);
        return NULL;
    }
    arr = (T*) malloc(sizeof(T) * l->size);
    travel = l->first;
    while (travel != NULL) {
        *(arr + arr_index ++) = travel->val;
        travel = travel->nxt;
    }
    t1 = l->first;
    while (t1 != NULL) {
        t2 = t1->nxt;
        free(t1);
        t1 = t2;
    }
    free(l);
    return arr;
}

void ll_print(pll l) {
    pln t = l == NULL ? NULL : l->first;
    while (t != NULL) {
        printf("%d ", t->val);
        t = t->nxt;
    }
    printf("\r\n");
}

void ll_free_all(pll l) {
    pln t1 = l->first, t2 = NULL;
    while (t1 != NULL) {
        t2 = t1->nxt;
        free(t1);
        t1 = t2;
    }
    free(l);
}

int main() {
    pll l = (pll) malloc(sizeof(sll));
    int arr[] = {1, 2, 3, 4};
    pll new_l = NULL;
    int* val = NULL;
    l->first = NULL;
    l->last = NULL;
    l->size = 0;

    ll_add_first(l, 1);
    ll_add_last(l, 101);
    ll_add_first(l, 2);
    ll_remove_first(l);
    ll_add_first(l, 3);
    ll_add_last(l, 104);
    ll_add_first(l, 4);

    ll_print(l);

    ll_free_all(l);

    l = (pll) malloc(sizeof(sll));
    l->first = NULL;
    l->last = NULL;
    l->size = 0;

    ll_construct_from_array(l, arr, 4);

    ll_print(l);

    val = ll_convert_to_array_free_l(l);
  
    printf("%d %d %d %d\r\n", val[0], val[1], val[2], val[3]);

    free(val);
    
}
