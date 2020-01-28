/*
    author:  zxwtry
    email:   zxwtry@qq.com
    project: ojcpp
    gcc:     4.8.4
    date:    2018-01-06 17:44:00
    status:  660K   141MS
*/


/*
    中国剩余定理：

    有n个周期变量：
    第i个，在a[i]达到最高峰，周期是m[i]

    找到最小的正整数：除以m[i] 余数是a[i]
    
    定义一个数组r
    r[i] ： m数组，除i，最小公倍数
            且满足   除以m[i] 余1

    一个符合要求的正整数：
    求和 r[i] * a[i]

    再对  m数组的最小公倍数  取余
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


// greatest common divisor
long long gcd(long long a, long long b) {
    if (a == b) return a;
    if (a < b) return gcd(b, a);
    while (true) {
        if (b == 0) {
            break;
        }
        long long t = a % b;
        a = b;
        b = t;
    }
    return a;
}



// lowest common multiple
long long lcm(long long a, long long b) {
    if (a == b) return a;
    return (a * b) / gcd(a, b);
}


// lowest common multiple array
long long lcm_arr(int* a, int al, int ar) {
    long long cm = 1;
    for (int ai = al; ai < ar; ai ++) {
        cm = lcm(cm, a[ai]);
    }
    return cm;
}


// 中国剩余定理
// 数组a中存储的是：处在峰值的时刻
// 数组m中存储的是：周期
int crt(int a[], int m[], int n) {
    long long * r = new long long[n];
    for (int i = 0; i < n; i ++) {
        long long rv = lcm_arr(m, 0, i) * lcm_arr(m, i + 1, n);
        r[i] = rv;
        while (r[i] % m[i] != 1) {
            r[i] += rv;
        }
    }
    long long v = 0;
    for (int i = 0; i < n; i ++) {
        v += r[i] * a[i];
    }
    v %= lcm_arr(m, 0, n);
    delete []r;
    return (int)v;
}


int main() {
    int a[] = {0, 0 ,0};
    int m[] = {23, 28, 33};
    int d, c = 1;
    while (true) {
        scanf("%d %d %d %d", &a[0], &a[1], &a[2], &d);
        if (a[0] == -1 && a[1] == -1 && a[2] == -1 && d == -1) {
            break;
        }
        for (int i = 0; i < 3; i ++) {
            a[i] = a[i] % m[i];
        }
        int ret = crt(a, m, 3) - d;
        int rs = (int)lcm_arr(m, 0, 3);
        while (ret <= 0) {
            ret += rs;
        }
        printf("Case %d: the next triple peak occurs in %d days.\n", c ++, ret);
    }
    return 0;
}
