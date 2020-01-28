/*
    author:  zxwtry
    email:   zxwtry@qq.com
    project: ojcpp
    gcc:     4.8.4
    date:    2018-01-06 09:33:00
    status:  712K   0MS
*/


/*
    计算题
*/

#include<iostream>
#include<stdio.h>
#include<string>
#include<algorithm>
#include<map>
#include<cmath>

using namespace std;


void print_arr(int* a, int ai, int aj) {
    printf("starting...\n");
    for (int ak = ai; ak < aj; ak ++) {
        printf("%-4d   %d\n", ak, a[ak]);
    }
    printf("ending...\n");
    
}


int cal(float v ) {
    int n = 2;
    while (v > 0.00) {
        v -= 1.0 / n;
        n ++;
    }
    return n - 2;
}cc

int main() {
    float v;
    while (true) {
        scanf("%f", &v);
        if (fabs(v) < 0.01) {
            break;
        }
        printf("%d card(s)\n", cal(v));
    }
    return 0;
}
