/*
    url: leetcode.com/problems/spiral-matrix/
    AC 3ms 0.00%
*/

#include <stdio.h>
#include <stdlib.h>

void shell_add(int* ans, int* ai, int** m, int x1, int y1, int x2, int y2) {
    int x = 0, y = 0;
    for (y = y1; y < y2; y ++)
        ans[(*ai) ++] = m[x1][y];
    for (x = x1; x < x2; x ++)
        ans[(*ai) ++] = m[x][y2];
    for (y = y2; y > y1; y --)
        ans[(*ai) ++] = m[x2][y];
    for (x = x2; x > x1; x --)
        ans[(*ai) ++] = m[x][y1];
}

int* spiralOrder(int** m, int rn, int cn) {
    int* ans = (int*) malloc(sizeof(int)*(rn*cn));
    int ai = 0;
    int x1 = 0, y1 = 0, x2 = rn - 1, y2 = cn - 1;
    while (x1 < x2 && y1 < y2) {
        shell_add(ans, &ai, m, x1, y1, x2, y2);
        x1 ++;
        y1 ++;
        x2 --;
        y2 --;
    }
    if (x1 == x2) {
        while(y1 <= y2)
            ans[ai ++] = m[x1][y1++];
    } else if (y1 == y2) {
        while (x1 <= x2)
            ans[ai ++] = m[x1++][y1];
    }
    printf("ai is %d\r\n", ai);
    return ans;
}

int main() {
    int rn = 7, cn = 3;
    int** m = (int**) malloc(sizeof(int*)*rn);
    int m0[] = {1, 2, 3};
    int m1[] = {4, 5, 6};
    int m2[] = {7, 8, 9};
    int m3[] = {10, 11, 12};
    int m4[] = {13, 14, 15};
    int m5[] = {16, 17, 18};
    int m6[] = {19, 20, 21};
    int* ans = NULL;
    int i = 0;
    m[0] = m0;
    m[1] = m1;
    m[2] = m2;
    m[3] = m3;
    m[4] = m4;
    m[5] = m5;
    m[6] = m6;
    ans = spiralOrder(m, rn, cn);
    for (i = 0; i < rn*cn; i ++)
        printf("%d ", ans[i]);
    printf("\r\n");
    free(m);
}