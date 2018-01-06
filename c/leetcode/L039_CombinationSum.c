/*
    url: leetcode.com/problems/combination-sum/
    AC 9ms 58.82%
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
    int* rs;
    T* arr;
};

void al_expand_capacity(pal l) {
    T* new_arr = (T*) malloc(sizeof(T) * (l->capacity * 2 + 1));
    int* new_rs = (int*) malloc(sizeof(int) * (l->capacity * 2 + 1));
    int i = 0;
    for (i = 0; i < l->capacity; i ++) {
        new_arr[i] = l->arr[i];
        new_rs[i] = l->rs[i];
    }
    free(l->arr);
    free(l->rs);
    l->arr = new_arr;
    l->rs = new_rs;
    l->capacity = l->capacity * 2 + 1;
}

void al_add_last(pal l, T v, int s) {
    if (l->capacity == l->size) al_expand_capacity(l);
    l->arr[l->size] = v;
    l->rs[l->size] = s;
    l->size ++;
}
//array list end

//search start

void search(pal l, int* c, int ci, int cs, int t, int* r, int ri) {
    int i = 0, *tmp1, times;
    if (t == 0) {
        tmp1 = (int*) malloc(sizeof(int) * ri);
        for (i = 0; i < ri; i ++)
            tmp1[i] = r[i];
        al_add_last(l, tmp1, ri);
        return;
    }
    if (t < 0 || ci == cs) return;
    for (times = 0; times <= t / c[ci]; times ++) {
        for (i = 0; i < times; i ++) {
            r[ri + i] = c[ci];
        }
        search(l, c, ci + 1, cs, t - times * c[ci], r, ri + times);
    }
}
//search end

int _min(int a, int b) {
    return a < b ? a : b;
}

//use array list as answer
int** combinationSum(int* c, int cs, int t, int** rcs, int* rs) {
    pal l = (pal) malloc(sizeof(sal));
    int* r = NULL, **ans = NULL, i = 0, mc = cs <= 0 ? 1 : c[0];
    if (cs <= 0) return NULL;
    l->capacity = 16;
    l->arr = (T*) malloc(sizeof(T) * l->capacity);
    l->rs = (int*) malloc(sizeof(int) * l->capacity);
    l->size = 0;
    for (i = 1; i < cs; i ++)
        mc = _min(mc, c[i]);
    r = (int*) malloc(sizeof(int) * (t / mc + 10));
    search(l, c, 0, cs, t, r, 0);
    (*rcs) = l->rs;
    (*rs) = l->size;
    ans = l->arr;
    free(l);
    return ans;
}

int main() {    
    int c[] = {2,3,7};
    int cs = 3;
    int t = 7;
    int* rcs = NULL;
    int rs = 0;
    int** answer = combinationSum(c, cs, t, &rcs, &rs);
    int i = 0, j = 0;
    printf("rs is %d  \r\n", rs);
    for (i = 0; i < rs; i ++) {
        for (j = 0; j < rcs[i]; j ++) {
            printf("%d ", answer[i][j]);
        }
        printf("\r\n");
        free(answer[i]);
    }
    free(answer);
    free(rcs);
}