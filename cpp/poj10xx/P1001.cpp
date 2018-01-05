/*
    author:  zxwtry
    email:   zxwtry@qq.com
    project: ojcpp
    gcc:     4.8.4
    date:    2018-01-05 20:54:00
    status:  724K   0MS
*/


/*
    数字的幂次方
*/


#include<iostream>
#include<stdio.h>
#include<string>

using namespace std;

void print_arr(int* a, int ai, int aj) {
    printf("starting...\n");
    for (int ak = ai; ak < aj; ak ++) {
        printf("%-4d   %d\n", ak, a[ak]);
    }
    printf("ending...\n");
}

int* string_remove_dot_reverse(string & a, int ai, int aj) {
    int rl = aj - ai;
    for (int i = ai; i < aj; i ++) {
        if (a[i] == '.') {
            rl --;
        }
    }
    int* arr = new int[rl + 1];
    int arri = rl;
    arr[0] = rl + 1;
    for (int i = ai; i < aj; i ++) {
        if (a[i] != '.') {
            arr[arri --] = a[i] - '0';
        }
    }
    return arr;
}

int count_after_point(string & a, int ai, int aj) {
    int c = 0;
    int i = ai;
    while (i < aj) {
        if (a[i ++] == '.') {
            break;
        }
    }
    return aj - i;
}

int* calc(int* c, int ci, int cj, int t) {
    int cl = cj - ci;
    int pj = (cl + 1) * t + 1;
    int* r = new int[pj];
    int* d = new int[pj];
    r[0] = pj;
    int rk = 0, ck = 0;
    for (rk = pj - 1; rk > 0; rk --) {
        r[rk] = 0;
        d[rk] = 0;
    }
    rk = 1;
    for (ck = ci; ck < cj; ck ++) {
        r[rk ++] = c[ck];
    }
    int rm = cl + 2;
    for (int tk = 1; tk < t; tk ++) {
        for (ck = ci; ck < cj; ck ++) {
            for (int rt = 1; rt < rm; rt ++) {
                d[rt + ck - ci] += c[ck] * r[rt];
            }
        }
        rm += cl + 1;
        for (int rt = 1; rt < rm; rt ++) {
            r[rt] = d[rt];
            d[rt] = 0;
        }
        int carry = 0;
        for (int rt = 1; rt < rm; rt ++) {
            int sum = r[rt] + carry;
            r[rt] = sum % 10;
            carry = sum / 10;
        }
    }
    delete []d;
    return r;
}


bool check_all_zero(int* a, int ai, int aj) {
    for (int ak = ai; ak < aj; ak ++) {
        if (a[ak] != 0) {
            return false;
        }
    }
    return true;
}



int main() {
    string s;
    int t;
    while(cin>>s>>t) {
	    int sl = s.length();
	    int* ar = string_remove_dot_reverse(s, 0, sl);
        if (check_all_zero(ar, 1, ar[0])) {
            printf("0\n");
            continue;
        }
        if (t == 0) {
            printf("1\n");
            continue;
        } 
	    int cap = count_after_point(s, 0, sl) * t;
	    int* c = calc(ar, 1, ar[0], t);
	    int ci = 1, cj = c[0];
	    while (ci < cj && c[ci] == 0) {
	        ci ++;
	    }
	    while (ci < cj && c[cj - 1] == 0) {
	        cj --;
	    }
	    if (cap >= cj - 1) {
	        printf(".");
	        for (int pk = cj - 1; pk < cap; pk ++) {
	            printf("0");
	        }
	    }
	    for (int ck = cj - 1; ck >= ci; ck --) {
	        printf("%c", (char)(c[ck] + '0'));
	        if (cap != 0 && ck != ci && ck - 1 == cap) {
	            printf(".");
	        }
	    }
        if (cap <= ci) {
            for (int pk = cap + 1; pk < ci; pk ++) {
                printf("0");
            }
        }
	    printf("\n");
	    delete []ar;
        delete []c;
    }
    return 0;
}
