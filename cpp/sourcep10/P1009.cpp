/*
    author:  zxwtry
    email:   zxwtry@qq.com
    project: ojcpp
    gcc:     4.8.4
    date:    2018-01-05 20:44:00
    status:  688K	16MS
*/

#include <iostream>
#include <stdio.h>
#include <string>
#include <algorithm>
#include <map>
#include <vector>
#include <stdlib.h>

using namespace std;

int xa[] = {-1, -1, -1, 0, 0, 1, 1, 1};
int ya[] = {-1, 0, 1, -1, 1, -1, 0, 1};

// 找到第一个大于p的下标
int get_vi(vector<int> &m, int ml, int mr, int p)
{
    mr--;
    while (ml < mr)
    {
        int mm = ml + (mr - ml) / 2;
        int cmp = m[mm] - p;
        if (cmp <= 0)
        {
            ml = mm + 1;
        }
        else
        {
            mr = mm;
        }
    }
    return ml;
}

int get_range(vector<int> &v, vector<int> &m, int xn, int yn, int x, int y, int val, int pi)
{
    int range = 0;
    int ml = 0, mr = (int)m.size();
    for (int i = 0; i < 8; i++)
    {
        int xx = x + xa[i], yy = y + ya[i];
        if (xx < 0 || xx >= xn || yy < 0 || yy >= yn)
            continue;
        // int pp = xx * yn + yy;
        int pp = pi;
        if (xa[i] != 0)
        {
            pp += (xa[i] > 0 ? yn : -yn);
        }
        pp += ya[i];
        range = max(range, abs(val - v[get_vi(m, ml, mr, pp)]));
    }
    return range;
}

void print_range(int *last, int range, int times)
{
    if (last[0] == range)
    {
        last[1] += times;
    }
    else
    {
        if (last[1] != 0)
        {
            printf("%d %d\n", last[0], last[1]);
        }
        last[0] = range;
        last[1] = times;
    }
}

void solve_one_line(vector<int> &v, vector<int> &c, vector<int> &m, int yn)
{
    int vn = (int)v.size();
    int last[2] = {0, 0};
    int pre_v = v[0];
    int now_v = v[0];
    int nxt_v;
    for (int i = 0; i < vn; i++)
    {
        nxt_v = i + 1 < vn ? v[i + 1] : now_v;
        if (c[i] > 2)
        {
            print_range(last, abs(now_v - pre_v), 1);
            print_range(last, 0, c[i] - 2);
            print_range(last, abs(nxt_v - now_v), 1);
        }
        else if (c[i] == 2)
        {
            print_range(last, abs(now_v - pre_v), 1);
            print_range(last, abs(nxt_v - now_v), 1);
        }
        else
        {
            print_range(last, max(abs(now_v - pre_v), abs(nxt_v - now_v)), 1);
        }
        pre_v = now_v;
        now_v = nxt_v;
    }
    printf("%d %d\n", last[0], last[1]);
}

void solve(vector<int> &v, vector<int> &c, vector<int> &m, int yn)
{
    int vn = (int)v.size();
    // 处理只有一行情况
    if (vn > 0 && m[vn - 1] == yn)
    {
        solve_one_line(v, c, m, yn);
        return;
    }
    int p = 0;
    int last[2] = {0, 0};
    int xn = m[vn - 1] / yn;
    for (int i = 0; i < vn; i++)
    {
        int p1 = p + yn + 1;
        int p2 = p + c[i] - yn - 1;
        int p3 = p + c[i];
        int pi = p;
        for (; pi < p1 && pi < p2; pi++)
        {
            print_range(last, get_range(v, m, xn, yn, pi / yn, pi % yn, v[i], pi), 1);
        }
        if (p2 > pi)
        {
            print_range(last, 0, p2 - pi);
        }
        pi = max(p2, pi);
        for (; pi < p3; pi++)
        {
            print_range(last, get_range(v, m, xn, yn, pi / yn, pi % yn, v[i], pi), 1);
        }
        p += c[i];
    }
    printf("%d %d\n", last[0], last[1]);
}

int main()
{
    int n, t, i;
    vector<int> v, c, m;
    if (getenv("zxw"))
    {
        freopen("D:/code/c/oj/data_in.txt", "r", stdin);
        freopen("D:/code/c/oj/data_out.txt", "w", stdout);
    }
    while (true)
    {
        t = 0;
        i = 0;
        scanf("%d", &n);
        if (n == 0)
            break;
        printf("%d\n", n);
        v.clear();
        c.clear();
        m.clear();

        while (true)
        {
            int vt, ct;
            scanf("%d %d", &vt, &ct);
            t += ct;
            if (vt == 0 && ct == 0)
                break;
            if (ct == 0)
                continue;
            if (i != 0 && vt == v[i - 1])
            {
                c[i - 1] += ct;
                m[i - 1] = t;
            }
            else
            {
                v.push_back(vt);
                c.push_back(ct);
                m.push_back(t);
                i++;
            }
        }
        solve(v, c, m, n);
        printf("0 0\n");
    }
    printf("0\n");
    if (getenv("zxw"))
    {
        fclose(stdin);
        fclose(stdout);
    }
    return 0;
}
