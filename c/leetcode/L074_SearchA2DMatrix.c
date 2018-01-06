/*
    url: leetcode.com/problems/search-a-2d-matrix
    AC 3ms 25.86%
*/

#include <stdio.h>
#include <stdlib.h>

#define bool int

bool search(int** m, int ri, int ci, int rj, int cj, int rn, int cn, int t) {
    int rm = (ri+rj) / 2, cm = (ci+cj) / 2;
    if (rm < 0 || rm >= rn) return 0;
    if (cm < 0 || cm >= cn) return 0;
    if (m[rm][cm] == t) return 1;
    if (ri > rj || ci > cj) return 0;
    if (m[rm][cm] > t) {
        return search(m, ri, ci, rm-1, cj, rn, cn, t) ||
            search(m, rm, ci, rj, cm-1, rn, cn, t);
    }
    return search(m, ri, cm+1, rj, cj, rn, cn, t) ||
        search(m, rm+1, ci, rj, cm+1, rn, cn, t);

}

bool searchMatrix(int** m, int rn, int cn, int t) {
    return search(m, 0, 0, rn-1, cn-1, rn, cn,  t);
}

int main() {
    int** m = (int**) malloc(sizeof(int*) * 3);
    int m0[] = {1, 3, 5, 7};
    int m1[] = {10,11,16,20};
    int m2[] = {23,30,34,50};
    m[0] = m0;
    m[1] = m1;
    m[2] = m2;
    printf("answer is %d\r\n", searchMatrix(m, 3, 4, 3));
}