/*
    test url: exercise.acmcoder.com/online/online_judge_ques?ques_id=3363&konwledgeId=40
    176ms 8952 kb
*/

#include <stdio.h>
#include <stdlib.h>

//signed int max 2147483647
int MAX = ((1<<31) - 1);

void swap(int * a, int * b) {
    int c = *a;
    *a = *b;
    *b = c;
}

void swap_ptr(int * s, int i, int j) {
    swap(s + i, s + j);
}

//remove _ error in vc : "syntax error : 'type'"
int _min(int a, int b) {
    return a < b ? a : b;
}

int dijkstra(int** r, int rn, int* s, int sn, int* d, int dn) {
    int i = 0, pj = 0, j = 0, sv = 0, hi = 0;
    int answer = MAX, sign = 0, p = 0, c = 0, n = 0;
    int ** next = (int **) malloc(sizeof(int *) * rn);
    int * heap = (int *) malloc(sizeof(int) * rn);
    int * hein = (int *) malloc(sizeof(int) * rn);
    int * ds = (int *) malloc(sizeof(int) * rn);
    for (i = 0; i < rn; i ++)
        *(next + i) = (int *) malloc(sizeof(int) * rn);
    //set next
    for (i = 1; i < rn; i ++) {
        *(*(next + i) + 0) = -1;
        pj = 0;
        for (j = 1; j < rn; j ++) {
            if (*(*(r + i) + j) != MAX) {
                *(*(next + i) + pj) = j;
                *(*(next + i) + j) = -1;
                pj = j;
            }
        }
    }
    for (i = 0; i < sn; i ++) {
        //select start is *(s + i)
        sv = *(s + i);
        //init heap
        hi = 0;
        for (j = 1; j < rn; j ++) {
            if (sv == j) {
                *(ds+j) = 0;
            } else {
                *(ds+j) = MAX;
                *(heap+(++hi)) = j;
                *(hein+(j)) = hi;
            }
        }
        //exit when heap is empty
        while (1) {
            //check if sv is in d
            sign = 0;
            for (j = 0; j < dn; j ++)
                if (*(d+j) == sv) {
                    answer = _min(answer, *(ds + sv));
                    sign = 1;
                    break;
                }
            if (sign) break;
            
            //exit when n == -1
            n = 0;
            while (1) {
                n = *(*(next + sv) + n);
                if (n == -1) break;
                if (*(ds + n) > *(ds + sv) + *(*(r + sv) + n)) {
                    *(ds + n) = *(ds + sv) + *(*(r + sv) + n);
                    c = *(hein + n);
                    p = c / 2;
                    while (p != 0) {
                        if (*(ds + * (heap + p)) > *(ds + *(heap + c))) {
                            swap_ptr(hein, *(heap + p), *(heap + c));
                            swap_ptr(heap, p, c);
                        } else break;
                        c = p;
                        p = c / 2;
                    }
                }
            }
            //renew sv
            if (hi == 0) break;
            sv = heap[1];
            heap[1] = heap[hi --];
            //heapDn
            p = 1;
            c = p * 2;
            while (! (c > hi)) {
                if ((!(c + 1 > hi)) && *(ds + * (heap + c + 1)) < *(ds + * (heap + c))) c ++;
                if (*(ds + *(heap + p)) > *(ds + *(heap + c))) {
                    swap_ptr(hein, *(heap + p), *(heap + c));
                    swap_ptr(heap, p, c);
                } else break;
                p = c;
                c = 2 * p;
            }
        }
    }
    for (i = 0; i < rn; i ++)
        free(*(next + i));
    free(ds);
    free(heap);
    free(hein);
    return answer;
}

void solve() {
    int v1 = 0, v2 = 0, v3 = 0, v4 = 0, i = 0, j = 0;
    int * s = NULL, * d = NULL;
    int ** r = NULL;
    int u = 0, v = 0, w = 0, t = 0, T = 0;
    scanf("%d", &T);
    for (t = 1; !(t > T); t ++) {
        scanf("%d", &v1);
        scanf("%d", &v2);
        scanf("%d", &v3);
        scanf("%d", &v4);
        s = (int *) malloc(sizeof(int) * v3);
        d = (int *) malloc(sizeof(int) * v4);
        v1 ++;
        r = (int **) malloc(sizeof(int *) * v1);
        for (i = 0; i < v1; i ++)
            *(r+i) = (int *) malloc(sizeof(int) * v1);
        //scanf start
        for (i = 0; i < v3; i ++)
            scanf("%d", (s + i));
        //scanf destination
        for (i = 0; i < v4; i ++)
            scanf("%d", (d + i));
        //set r default
        for (i = 0; i < v1; i ++)
            for (j = 0; j < v1; j ++)
                *(*(r + i) + j) = MAX;
        //scanf route
        for (i = 0; i < v2; i ++) {
            scanf("%d", &u);
            scanf("%d", &v);
            scanf("%d", &w);
            *(*(r + u) + v) = w;
            *(*(r + v) + u) = w;
        }
        //get answer
        i = dijkstra(r, v1, s, v3, d, v4);
        if (i == MAX) { 
            printf("Case #%d: No answer\n", t);
        } else {
            printf("Case #%d: %d\n", t, i);
        }
        //free
        for (i = 0; i < v1; i ++)
            free(*(r + i));
        free(s);
        free(d);
    }
}

int main() {
    solve();
    return 0;
}