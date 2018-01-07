/*
    author:  zxwtry
    email:   zxwtry@qq.com
    project: ojcpp
    gcc:     4.8.4
    date:    2018-01-05 20:44:00
    status:  724K   16MS
*/


/*
    玛雅日历：
    365天
    19月
    前18个月，20天

    20 时期
    每个时期   13天
*/


#include<iostream>
#include<stdio.h>
#include<string>
#include<algorithm>
#include<map>
#include<vector>

using namespace std;

string month1[] = {
    "pop", "no", "zip", "zotz", "tzec",
    "xul", "yoxkin", "mol", "chen", "yax",
    "zac", "ceh", "mac", "kankin", "muan",
    "pax", "koyab", "cumhu", "uayet"
};


string month2[] = {
    "imix", "ik", "akbal", "kan", "chicchan", 
    "cimi", "manik", "lamat", "muluk", "ok", 
    "chuen", "eb", "ben", "ix", "mem", 
    "cib", "caban", "eznab", "canac", "ahau"
};


long long get_day(string & d, string & m, int year) {
    long long day = 365 * year;
    int mn = 19, mi = 0;
    for (; mi < mn; mi ++) {
        if (month1[mi] == m) break;
    }
    day += 20 * mi;
    day += atoi(d.substr(0, d.size() - 1).c_str());
    return day;
}


int main() {
    int n, y1;
    string d1, m1;
    cin>>n;
    cout<<n<<endl;
    for (int i = 0; i < n; i ++) {
        cin>>d1>>m1>>y1;
        int day = get_day(d1, m1, y1);
        int d2 = (day % 13) + 1;
        string m2 = month2[day % 20];
        int y2 = day / 260;
        cout<<d2<<" "<<m2<<" "<<y2<<endl;
    }
    return 0;
}
