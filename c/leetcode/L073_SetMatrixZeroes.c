/*
    url: leetcode.com/problems/set-matrix-zeroes
    AC 23ms 40.54%
*/

#include <stdio.h>
#include <stdlib.h>

void setZeroes(int** m, int xn, int yn) {
    int xi = 0, yi = 0, x0 = 1, y0 = 1;
    int x = 0, y = 0;
    for (xi = 0; xi < xn; xi ++)
        if (m[xi][0] == 0) y0 = 0;
    for (yi = 0; yi < yn; yi ++)
        if (m[0][yi] == 0) x0 = 0;

    printf("%d %d\r\n", x0, y0);
    for (xi = 1; xi < xn; xi ++) {
        for (yi = 1; yi < yn; yi ++) {
            if (m[xi][yi] == 0) {
                m[xi][0] = 0;
                m[0][yi] = 0;
            }
        }
    }
    for (xi = 1; xi < xn; xi ++) {
        for (yi = 1; yi < yn; yi ++) {
            if (m[xi][0] == 0 || m[0][yi] == 0) {
                m[xi][yi] = 0;
            }
        }
    }
    if (x0 == 0)
        for (yi = 0; yi < yn; yi ++)
            m[0][yi] = 0;
    if (y0 == 0)
        for (xi = 0; xi < xn; xi ++)
            m[xi][0] = 0;
}

int main() {
    int** m = (int**) malloc(sizeof(int*) * 2);
    int m0[] = {1, 1, 1};
    int m1[] = {0, 1, 2};
    int xn = 2, xi = 0;
    int yn = 3, yi = 0;
    m[0] = m0;
    m[1] = m1;
    setZeroes(m , 2, 3);
    for (xi = 0; xi < xn; xi ++) {
        for (yi = 0; yi < yn; yi ++)
            printf("%d ", m[xi][yi]);
        printf("\r\n");
    }
    free(m);
    return 0;
}