/*
    url: leetcode.com/problems/edit-distance
    minDistance:  AC 9ms 23.81%
    minDistance2: AC 9ms 23.81%
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int _min(int a, int b, int c) {
    if (a < b) return  a < c ? a : c;
    return b < c ? b : c;
}

int minDistance(char* w1, char* w2) {
    int n1 = strlen(w1), n2 = strlen(w2), i = 0, j = 0;
    int ** m = (int**) malloc(sizeof(int*) * (n1 + 1));
    for (i = 0; i <= n1; i ++)
        m[i] = (int*) malloc(sizeof(int) * (n2 + 1));
    for (j = 0; j <= n2; j ++)
        m[0][j] = j;
    for (i = 0; i <= n1; i ++)
        m[i][0] = i;
    for (i = 0; i < n1; i ++) {
        for (j = 0; j < n2; j ++) {
            m[i+1][j+1] = _min(m[i][j]+(w1[i] == w2[j] ? 0 : 1), 
                m[i+1][j] + 1, m[i][j+1] + 1);
        }
    }
    j = m[n1][n2];
    for (i = 0; i <= n1; i ++)
        free(m[i]);
    free(m);
    return j;
}

void swap(int** m1, int** m2) {
    int* t = *m1;
    *m1 = *m2;
    *m2 = t;
}

int minDistance2(char* w1, char* w2) {
    int n1 = strlen(w1), n2 = strlen(w2), i = 0, j = 0;
    int *m1 = (int*) malloc(sizeof(int) * (n2 + 1));
    int *m2 = (int*) malloc(sizeof(int) * (n2 + 1));
    for (j = 0; j <= n2; j ++)
        m1[j] = j;
    for (i = 0; i < n1; i ++) {
        m2[0] = i+1;
        for (j = 0; j < n2; j ++) {
            m2[j+1] = _min(m1[j]+(w1[i] == w2[j] ? 0 : 1), 
                m2[j] + 1, m1[j+1] + 1);
        }
        swap(&m1, &m2);
    }
    j = m1[n2];
    free(m1);
    free(m2);
    return j;
}

int main() {
    char* w1 = "b";
    char* w2 = "";
    printf("answer is %d\r\n", minDistance(w1, w2));
    return 0;
}