/*
    url: leetcode.com/problems/n-queens-ii/
    AC 6ms 0.00%
    AC 3ms 7.14%
*/

#include <stdio.h>
#include <stdlib.h>

int valid(char** save, int i, int j, int n) {
    int k = 0, ii = 0, jj = 0;
    for (k = 0; k < n; k ++) {
        if (k != i && save[k][j] == 'Q') return 0;
        if (k != j && save[i][k] == 'Q') return 0;
    }
    ii = i + 1;
    jj = j + 1;
    while (ii < n && jj < n) {
        if (save[ii][jj] != 'Q') {
            ii ++;
            jj ++;
        } else return 0;
    }
    ii = i - 1;
    jj = j - 1;
    while (ii > -1 && jj > -1) {
        if (save[ii][jj] != 'Q') {
            ii --;
            jj --;
        } else return 0;
    }
    ii = i + 1;
    jj = j - 1;
    while (ii < n && jj > -1) {
        if (save[ii][jj] != 'Q') {
            ii ++;
            jj --;
        } else return 0;
    }
    ii = i - 1;
    jj = j + 1;
    while (ii > -1 && jj < n) {
        if (save[ii][jj] != 'Q') {
            ii --;
            jj ++;
        } else return 0;
    }
    return 1;
}

void solve(char** save, int row, int n, int* ans) {
    int i = 0, j = 0, col = 0;
    if (row == n) {
        (*ans) ++;
        return;
    }
    for (col = 0; col < n; col ++) {
        if (valid(save, row, col, n)) {
            save[row][col] = 'Q';
            solve(save, row+1, n, ans);
            save[row][col] = '.';
        }
    }
}

int totalNQueens(int n) {
    char** save = (char**) malloc(sizeof(char*) * n);
    char* save_row = NULL;
    int i = 0, j = 0, ans = 0;
    for (i = 0; i < n; i ++) {
        save_row = (char*) malloc(sizeof(char) * (n+1));
        for (j = 0; j < n; j ++) save_row[j] = '.';
        save_row[n] = '\0';
        save[i] = save_row;
    }
    solve(save, 0, n, &ans);
    return ans;
}

void solve2(int row, int n, int* ans, int* col_sign, int* lup_sign, int* rup_sign) {
    int col = 0;
    if (row == n) {
        (*ans) ++;
        return;
    }
    for (col = 0; col < n; col ++) {
        if (!col_sign[col] && !lup_sign[row + col] && !rup_sign[col - row + n - 1]) {
            col_sign[col] = lup_sign[row + col] = rup_sign[col - row + n - 1] = 1;
            solve2(row+1, n, ans, col_sign, lup_sign, rup_sign);
            col_sign[col] = lup_sign[row + col] = rup_sign[col - row + n - 1] = 0;
        }
    }
}

int totalNQueens2(int n) {
    int i = 0, j = 0, ans = 0;
    int *col_sign = NULL, *lup_sign = NULL, *rup_sign = NULL;
    col_sign = (int*) malloc(sizeof(int) * n);
    lup_sign = (int*) malloc(sizeof(int) * (2 * n - 1));
    rup_sign = (int*) malloc(sizeof(int) * (2 * n - 1));
    for (i = 0; i < n; i ++) col_sign[i] = 0;
    for (i = 0; i < 2 * n - 1; i ++) lup_sign[i] = 0;
    for (i = 0; i < 2 * n - 1; i ++) rup_sign[i] = 0;
    solve2(0, n, &ans, col_sign, lup_sign, rup_sign);
    return ans;
}

int main() {
    printf("answer is %d\r\n", totalNQueens2(14));
}

