/*
    author:  zxwtry
    email:   zxwtry@qq.com
    project: ojcpp
    gcc:     4.8.4
    date:    2018-01-06 17:24:00
    status:  680K   0MS
*/

#include<iostream>
#include<stdio.h>
#include<string>
#include<algorithm>
#include<map>
#include<vector>

using namespace std;


void print_arr(int* a, int ai, int aj) {
    printf("starting...\n");
    for (int ak = ai; ak < aj; ak ++) {
        printf("%-4d   %d\n", ak, a[ak]);
    }
    printf("ending...\n");
}


int main() {
    int t;
    scanf("%d", &t);
    float x, y;
    for (int ti = 0; ti < t; ti ++) {
        scanf("%f %f", &x, &y);
        double ans = 3.14 * (  x * x + y * y) / 2 / 50;
        int ans_int = (int) ans;
        if (ans - ans_int < 0.00001) {
        } else {
            ans_int ++;
        }
        printf("Property %d: This property will begin eroding in year %d.\n", (ti + 1), ans_int);
    }
    printf("END OF OUTPUT.\n");
    return 0;
}
