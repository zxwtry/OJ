/*
    test url: acm.hdu.edu.cn/showproblem.php?pid=1392
    bubble_sort: 109MS  1696K
    quick_osrt:   46MS  1696K

    note:
        use gcc: gcc ConvexHull.c -lm -o convexHull
        (to link math)
*/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

struct P {
    int x,y;
};

//to find ymin
//choose i return 1
int chs_i(struct P * ps, int i, int j) {
    struct P pi = *(ps + i), pj = *(ps + j);
    if (pi.y < pj.y)
        return 0;
    else if (pi.y == pj.y && pi.x < pj.x)
        return 0;
    return 1;
};

int multiply(struct P p1, struct P p2, struct P p3) {
    return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
}

int dist_square(struct P p1, struct P p2) {
    return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
}

int cmp(struct P * ps, int i, int j) {
    int m = 0, d1 = 0, d2 = 0;
    m = multiply(*(ps+0), *(ps+i), *(ps+j));
    if (m < 0) return 1;
    else if (m > 0) return -1;
    d1 = dist_square(*(ps+0), *(ps+i));
    d2 = dist_square(*(ps+0), *(ps+j));
    if (d1 > d2)
        return -1;
    else if (d1 == d2)
        return 0;
    return 1;
}

void swap_i(int* a, int* b) {
    int t = *a;
    *a = *b;
    *b = t;
}

void swap_p(struct P * ps, int i, int j) {
    swap_i(&(*(ps+i)).x, &(*(ps+j)).x);
    swap_i(&(*(ps+i)).y, &(*(ps+j)).y);
}

//set *(ps + i)
void set_i(struct P * ps, int i, int j) {
    (*(ps+i)).x = (*(ps+j)).x;
    (*(ps+i)).y = (*(ps+j)).y;
}

//[sti, eni)
void bubble_sort(struct P * ps, int sti, int eni) {
    int is_swaped = 1;
    int i = 0;
    while (is_swaped) {
        is_swaped = 0;
        for (i = sti + 1; i < eni; i ++) {
            if (cmp(ps, i-1, i) > 0) {
                swap_p(ps, i - 1, i);
                is_swaped = 1;
            }
        }
    }
}

//[i, j]
int partition(struct P * ps, int sti, int eni, int li) {
    //li is last index of ps
    set_i(ps, li, sti);
    while(sti < eni) {
        while (sti < eni && cmp(ps, li, eni) <= 0) eni --;
        set_i(ps, sti, eni);
        while (sti < eni && cmp(ps, li, sti) >= 0) sti ++;
        set_i(ps, eni, sti);
    }
    set_i(ps, sti, li);
    return sti;
}

//[sti, eni)
void quick_sort(struct P * ps, int sti, int eni, int li) {
    int p = 0;
    if (sti + 1 < eni) {
        p = partition(ps, sti, eni - 1, li);
        quick_sort(ps, sti, p, li);
        quick_sort(ps, p + 1, eni, li);
    }
}



int convex_hull(struct P * ps, int n) {
    int i = 0, top = 0, m = 0;
    for (i = 0; i < 3; i ++)
        set_i(ps, n + 1 + i, i);
    top = n + 3;
    for (; ! (i > n); i ++) {
        m = multiply(*(ps + top - 1), *(ps + top), *(ps + i));
        if (m == 0) {
            if (dist_square(*(ps + top - 1), *(ps + top)) < dist_square(*(ps + top - 1), *(ps + i)))
                set_i(ps, top, i);
        } else {
            while (m < 0) {
                top --;
                m = multiply(*(ps + top - 1), *(ps + top), *(ps + i));
            }
            top ++;
            set_i(ps, top, i);
        }
    }
    return top;
}

void solve() {
    int i = 0, n = 0, mi = 0, m = 0;
    double d = 0.0;
    struct P * ps;
    while (1) {
        scanf("%d", &n);
        if (n == 0) break;
        mi = 0;
        ps = (struct P *)malloc(sizeof(struct P) * (2 * n + 3));
        for (i = 0; i < n; i ++) {
            scanf("%d", &(*(ps + i)).x);
            scanf("%d", &(*(ps + i)).y);
            if (chs_i(ps, mi, i)) mi = i;
        }
        swap_p(ps, 0, mi);
        set_i(ps, n, 0);
        //bubble_sort(ps, 1, n);
        quick_sort(ps, 1, n, 2 * n + 2);
        m = convex_hull(ps, n);
        d = 0;
        for (i = n + 1; i < m; i ++) {
            d += sqrt(dist_square(*(ps + i), *(ps + i + 1)));
        }
        printf("%.2lf\n", n == 2 ? d / 2 : d);
        free(ps);
    }
}

int main() {
    solve();
    return 0;
}
