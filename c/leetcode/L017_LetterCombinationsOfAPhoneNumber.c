/*
    url: leetcode.com/problems/letter-combinations-of-a-phone-number/
    3ms 0.00%
*/

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

void dfs(char ** c, char * d, int di, int dlen, char ** a, int *ai, char * temp, int * cc) {
    char ch = *(d + di);
    char * t = NULL;
    int i = 0;
    if (ch == '\0') {
        //
        t = (char *) malloc(sizeof(char) * (dlen + 1));
        for (i = 0; i < dlen + 1; i ++) {
            *(t + i) = *(temp + i);
        }
        *(a + ((*ai)++)) = t;
    } else {
        for (i = 0; i < *(cc + ch - '2'); i ++) {
            *(temp + di) = *(*(c + ch - '2') + i);
            dfs(c, d, di + 1, dlen, a, ai, temp, cc);
        }
    }
}

char** letterCombinations(char* digits, int* returnSize) {
    char c0[] = {'a', 'b', 'c'};
    char c1[] = {'d', 'e', 'f'};
    char c2[] = {'g', 'h', 'i'};
    char c3[] = {'j', 'k', 'l'};
    char c4[] = {'m', 'n', 'o'};
    char c5[] = {'p', 'q', 'r', 's'};
    char c6[] = {'t', 'u', 'v'};
    char c7[] = {'w', 'x', 'y', 'z'};
    int index[] = {0, 0, 0, 1, 1, 1,
                   2, 2, 2, 3, 3, 3,
                   4, 4, 4, 5, 5, 5, 5,
                   6, 6, 6, 7, 7, 7, 7};
    char * c[8];
    int i = 0;
    int cc[] = {3, 3, 3, 3, 3, 4, 3, 4};
    int len = 0;
    int count = 1;
    char ** answer = NULL;
    char * temp = NULL;
    int ai = 0;
    c[0] = c0;
    c[1] = c1;
    c[2] = c2;
    c[3] = c3;
    c[4] = c4;
    c[5] = c5;
    c[6] = c6;
    c[7] = c7;
    len = strlen(digits);
    if (len == 0) {
        *returnSize = 0;
        return NULL;
    }
    for (i = 0; i < len; i ++) {
        count *= cc[*(digits+ i) - '2'];
    }
    answer = (char **) malloc(sizeof(char *) * count);
    temp = (char *) malloc(sizeof(char) * (len + 1));
    *(temp + len) = '\0';
    dfs(c, digits, 0, len, answer, &ai, temp, cc);
    *(returnSize) = count;
    return answer;
}

int main() {
    char ** a = NULL;
    char digits[] = "7";
    int returnSize[] = {1};
    int i = 0;
    a = letterCombinations(digits, returnSize);
    for (i = 0; i < *returnSize; i ++) 
        free(*(a + i));
    free(a);
    printf("a");
}