/*
    implement   bubble_sort
                insert_sort
                shell_sort
                heap_sort
                merge_sort
                quick_sort
*/

#include <stdio.h>
#include <stdlib.h>

void swap(int* a, int* b) {
    int t = *a;
    *a = *b;
    *b = t;
}

void bubble_sort_basic(int *n, int s) {
    int i = 0, j = 0;
    for (i = 0; i < s; i ++) {
        for (j = 1; j < s; j ++) {
            if (*(n + j - 1) > *(n + j))
                swap(n + j - 1, n + j);
        }
    }
}

void bubble_sort(int *n, int s) {
    int i = 0, sign = 1;
    while (sign) {
        sign = 0;
        for (i = 1; i < s; i ++) {
            if (*(n + i - 1) > *(n + i)) {
                swap(n + i - 1, n + i);
                sign = 1;
            }
        }
    }
}

void insert_sort(int *n, int s) {
    int i = 0, j = 0, t = 0;
    //i < j
    for (j = 1; j < s; j ++) {
        t = *(n + j);
        for (i = j; i > 0 && *(n + i - 1) > t; i --)
            *(n + i) = *(n + i - 1);
        *(n + i) = t;
    }
}

void shell_sort(int *n, int s) {
    int i = 0, j = 0, g = 0, t = 0;
    //i <= j
    for (g = s / 2; g > 0; g /= 2) {
        for (j = g; j < s; j ++) {
            t = *(n + j);
            for (i = j; i >= g && t < *(n + i - g); i -= g)
                *(n + i) = *(n + i - g);
            *(n + i) = t;
        }
    }
}

void heap_sort(int *n, int s) {
    int p = 0, c = 0, i = 0, hs = s;
    int * heap = (int *) malloc(sizeof(int) * s);
    for (i = 0; i < s; i ++)
        *(heap + i) = *(n + i);
    //build heap
    for (i = (s - 2)/2; i > -1; i --) {
        //heap down
        p = i;
        c = 2 * p + 1;
        while (c < s) {
            if (c + 1 < s && *(heap + c + 1) < *(heap + c)) c ++;
            if (*(heap + p) > *(heap + c)) {
                swap(heap + p, heap + c);
            } else break;
            p = c;
            c = 2 * p + 1;
        }
    }
    //pop heap
    for (i = 0; i < s; i ++) {
        *(n + i) = *heap;
        *heap = *(heap + hs - 1);
        //heap down
        hs --;
        p = 0;
        c = 2 * p + 1;
        while (c < hs) {
            if (c + 1 < hs && *(heap + c + 1) < *(heap + c)) c ++;
            if (*(heap + p) > *(heap + c)) {
                swap(heap + p, heap + c);
            } else break;
            p = c;
            c = 2 * p + 1;
        }
    }
}

//[l, m) [m, r)
void _merge(int* n, int l, int m, int r, int* t) {
    int i = l, j = m, k = l;
    while (i < m && j < r) {
        if (*(n + i) < *(n + j)) {
            *(t + (k ++)) = *(n + (i ++));
        } else {
            *(t + (k ++)) = *(n + (j ++));
        }
    }
    while (i < m) 
        *(t + (k ++)) = *(n + (i ++));
    while (j < r)
        *(t + (k ++)) = *(n + (j ++));
    for (k = l; k < r; k ++)
        *(n + k) = *(t + k);
}

//[l, r)
void _merge_sort(int*n, int l, int r, int* t) {
    int m = 0;
    if (l + 2 > r) {
        return;
    } else if (l + 2 == r) {
        if (*(n + l) > *(n + l + 1))
            swap(n + l, n + l + 1);
    } else {
        m = (l + r) / 2 + 1;
        _merge_sort(n, l, m, t);
        _merge_sort(n , m, r, t);
        _merge(n, l, m, r, t);  
    }
}

void merge_sort(int *n, int s) {
    int* t = (int *) malloc(sizeof(int) * s);
    _merge_sort(n, 0, s, t);
    free(t);
}

//[l, r)
int _partition(int* n, int l, int r) {
    r --;
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
        p = _partition(n, l, r);
        _quick_sort(n, l, p);
        _quick_sort(n, p + 1, r);
    }
}

void quick_sort(int* n, int s) {
    _quick_sort(n, 0, s);
}


int main() {
    int n[] = {2, 2, 1, 1, 1, 4, 3};
    int s = 7;
    int i = 0;
    quick_sort(n, s);
    for (i = 0; i < s; i ++) {
        printf("%d ", *(n + i));
    }
    printf("\r\n");
}
