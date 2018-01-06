/*
    url: leetcode.com/problems/valid-sudoku/
    9ms 41.33%
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define bool int

bool isValidSudoku(char** b, int rs, int cs) {
    int row = 0, col = 0, sign = 1, si = 0;
    int ri = 0, ci = 0;
    char s[9];
    for (row = 0; row < rs; row ++) {
        memset(s, 0, 9);
        for (col = 0; col < cs; col ++) {
            if (b[row][col] != '.') {
                si = b[row][col] - '1';
                if (si < 0 || si > 9) return 0;
                s[si] ++;
                if (s[si] > 1) return 0;
            }
        }
        memset(s, 0, 9);
        for (col = 0; col < cs; col ++) {
            if (b[col][row] != '.') {
                si = b[col][row] - '1';
                if (si < 0 || si > 9) return 0;
                s[si] ++;
                if (s[si] > 1) return 0;
            }
        }
    }
    for (row = 1; row < 9; row += 3) {
        for (col = 1; col < 9; col += 3) {
            memset(s, 0, 9);
            for (ri = -1; ri < 2; ri ++) {
                for (ci = -1; ci < 2; ci ++) {
                    if (b[row + ri][col + ci] != '.') {
                        si = b[row + ri][col + ci] - '1';
                        if (si < 0 || si > 9) return 0;
                        s[si] ++;
                        if (s[si] > 1) return 0;
                    }                  
                }
            }
            
        }
    }
    return 1;
}

bool isValid2(char row[], int i) {
    //'.'
    if (i < 0 || i > 8) return 1;
    //invalid
    if (row[i] == 1) return 0;
    //valid
    row[i] = 1;
    return 1;
}

bool isValidSudoku2(char** b, int rs, int cs) {
    int i =0, j = 0;
    char row[9], col[9], nin[9];
    for (i = 0; i < 9; i ++) {
        memset(row, 0, 9);
        memset(col, 0, 9);
        memset(nin, 0, 9);
        for (j = 0; j < 9; j ++) {
            if (isValid2(row, b[i][j] - '1') == 0)
                return 0;
            if (isValid2(col, b[j][i] - '1') == 0)
                return 0;
            if (isValid2(nin, b[i/3*3+j/3][i%3*3+j%3] - '1') == 0)
                return 0;
        }
    }
    return 1;
}

int main() {
    char ** b = (char **) malloc(sizeof(char *) * 9);
    int rs = 9, cs = 9;
    b[0] = ".87654321";
    b[1] = "2........";
    b[2] = "3........";
    b[3] = "4........";
    b[4] = "5........";
    b[5] = "6........";
    b[6] = "7........";
    b[7] = "8........";
    b[8] = "9........";
    printf("answer is %d\r\n", isValidSudoku(b, rs, cs));
    free(b);
    return 0;
}