/*
    url: leetcode.com/problems/largest-rectangle-in-histogram
    largestRectangleArea  AC 9ms 29.41%
    largestRectangleArea2 AC 6ms 41.18%
*/

#include <stdio.h>
#include <stdlib.h>

//element type
typedef int T;
typedef struct dll sdll;
typedef struct dll * pdll;
typedef struct dln sdln;
typedef struct dln * pdln;


//doubly list node
struct dln {
    T val;
    pdln pre;
    pdln nxt;
};

//doubly linked list
struct dll {
    pdln first;
    pdln last;
    int size;
};

pdll dll_init() {
    pdll l = (pdll) malloc(sizeof(sdll));
    l->first = NULL;
    l->last = NULL;
    l->size = 0;
    return l;
}

void dll_add_last(pdll l, T v) {
    pdln t = (pdln) malloc(sizeof(sdln));
    t->val = v;
    t->pre = NULL;
    t->nxt = NULL;
    if (l->size == 0) {
        l->first = t;
        l->last = t;
        l->size = 1;
        return;
    }
    t->pre = l->last;
    l->last->nxt = t;
    l->last = t;
    l->size ++;
}

void dll_remove_last(pdll l) {
    pdln t = NULL;
    if (l->last == NULL) return;
    if (l->first == l->last) {
        free(l->first);
        l->first = NULL;
        l->last = NULL;
        l->size = 0;
        return;
    }
    t = l->last->pre;
    t->nxt = NULL;
    free(l->last);
    l->last = t;
    l->size --;
}

void dll_print(pdll l) {
    pdln t = l == NULL ? NULL : l->first;
    while (t != NULL) {
        printf("%d ", t->val);
        t = t->nxt;
    }
    printf("\r\n");
}

void dll_free_all(pdll l) {
    pdln t1 = l->first, t2 = NULL;
    while (t1 != NULL) {
        t2 = t1->nxt;
        free(t1);
        t1 = t2;
    }
    free(l);
}

int _max(int a, int b) {
    return a < b ? b : a;
}

int largestRectangleArea(int* h, int hn) {
    pdll l = dll_init();
    int i = 0, v = 0, a = 0;
    for (i = 0; i <= hn; i ++) {
        v = i == hn ? 0 : h[i];
        if (l->size == 0 || v >= h[l->last->val]) {
            dll_add_last(l, i);
        } else {
            v = l->last->val;
            dll_remove_last(l);
            a = _max(a, h[v] * (l->size == 0 ? i : i - 1 - l->last->val));
            i --;
        }
    }
    return a;
}

int largestRectangleArea2(int* h, int hn) {
    int* m = (int*) malloc(sizeof(int) * hn);
    int i = 0, v = 0, a = 0, mi = 0;
    for (i = 0; i < hn; i ++) m[i] = 0;
    for (i = 0; i <= hn; i ++) {
        v = i == hn ? 0 : h[i];
        if (mi == 0 || v >= h[m[mi-1]]) {
            m[mi ++] = i;
        } else {
            v = m[mi-1];
            mi --;
            a = _max(a, h[v] * (mi == 0 ? i : i - 1 - m[mi-1]));
            i --;
        }
    }
    return a;
}

int main() {
    int h[] = {2,1,5,6,2,3};
    int hn = 6;
    printf("answer is %d\r\n", largestRectangleArea(h, hn));
    return 0;
}


