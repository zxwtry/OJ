/*
    url: leetcode.com/problems/rotate-image/
    AC 6ms 1.02%
*/

#include <stdio.h>
#include <stdlib.h>

//[i, i]  [j, j]
void shell_rotate(int** m, int i, int j) {
    int t = 0, k = 0;
    for (k = 0; k < j-i; k ++) {
        t = m[i][i+k];
        m[i][i+k] = m[j-k][i];
        m[j-k][i] = m[j][j-k];
        m[j][j-k] = m[i+k][j];
        m[i+k][j] = t;
    }
}

void rotate(int** m, int mr, int mc) {
    int i = 0, j = mr - 1;
    while (i < j) {
        shell_rotate(m, i, j);
        i ++;
        j --;
    }
}

int main() {
    int n = 4;
    int** m = (int**) malloc(sizeof(int*) * n);
    int m0[] = {1, 2, 3, 4};
    int m1[] = {5, 6, 7, 8};
    int m2[] = {9, 10, 11, 12};
    int m3[] = {13, 14, 15, 16};
    int mr = 4, mc = 4, i = 0, j = 0;
    m[0] = m0;
    m[1] = m1;
    m[2] = m2;
    m[3] = m3;
    rotate(m, mr, mc);
    //shell_rotate(m, 0, 3);
    for (i = 0; i < 4; i ++) {
        for (j = 0; j < 4; j ++) {
            printf("%d ", m[i][j]);
        }
        printf("\r\n");
    }
    free(m);
}

