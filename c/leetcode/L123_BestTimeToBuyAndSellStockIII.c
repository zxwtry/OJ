/*
    url: leetcode.com/problems/best-time-to-buy-and-sell-stock-iii
    on_n:   AC 3ms 43.33%
    on_n_2: AC 3ms 43.33%
*/

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

void swap(int* a, int *b) {
    int t = *a;
    *a = *b;
    *b = t;
}

void heap_up(int* h, int j) {
    int p = (j-1) / 2, c = j;
    while (p != c) {
        if (h[p] > h[c]) {
            swap(h+p, h+c);
            c = p;
            p = (c-1) / 2;
        } else break;
    }
}

void heap_dn(int* h, int hn, int i) {
    int p = 0, c = 1;
    while (c < hn) {
        if (c+1 < hn && h[c+1] < h[c]) c ++;
        if (h[p] > h[c]) {
            swap(h+p, h+c);
            p = c;
            c = 2*p + 1;
        } else break;
    }
}

void heap_add(int* h, int *hi, int hn, int v) {
    if ((*hi) != hn) {
        h[*hi] = v;
        heap_up(h, *hi);
        (*hi) ++;
    } else {
        if (v > h[0]) {
            h[0] = v;
            heap_dn(h, hn, 0);
        }
    }
}

int _max(int a, int b) {
    return a < b ? b : a;
}

int _min(int a, int b) {
    return a < b ? a : b;
}

//TLE
int onn_nn(int* p, int pn) {
    int** m = NULL, i = 0, j = 0;
    int iv = 0, jv = 0, ans = 0;
    if (pn < 2) return 0;
    m = (int**) malloc(sizeof(int*) * pn);
    for (i = 0; i < pn; i ++)
        m[i] = (int*) malloc(sizeof(int) * pn);
    for (i = 0; i < pn ; i ++) m[i][i] = 0;
    for (i = 0; i < pn; i ++) {
        iv = p[i];
        for (j = i+1; j < pn; j ++) {
            jv = p[j];
            if (jv - iv > m[i][j-1])
                m[i][j] = jv - iv;
            else m[i][j] = m[i][j-1];
        }
    }
    for (i = 0; i < pn; i ++) {
        for (j = i+2; j < pn; j ++) {
            ans = _max(ans, m[i][j-1]+m[j][pn-1]);
        }
        ans = _max(ans, m[i][pn-1]);
    }
    return ans;
}

int on_n(int* p, int pn) {
    int* l = (int*) malloc(sizeof(int) * (pn+1));
    int* r = (int*) malloc(sizeof(int) * (pn+1));
    int i = 0, mv = INT_MAX, m = 0, ans = 0;
    l[0] = 0;
    for (i = 1; i <= pn; i ++) {
        mv = _min(p[i-1], mv);
        m = _max(m, p[i-1]-mv);
        l[i] = m;
    }
    mv = INT_MIN;
    m = 0;
    r[pn] = 0;
    for (i = pn-1; i > -1; i --) {
        mv = _max(p[i], mv);
        m = _max(m, mv-p[i]);
        r[i] = m;
    }
    for (i = 0; i <= pn; i ++) {
        ans = _max(ans, l[i]+r[i]);
    }
    free(l);
    free(r);
    return ans;
}

int on_n_2(int* p, int pn) {
    int* l = (int*) malloc(sizeof(int) * (pn+1));
    int i = 0, mv = INT_MAX, m = 0, ans = 0;
    l[0] = 0;
    for (i = 1; i <= pn; i ++) {
        mv = _min(p[i-1], mv);
        m = _max(m, p[i-1]-mv);
        l[i] = m;
    }
    mv = INT_MIN;
    m = 0;
    for (i = pn-1; i > -1; i --) {
        mv = _max(p[i], mv);
        m = _max(m, mv-p[i]);
        ans = _max(ans, l[i]+m);
    }
    ans = _max(ans, l[pn] + 0);
    free(l);
    return ans;
}

int maxProfit(int* p, int pn) {
    return on_n_2(p, pn);
}

int main() {
    int p[] = {1, 7, 2, 9, 3, 11, 4, 13, 5, 15};
    int pn = 10;
    printf("answer is %d\n", maxProfit(p, pn));
    return 0;
}