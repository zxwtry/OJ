/*
    url: leetcode.com/problems/n-queens/
    AC 6ms 16.67%
    AC 3ms 50.00%
*/

#include <stdio.h>
#include <stdlib.h>

//array list start

typedef char** T;
typedef struct al sal;
typedef struct al * pal;

struct al {
    int capacity;
    int size;
    T* arr;
};

void al_expand_capacity(pal l) {
    T* new_arr = (T*) malloc(sizeof(T) * (l->capacity * 2 + 1));
    int i = 0;
    for (i = 0; i < l->capacity; i ++)
        new_arr[i] = l->arr[i];
    free(l->arr);
    l->arr = new_arr;
    l->capacity = l->capacity * 2 + 1;
}

void al_add_last(pal l, T v) {
    if (l->capacity == l->size) al_expand_capacity(l);
    l->arr[l->size] = v;
    l->size ++;
}

void al_add_to_index(pal l, T v, int index) {
    int i = 0;
    if (index > l->size) return;
    if (l->capacity == l->size) al_expand_capacity(l);
    for (i = l->size - 1; i >= index; i --) {
        l->arr[i+1] = l->arr[i];
    }
    l->arr[index] = v;
    l->size ++;
}

T* al_convert_to_array_free_l(pal l) {
    T* arr = l->arr;
    free(l);
    return arr;
}

T al_access_by_index(pal l, int index) {
    if (index >= l->size || index < 0) return 0;
    return l->arr[index];
}

//array list end


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

void solve(pal l, char** save, int row, int n) {
    char** temp = NULL, *temp_row = NULL;
    int i = 0, j = 0, col = 0;
    if (row == n) {
        temp = (char**) malloc(sizeof(char*) * n);;
        for (i = 0; i < n; i ++) {
            temp_row = (char*) malloc(sizeof(char) * (n+1));
            for (j = 0; j < n; j ++) temp_row[j] = save[i][j];
            temp_row[n] = '\0';
            temp[i] = temp_row;
        }
        al_add_last(l, temp);
        return;
    }
    for (col = 0; col < n; col ++) {
        if (valid(save, row, col, n)) {
            save[row][col] = 'Q';
            solve(l, save, row+1, n);
            save[row][col] = '.';
        }
    }
}

char*** solveNQueens(int n, int* r) {
    char** save = (char**) malloc(sizeof(char*) * n);
    char* save_row = NULL;
    int i = 0, j = 0;
    pal l = (pal) malloc(sizeof(sal));
    l->size = 0;
    l->capacity = 16;
    l->arr = (T*) malloc(sizeof(T) * l->capacity);
    for (i = 0; i < n; i ++) {
        save_row = (char*) malloc(sizeof(char) * (n+1));
        for (j = 0; j < n; j ++) save_row[j] = '.';
        save_row[n] = '\0';
        save[i] = save_row;
    }
    solve(l, save, 0, n);
    *r = l->size;
    return al_convert_to_array_free_l(l);
}


void solve2(pal l, char** save, int row, int n, int* col_sign, int* lup_sign, int* rup_sign) {
    char** temp = NULL, *temp_row = NULL;
    int i = 0, j = 0, col = 0;
    if (row == n) {
        temp = (char**) malloc(sizeof(char*) * n);;
        for (i = 0; i < n; i ++) {
            temp_row = (char*) malloc(sizeof(char) * (n+1));
            for (j = 0; j < n; j ++) temp_row[j] = save[i][j];
            temp_row[n] = '\0';
            temp[i] = temp_row;
        }
        al_add_last(l, temp);
        return;
    }
    for (col = 0; col < n; col ++) {
        if (!col_sign[col] && !lup_sign[col - row + n - 1] && !rup_sign[row + col]) {
            col_sign[col] = lup_sign[col - row + n - 1] = rup_sign[row + col] = 1;
            save[row][col] = 'Q';
            solve2(l, save, row+1, n, col_sign, lup_sign, rup_sign);
            save[row][col] = '.';
            col_sign[col] = lup_sign[col - row + n - 1] = rup_sign[row + col] = 0;
        }
    }
}

char*** solveNQueens2(int n, int* r) {
    char** save = (char**) malloc(sizeof(char*) * n);
    char* save_row = NULL;
    int i = 0, j = 0;
    pal l = (pal) malloc(sizeof(sal));
    int *col_sign = NULL, *lup_sign = NULL, *rup_sign = NULL; 
    l->size = 0;
    l->capacity = 16;
    l->arr = (T*) malloc(sizeof(T) * l->capacity);
    col_sign = (int*) malloc(sizeof(int) * n);
    lup_sign = (int*) malloc(sizeof(int) * (2 * n - 1));
    rup_sign = (int*) malloc(sizeof(int) * (2 * n - 1));
    for (i = 0; i < n; i ++) col_sign[i] = 0;
    for (i = 0; i < 2 * n - 1; i ++) lup_sign[i] = 0;
    for (i = 0; i < 2 * n - 1; i ++) rup_sign[i] = 0;
    for (i = 0; i < n; i ++) {
        save_row = (char*) malloc(sizeof(char) * (n+1));
        for (j = 0; j < n; j ++) save_row[j] = '.';
        save_row[n] = '\0';
        save[i] = save_row;
    }
    solve2(l, save, 0, n, col_sign, lup_sign, rup_sign);
    *r = l->size;
    free(col_sign);
    free(lup_sign);
    free(rup_sign);
    return al_convert_to_array_free_l(l);
}

int main() {
    int r = 0;
    int n = 13;
    int i = 0;
    int j = 0;
    char*** ans = NULL;
    for (n = 1; n < 7; n ++) {
        ans = solveNQueens2(n, &r);            
        printf("n is %d  r is %d\r\n", n, r);
        for (i = 0; i < r; i ++) {
            for (j = 0; j < n; j ++) {
                printf("%s\r\n", ans[i][j]);
                free(ans[i][j]);
            }
            printf("=======================\r\n");
        }
        free(ans);
    }
}
