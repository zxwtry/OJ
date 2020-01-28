/*
    author:  zxwtry
    email:   zxwtry@qq.com
    project: ojcpp
    gcc:     4.8.4
    date:    2018-01-05 20:44:00
    status:  712K   0MS
*/

#include<iostream>
#include<stdio.h>
#include<string>
#include<algorithm>
#include<map>

using namespace std;


void print_arr(int* a, int ai, int aj) {
    printf("starting...\n");
    for (int ak = ai; ak < aj; ak ++) {
        printf("%-4d   %d\n", ak, a[ak]);
    }
    printf("ending...\n");
}


int main() {
    double v = 0;
    double a = 0;
    for (int i = 0; i < 12; i ++) {
        scanf("%lf", &a);
        v += a;
    }
    v = v / 12;
    int t = (int) (v * 1000);
    if (t % 10 > 4) {
        t += 10;
    }
    t = t / 10;
    printf("$%d.%d\n", t / 100, t % 100);
    return 0;
}
