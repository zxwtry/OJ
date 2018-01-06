/*
    url: leetcode.com/problems/insert-interval
    AC 9ms 37.50%
*/

#include <stdio.h>
#include <stdlib.h>

typedef struct Interval si;

struct Interval {
    int start;
    int end;
};

int cmp_real(si* n, int ni, int nn, int val) {
    si *n1 = NULL, *n2 = NULL;
    if (ni % 2 == 0) {
        n1 = ni / 2 == 0 ? NULL : n + (ni / 2 - 1);
        n2 = ni / 2 == nn ? NULL : n + (ni / 2);
        if (n1 != NULL && n1->end  >= val) return -1;
        if (n2 != NULL && n2->start <= val) return 1;
        return 0;
    } else {
        n1 = n  + (ni / 2);
        if (val < n1->start) return -1;
        if (val > n1->end) return 1;
        return 0;
    }
}

int state(si* n, int nn, int val) {
    int i = 0, j = 2  * nn, m = 0, cmp_val = 0;
    while (i < j) {
        m = i + (j - i) / 2;
        cmp_val = cmp_real(n, m, nn, val);
        if (cmp_val == 0) return m;
        else if (cmp_val < 0) {
            j = m - 1;
        } else {
            i = m + 1;
        }
    }
    return i;
}

si* insert(si* n, int nn, si new_n, int* rn) {
    int i = state(n, nn, new_n.start);
    int j = state(n, nn, new_n.end);
    int k = 0, cut = 0, v = 0;
    int r1 = 0, r2 = 0, ai = 0;
    si* ans = NULL;
    if (i == j) {
        if (i % 2 == 0) {
            ans = (si*) malloc(sizeof(si) * (nn + 1));
            r1 = i/2-1;
            r2 = i/2;
            ai = 0;
            for (v = 0; v <= r1; v ++)
                ans[ai ++] = n[v];
            ans[ai ++] = new_n;
            for (v = r2; v < nn; v ++)
                ans[ai ++] = n[v];
            *rn = nn+1;
            return ans;
        } else {
            *rn = nn;
            return n;
        }
    }
    r1 = i/2-1;
    r2 = (j+1)/2;
    if (i%2 == 1) new_n.start = n[i/2].start;
    if (j%2 == 1) new_n.end = n[j/2].end;
    ans = (si*) malloc(sizeof(si) * (nn + r1 - r2 + 2));
    ai = 0;
    for (v = 0; v <= r1; v ++)
        ans[ai ++] = n[v];
    ans[ai ++] = new_n;
    for (v = r2; v < nn; v ++)
        ans[ai ++] = n[v];
    *rn = nn + r1 - r2 + 2;
    return ans;
}

int main() {
    si n[5];
    int i = 0;
    int rn = 0;
    si* ans = NULL;
    int nn = 5;
    si new_n;
    new_n.start = -1;
    new_n.end = -1;
    n[0].start = 1;
    n[0].end = 2;
    n[1].start = 3;
    n[1].end = 5;
    n[2].start = 6;
    n[2].end = 7;
    n[3].start = 8;
    n[3].end = 10;
    n[4].start = 12;
    n[4].end = 16;
    //for (i = -1; i < 19; i ++)
    //printf("i is %d  small is %d\r\n", i,  state(n, nn, i));
    ans = insert(n, nn, new_n, &rn);
    printf("answer size is %d\r\n", rn);
    for (i = 0; i < rn; i ++)
        printf("start is %d   end is %d\r\n", ans[i].start, ans[i].end);
    //free(ans);
    return 0;
}