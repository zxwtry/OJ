/*
    url: leetcode.com/problems/permutations-ii/
    AC 13ms 72.22%
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

//quick sort start

//[i, j)
int partition(int* n, int i, int j) {
    int s = n[i];
    j --;
    while (i < j) {
        while (i < j && n[j] >= s) j --;
        n[i] = n[j];
        while (i < j && n[i] <= s) i ++;
        n[j] = n[i];
    }
    n[i] = s;
    return i;
}

//[i, j)
void quick_sort(int* n, int i, int j) {
    int p = 0;
    if (i + 1 < j) {
        p = partition(n, i, j);
        quick_sort(n, i, p);
        quick_sort(n, p+1, j);
    }
}

//quick sort end

void swap(int* a, int* b) {
    int t = *a;
    *a = *b;
    *b = t;
}

int isDuplicate(int* n, int ni, int nj) {
    int i = 0;
    for (i = ni; i < nj; i ++) {
        if (n[i] == n[nj])
            return 1;
    }
    return 0;
}

int* arr_copy_of(int* n, int ns) {
    int* ans = (int*) malloc(sizeof(int) * ns);
    int i = 0;
    for (i = 0; i < ns; i ++)
        ans[i] = n[i];
    return ans;
}

void search(pal l, int* n, int ni, int ns) {
    int i = 0;
    if (ni == ns) {
        al_add_last(l, arr_copy_of(n, ns));
        return;
    }
    for (i = ni; i < ns; i ++) {
        if (isDuplicate(n, ni, i)) continue;
        swap(n+i, n+ni);
        search(l, n, ni+1, ns);
        swap(n+i, n+ni);
    }
}

int** permuteUnique(int* n, int ns, int* rs) {
    pal l = (pal) malloc(sizeof(sal));
    l->size = 0;
    l->capacity = 16;
    l->arr = (T*) malloc(sizeof(T) * l->capacity);
    //sort
    quick_sort(n, 0, ns);
    search(l, n, 0, ns);
    *rs = l->size;
    return al_convert_to_array_free_l(l);
}

int main() {
    int n[] = {1, 1, 3};
    int s = 3;
    int rs = 0;
    int** ans = permuteUnique(n, s, &rs);
    int i = 0, j = 0;
    for (i = 0; i < rs; i ++) {
        for (j = 0; j < s; j ++)
            printf("%d ", ans[i][j]);
        printf("\r\n");
        free(*(ans+i));
    }
    free(ans);
    return 0;
}