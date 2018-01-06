/*
    url: leetcode.com/problems/merge-intervals/
    AC 9ms 45.83%
*/

#include <stdio.h>
#include <stdlib.h>

typedef struct Interval si;

struct Interval {
    int start;
    int end;
};

int cmp(si i, int start, int end) {
    if (i.start < start) {
        return -1;
    } else if (i.start > start) {
        return 1;
    }
    if (i.end > end) {
        return -1;
    } else if (i.end < end) {
        return 1;
    }
    return 0;
}

//[i, j)
int partition(si* n, int i, int j) {
    int save_start = 0, save_end = 0;
    j --;
    save_start = n[i].start;
    save_end = n[i].end;
    while (i < j) {
        while (i < j && cmp(n[j], save_start, save_end) >= 0) j --;
        n[i].start = n[j].start;
        n[i].end = n[j].end;
        while (i < j && cmp(n[i], save_start, save_end) <= 0) i ++;
        n[j].start = n[i].start;
        n[j].end = n[i].end;
    }
    n[i].start = save_start;
    n[i].end = save_end;
    return i;
}

//[i, j)
void quick_sort(si* n, int i, int j) {
    int p = 0;
    if (i + 1 < j) {
        p = partition(n, i, j);
        quick_sort(n, i, p);
        quick_sort(n, p+1, j);
    }
}

si* merge(si* n, int nn, int* rn) {
    int s = 0, e = 0, i = 0, ai = 0;
    if (nn == 0) return NULL;
    quick_sort(n, 0, nn);
    s = n[0].start;
    e = n[0].end;
    for (i = 1; i < nn; i ++) {
        if (e < n[i].start) {
            n[ai].start = s;
            n[ai].end = e;
            ai ++;
            s = n[i].start;
            e = n[i].end;
        } else {
            e = e > n[i].end ? e : n[i].end;
        }
    }
    n[ai].start = s;
    n[ai].end = e;
    ai ++;
    *rn = ai;
    return n;
}

int main() {
    si n[5];
    int i = 0;
    int rn = 0;
    si* ans = NULL;
    n[0].start = 15;
    n[0].end = 18;
    n[1].start = 8;
    n[1].end = 10;
    n[2].start = 2;
    n[2].end = 6;
    n[3].start = 1;
    n[3].end = 3;
    n[4].start = 100;
    n[4].end = 200;
    ans = merge(n, 5, &rn);
    for (i = 0; i < rn; i ++) {
        printf("start is %d  end is %d\r\n", n[i].start, n[i].end);
    }
}