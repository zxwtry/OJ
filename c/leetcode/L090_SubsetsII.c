/*
    url: leetcode.com/problems/subsets-ii
    AC 3ms 70.59%
*/

#include <stdio.h>
#include <stdlib.h>

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

T* al_convert_to_array_free_l(pal l) {
    T* arr = l->arr;
    free(l);
    return arr;
}

void al_free(pal l) {
    int i = 0;
    for (i = 0; i < l->size; i ++)
        free(l->arr[i]);
    free(l->arr);
    free(l);
}

int partition(int* n, int i, int j) {
    int s = n[i];
    while (i < j) {
        while (i < j && n[j] >= s) j --;
        n[i] = n[j];
        while (i < j && n[i] <= s) i ++;
        n[j] = n[i];
    }
    n[i] = s;
    return i;
}

void quick_sort(int* n, int i, int j) {
    int p = 0;
    if (i+1 < j) {
        p = partition(n, i, j-1);
        quick_sort(n, i, p);
        quick_sort(n, p+1, j);
    }
}

int* arr_copy(int* s, int sn) {
    int* t = (int*) malloc(sizeof(int) * sn);
    int i = 0;
    for (i = 0; i < sn; i ++) {
        t[i] = s[i];
    }
    return t;
}

int* arr_int(int val) {
    int* t = (int*) malloc(sizeof(int));
    t[0] = val;
    return t;
}

void search(pal l, pal ln, int* n, int ni, int nn, int* s, int si, int sn, int sign) {
    int i = 0;
    if (si == sn && ni == nn) {
        al_add_last(l, arr_copy(s, sn));
        al_add_last(ln, arr_int(sn));
        return;
    }
    if (ni >= nn || si > sn) return;
    if (! (sign && ni != 0 && n[ni-1] == n[ni])) {
        search(l, ln, n, ni+1, nn, s, si, sn, 0);
    }
    if (si < sn) s[si] = n[ni];
    search(l, ln, n, ni+1, nn, s, si+1, sn, 1);
}

int** subsetsWithDup(int* n, int nn, int** cn, int* rn) {
    pal l = NULL, ln = NULL;
    int ni = 0, *s = NULL, si = 0;
    if (n == NULL || nn < 1) return NULL;
    quick_sort(n, 0, nn);
    l = al_init(16);
    ln = al_init(16);
    s = (int*) malloc(sizeof(int) * nn);
    for (ni = 0; ni <= nn; ni ++) {
        search(l, ln, n, 0, nn, s, 0, ni, 0);
    }
    *rn = l->size;
    *cn = (int*) malloc(sizeof(int) * *rn);
    for (si = 0; si < *rn; si ++)
        (*cn)[si] = ln->arr[si][0];
   al_free(ln);
   return al_convert_to_array_free_l(l);
}

int main() {
    int n[] = {1,4,3,5,4,4,7,7,8,0};
    int nn = 2;
    int rn = 0;
    int* cn = NULL;
    int** a = subsetsWithDup(n, nn, &cn, &rn);
    int i = 0, j = 0;
    for (i = 0; i < rn; i ++) {
        for (j = 0; j < cn[i]; j ++) {
            printf("%d ", a[i][j]);
        }
        printf("\r\n");
        free(a[i]);   
    }
    free(a);
}