/*
    url: leetcode.com/problems/multiply-strings/
    AC 6ms 57.50%
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//linked list start

typedef char T;
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

//linked list end

char* multiply(char* n1, char* n2) {
    int n1n = strlen(n1), n2n = strlen(n2);
    int* rec = (int*) malloc(sizeof(int) * (n1n + n2n - 1));
    int i = 0, i1 = 0, i2 = 0, val = 0;
    pll l = (pll) malloc(sizeof(sll));
    l->first = NULL;
    l->last = NULL;
    l->size = 0;
    for (i = 0; i < n1n + n2n - 1; i ++)
        rec[i] = 0;
    for (i1 = 0; i1 < n1n; i1 ++) {
        for (i2 = 0; i2 < n2n; i2 ++) {
            rec[i1 + i2] += (*(n1 + i1) - '0') * (*(n2 + i2) - '0');
        }
    }
    for (val = 0, i = n1n + n2n - 2; i > -1; i --) {
        val += rec[i];
        ll_add_first(l, (char)('0' + val % 10));
        val = val / 10;
    }
    while (val != 0) {
        ll_add_first(l, (char)('0' + val % 10));
        val = val / 10;
    }
    while (l->first != l->last && l->first->val == '0')
        ll_remove_first(l);
    ll_add_last(l, '\0');
    free(rec);
    return ll_convert_to_array_free_l(l);
}

int main() {
    char* n1 = "99999";
    char* n2 = "0";
    char* ans = multiply(n1, n2);
    printf("answer is %s\r\n", ans);
    free(ans);
}