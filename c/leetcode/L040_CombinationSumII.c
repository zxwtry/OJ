/*
	url: leetcode.com/problems/combination-sum-ii/
    AC 12ms 27.27%
*/

#include <stdio.h>
#include <stdlib.h>

//array list start

typedef int* T;
typedef struct al sal;
typedef struct al * pal;

struct al {
    int capacity;
    int size;
    T* arr;
};

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

void al_add_first(pal l, T v) {
    int i = 0;
    if (l->capacity == l->size) al_expand_capacity(l);
    for (i = l->size; i > 0; i --)
        l->arr[i] = l->arr[i - 1];
    l->arr[0] = v;
    l->size ++;
}

void al_add_to_index(pal l, T v, int index) {
    int i = 0;
    if (index > l->size) return;
    if (l->capacity == l->size) al_expand_capacity(l);
    for (i = l->size - 1; i >= index; i --) {
        l->arr[i+1] = l->arr[i];
    }
    l->arr[index] = v;
    l->size ++;
}

//if T is ptr, need to free l->size - 1
void al_remove_last(pal l) {
    if (l->size == 0) return;
    l->arr[l->size - 1] = 0; //or NULL and free
    l->size --;
}

//if T is ptr, need to free 0
void al_remove_first(pal l) {
    int i = 0;
    if (l->size == 0) return;
    l->arr[0] = 0; //or NULL and free
    for (i = 1; i < l->size; i ++) {
        l->arr[i - 1] = l->arr[i];
    }
    l->size --;
}

T* al_convert_to_array_free_l(pal l) {
    T* arr = l->arr;
    free(l);
    return arr;
}

T al_access_by_index(pal l, int index) {
    if (index >= l->size || index < 0) return 0;
    return l->arr[index];
}

void al_free_all(pal l) {
    free(l->arr);
    free(l);
}

void al_print(pal l) {
    int i = 0;
    if (l->size == 0) return;
    for (i = 0; i < l->size; i ++)
        printf("%d ", l->arr[i]);
    printf("\r\n");
}

//array list end


//quick srot start

//[l, r]
int _partition(int* n, int l, int r) {
    int s = *(n + l);
    while (l < r) {
        while (l < r && *(n + r) >= s) r --;
        *(n + l) = *(n + r);
        while (l < r && *(n + l) <= s) l ++;
        *(n + r) = *(n + l);
    }
    *(n + l) = s;
    return l;
}

//[l, r)
void _quick_sort(int* n, int l, int r) {
    int p = 0;
    if (l < r) {
        p = _partition(n, l, r - 1);
        _quick_sort(n, l, p);
        _quick_sort(n, p + 1, r);
    }
}

void quick_sort(int* n, int s) {
    _quick_sort(n, 0, s);
}

//quick sort end

void search(pal l, int* c, int ci, int cn, int** rn, int t, int sign, int* s, int si) {
    int* temp = NULL, i = 0;
    if (t == 0 && ci == cn) {
        temp = (int*) malloc(sizeof(int) * si);
        for (i = 0; i < si; i ++)
            temp[i] = s[i];
        (*rn)[l->size] = si;
        al_add_last(l, temp);
        return;
    }
    if (t < 0 || ci == cn) return;
    // sign = 1 and c[ci - 1] == c[ci] : not
    if (!(sign && c[ci - 1] == c[ci])) {
        search(l, c, ci + 1, cn, rn, t, 0, s, si);
    }
    s[si] = c[ci];
    search(l, c, ci+1, cn, rn, t-c[ci], 1, s, si+1);
}

int** combinationSum2(int* c, int cn, int t, int** rn, int* r) {
    int* s = (int*) malloc(sizeof(int) * cn);
    pal l = (pal) malloc(sizeof(sal));
    l->size = 0;
    l->capacity = 16;
    l->arr = (T*) malloc(sizeof(T) * l->capacity);
    quick_sort(c, cn);
    *rn = (int*) malloc(sizeof(int) * (cn*cn));
    search(l, c, 0, cn, rn, t, 0, s, 0);
    *r = l->size;
    free(s);
    return al_convert_to_array_free_l(l);
}

int main() {
    int c[] = {1, 1};
    int cn = 2; 
    int t = 1;
    int* rn = NULL;
    int r = 0;
    int** ans = combinationSum2(c, cn, t, &rn, &r);
    int i = 0, j = 0;
    printf("r is %d\r\n", r);
    for (i = 0; i < r; i ++) {
        for (j = 0; j < rn[i]; j ++)
            printf("%d ", ans[i][j]);
        printf("\r\n");
        free(ans[i]);
    }
    free(ans);
    return 0;
}



