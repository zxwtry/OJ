/*
    url: leetcode.com/problems/next-permutation/
    9ms 22.22%
*/

#include <stdio.h>
#include <stdlib.h>

void print_array(int * n, int ns) {
    int i = 0;
    for (i = 0; i < ns; i ++)
        printf("%d ", n[i]);
    printf("\r\n");
}

void swap(int * a, int * b) {
    int t = * a;
    * a = * b;
    * b = t;
}

//[i, j)
void reverse(int * n, int i, int j) {
    j --;
    while (i < j) swap(n + (i ++), n + (j --));
}

//[i, j)
int _binary_search_little_bigger(int * n, int i, int j, int v) {
    int m = 0;
    j --;
    while (i < j) {
        //same with (i + j + 1) / 2
        m = i + (j - i + 1) / 2;
        if (n[m] > v) {
            i = m;
        } else {
            j = m - 1;
        }
    }
    return i;
}

void nextPermutation(int* n, int ns) {
    int i = 0, j = 0;
    for (i = ns - 2; i > -1 && n[i] >= n[i + 1]; i --) {};
    if (i == -1) {
        reverse(n, 0, ns);
    } else {
        swap(n + i, n + _binary_search_little_bigger(n, i + 1, ns, n[i]));
        reverse(n, i + 1, ns);
    }
}

int main() {
    int n[] = {3, 4, 3, 2, 1};
    int ns = 5;
    nextPermutation(n, ns);
    print_array(n, ns);
    return 0;
}