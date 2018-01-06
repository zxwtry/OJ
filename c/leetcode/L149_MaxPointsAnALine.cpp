#pragma warning(disable:4786)
/*
    url: leetcode.com/problems/max-points-on-a-line
    AC 26ms 14.44%
*/

#include <iostream>
#include <vector>
#include <map>

using namespace std;

struct Point {
    int x;
    int y;
    Point() : x(0), y(0) {}
    Point(int a, int b) : x(a), y(b) {}
};

class Solution {
public:
    int _max(int a, int b) {
        return a < b ? b : a;
    }
    double calc(int a, int b) {
        double ans = 0.0;
        int c = gcd(a < 0 ? -a: a, b < 0 ? -b : b);
        a /= c;
        b /= c;
        if (a < 0 && b < 0) {
            a = -a;
            b = -b;
        } else if ((a < 0 && b > 0) || (a > 0 && b < 0)) {
            a = -(a < 0 ? -a : a);
            b = b < 0 ? -b : b;
        }
        ans += a;
        ans = ans * 200000000;
        ans += b;
        return ans;
    }
    int gcd(int a, int b) {
        int t = 0;
        if (a < b) return gcd(b, a);
        if (b == 0) return a;
        while (b != 0) {
            t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
    int maxPoints(vector<Point>& ps) {
        map<double, int > m;
        int ans = 0;
        int pl = ps.size();
        int i = 0, j = 0, ix = 0, iy = 0;
        int jx = 0, jy = 0, cnt = 0, mm = 0;
        double f = 0.0;
        for (i = 0; i < pl; i ++) {
            m.clear();
            ix = ps[i].x;
            iy = ps[i].y;
            cnt = 0;
            mm = 1;
            for (j = 0; j < pl; j ++) {
                if (i == j) continue;
                jx = ps[j].x;
                jy = ps[j].y;
                if (ix == jx && iy == jy) {
                    cnt ++;
                    continue;
                }
                if (ix == jx) {
                    f = 1.0E38f;
                } else {
                    f = calc(ix-jx, iy-jy);
                    //f = ((float)(iy-jy)) / (ix-jx);
                }
                if (m.count(f)) {
                    m[f] = m[f] + 1;
                } else {
                    m[f] = 2;
                }
                mm = _max(mm, m[f]);
            }
            ans = _max(ans, mm + cnt);
        }
        return ans;
    }
};


int main(void) {
    vector<Point > ps;
    ps.push_back(Point(1, 1));
    ps.push_back(Point(-1, -1));
    ps.push_back(Point(0, 0));
    ps.push_back(Point(0, 0));
    cout<<Solution().maxPoints(ps)<<endl;
    return 0;
}