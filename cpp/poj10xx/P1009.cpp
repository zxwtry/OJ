/*
    author:  zxwtry
    email:   zxwtry@qq.com
    project: ojcpp
    gcc:     4.8.4
    date:    2018-01-05 20:44:00
*/

/*
*/

#include<iostream>
#include<stdio.h>
#include<string>
#include<algorithm>
#include<map>
#include<vector>
#include<stdlib.h>

using namespace std;


int xa[] = {-1, -1, -1, 0, 0, 1, 1, 1};
int ya[] = {-1, 0, 1, -1, 1, -1, 0, 1};

// 找到第一个大于p的下标
int get_vi(vector<int> & m, int ml, int mr, int p) {
    mr --;
    while (ml < mr) {
        int mm = ml + (mr - ml) / 2;
        int cmp = m[mm] - p;
        if (cmp <= 0) {
            ml = mm + 1;
        } else {
            mr = mm;
        }
    }
    return ml;
}

int cnt_my = 0;

int get_range(vector<int> & v, vector<int> & m, int xn, int yn, int x, int y, int val) {
    int range = 0;
    int ml = 0, mr = m.size();
    for (int i = 0; i < 8; i ++) {
        int xx = x + xa[i], yy = y + ya[i];
        if (xx < 0 || xx >= xn || yy < 0 || yy >= yn) continue;
        int pp = xx * yn + yy;
        range = max(range, abs(val - v[get_vi(m, ml, mr, pp)]));
    }
    return range;
}


void print_range(int* last, int range, int times) {
    if (last[0] == range || last[1] == 0) {
        last[1] += times;
    } else {
        printf("%d %d\n", last[0], last[1]);
        last[0] = range;
        last[1] = times;
    }
}


void solve(vector<int> & v, vector<int> & c, vector<int> & m, int yn) {
    int vn = v.size();
    int p = 0;
    int last[2] = {0, 0};
    int xn = m[vn - 1] / yn;
    for (int i = 0; i < vn; i ++) {
        int p1 = p + yn + 1;
        int p2 = p + c[i] - yn - 1;
        int p3 = p + c[i];
        int pi = p;
        // printf("%d %d %d %d\n", p1, p2, p3, pi);
        for (; pi < p1 && pi < p2; pi ++) {
            print_range(last, get_range(v, m, xn, yn, pi / yn, pi % yn, v[i]), 1);
        }
        if (p2 > pi) {
            print_range(last, 0, p2 - pi);
        }
        pi = p2;
        for (; pi < p3; pi ++) {
            print_range(last, get_range(v, m, xn, yn, pi / yn, pi % yn, v[i]), 1);
        } 
        p += c[i];
    }
    printf("%d %d\n", last[0], last[1]);
}


int main() {
    int n, t;
    vector<int> v, c, m;
    while(true) {
        t = 0;
        scanf("%d", &n);
        if (n == 0) break;
        printf("%d\n", n);
        v.clear();
        c.clear();
        m.clear();
        while(true) {
            int vt, ct;
            scanf("%d %d", &vt, &ct);
            t += ct;
            if (vt == 0 && ct == 0) break;
            v.push_back(vt);
            c.push_back(ct);
            m.push_back(t);
        }
        solve(v, c, m, n);
        printf("0 0\n");
    }    
    return 0;
}
