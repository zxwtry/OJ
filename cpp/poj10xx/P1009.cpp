/*
    author:  zxwtry
    email:   zxwtry@qq.com
    project: ojcpp
    gcc:     4.8.4
    date:    2018-01-05 20:44:00
*/

#include<iostream>
#include<stdio.h>
#include<string>
#include<algorithm>
#include<map>
#include<vector>

using namespace std;


int xa[] = {-1, 0, 1, -1, 1, -1, 0, 1};
int ya[] = {-1, -1, -1, 0, 0, 1, 1, 1};


int find_value(vector<int> & cm, int i, int bi) {
    int n = cm.size();
    int ci = bi;
    for (; ci < n; ci ++) {
        if (cm[ci] > i) break;
    }
    return ci;
}


int calc_value(vector<int> & v, vector<int> & c, vector<int> & cm,
    int xn, int yn, int x, int y, int val, int & bi) {
    int ans = 0;
    for (int xi = 0; xi < 8; xi ++) {
        int xx = x + xa[xi];
        int yy = y + ya[xi];
        printf("xx:%d  yy:%d\n", xx, yy);
        if (xx < 0 || xx >= xn || yy < 0 || yy >= yn) {
            continue;
        }
        int ii = xx + yn * yy;
        int ci = find_value(cm, ii, bi);
        if (xi == 0) {
            bi = ci;
        }
        printf("v[ci] is %d\n", v[ci]);
        ans = max(ans, abs(val - v[ci]));        
    }
    return ans;
}


void add_vvcc(vector<int> & vv, vector<int> & cc, int cnt, int val) {
    printf("add vvcc cnt:%d   val:%d\n", cnt, val);
    int nn = vv.size();
    if (nn == 0) {
        vv.push_back(val);
        cc.push_back(cnt);
        return;
    }
    if (vv[nn - 1] == val) {
        cc[nn - 1] += cnt;
    } else {
        vv.push_back(val);
        cc.push_back(cnt);
    }
}


void solve(vector<int> & v, vector<int> & c, int n) {
    int s = 0;
    int vn = v.size();
    vector<int> cm;
    for (int vi = 0; vi < vn; vi ++) {
        s += c[vi];
        cm.push_back(s);
    }
    int yn = n, xn = s / n;
    s = 0;
    vector<int> vv, cc;
    int bi = 0;
    for (int vi = 0; vi < vn; vi ++) {
        int s1 = s + yn + 1;
        int s2 = s + c[vi] - yn - 1;
        int s3 = s + c[vi];
        int si = s;
        for (; si < s1 && si < s3; si ++) {
            add_vvcc(vv, cc, 1, 
                calc_value(v, c, cm, xn, yn, si / yn, si % yn, v[vi], bi)
            );
        }
        if (s2 > si) {
            // 增加 s2 - si个0
            add_vvcc(vv, cc, s2 - si, 0);
            si = s2;
        } 
        for (; si < s3; si ++) {
            add_vvcc(vv, cc, 1, 
                calc_value(v, c, cm, xn, yn, si / yn, si % yn, v[vi], bi)
            );
        }
        s += c[vi]; 
    }
    printf("\nstart print ans\n");
    printf("%d\n", n);
    for (int i = 0, vvn = vv.size(); i < vvn; i ++) {
        printf("%d %d\n", vv[i], cc[i]);
    }
    printf("end print ans\n");
}



int main() {
    vector<int> vv, vc;
    int n;
    while(true) {
        scanf("%d", &n);
        if (n == 0) break;
        vv.clear();
        vc.clear();
        int v,c;
        while (true) {
            scanf("%d %d", &v, &c);
            if (v == 0 && c == 0) break;
            vv.push_back(v);
            vc.push_back(c);
        }
        solve(vv, vc, n);
    }    
    return 0;
}
