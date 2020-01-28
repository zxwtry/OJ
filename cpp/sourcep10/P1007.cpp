/*
    author:  zxwtry
    email:   zxwtry@qq.com
    project: ojcpp
    gcc:     4.8.4
    date:    2018-01-07 11:43:00
    status:  664K   16MS
*/

#include<iostream>
#include<stdio.h>
#include<string>
#include<algorithm>
#include<map>
#include<vector>

using namespace std;

int count_reverse_pairs(char c[], int cl, int cr) {
    int cnt = 0;
    for (int ci = cl; ci < cr; ci ++) {
        for (int cj = ci + 1; cj < cr; cj ++) {
            if (c[ci] > c[cj]) {
                cnt ++;
            }
        }
    }
    return cnt;
}


void sort_bubble(char** c, int l, int r, int* v) {
    bool sign = true;
    while (sign) {
        sign = false;
        for (int i = l + 1; i < r; i ++) {
            if (v[i - 1] > v[i]) {
                swap(c[i - 1], c[i]);
                swap(v[i - 1], v[i]);
                sign = true;
            }
        }
    }
}


int main() {
    int n,m;
    scanf("%d %d", &n, &m);
    char** c = new char* [m];
    int* v = new int[m];
    for (int mi = 0; mi < m; mi ++) {
        c[mi] = new char[n + 1];
        scanf("%s", c[mi]);
        v[mi] = count_reverse_pairs(c[mi], 0, n);
    }
    sort_bubble(c, 0, m, v);
    for (int mi = 0; mi < m; mi ++) {
        printf("%s\n", c[mi]);
    }
    return 0;
}
