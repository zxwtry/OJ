/*
    url: leetcode.com/problems/spiral-matrix-ii/
    AC 3ms 14.29%
*/

#include <stdio.h>
#include <stdlib.h>

void shell_add(int** m, int i, int j, int* v) {
    int k = 0;
    for (k = i; k < j; k ++)
        m[i][k] = (*v) ++;
    for (k = i; k < j; k ++)
        m[k][j] = (*v) ++;
    for (k = j; k > i; k --)
        m[j][k] = (*v) ++;
    for (k = j; k > i; k --)
        m[k][i] = (*v) ++;
}

int** generateMatrix(int n) {
    int** m = (int**) malloc(sizeof(int*) * n);
    int i = 0, j = n-1, v = 1;
    for (i = 0; i < n; i ++)
        m[i] = (int*) malloc(sizeof(int) * n);
    i = 0;
    while (i < j) {
        shell_add(m, i, j, &v);
        i ++;
        j --;
    }
    if (n % 2 == 1)
        m[n/2][n/2] = v;
    return m;
}

int main() {
    int n = 9;
    int** m = generateMatrix(n);
    int i = 0; 
    int j = 0;
    for (i = 0; i < n; i ++) {
        for (j = 0; j < n; j ++) {
            printf("%d ", m[i][j]);
        }
        printf("\r\n");
    }
}