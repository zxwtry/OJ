/*
    author:  zxwtry
    email:   zxwtry@qq.com
    project: ojcpp
    gcc:     4.8.4
    date:    2018-01-05 20:44:00
    status:  main_arr   1088K   516MS
    status:  main_map   3980K   735MS
*/

/*
    去重，排序
    使用cin>>s 会超时
    
    使用qsort_main同样会超时
*/

#include<iostream>
#include<stdio.h>
#include<string>
#include<algorithm>
#include<map>
#include<stdlib.h>

using namespace std;


void print_arr(int* a, int ai, int aj) {
    printf("starting...\n");

       for (int ak = ai; ak < aj; ak ++) {
        printf("%-4d   %d\n", ak, a[ak]);
    }
    printf("ending...\n");
}

int map_int[] = {
    2, 2, 2,
    3, 3, 3,
    4, 4, 4,
    5, 5, 5,
    6, 6, 6,
    7, 7, 7, 7,
    8, 8, 8,
    9, 9, 9, 9
};

int map_string_to_arr(string & s) {
    int si = 0;
    int sj = s.length();
    int v = 0;
    for (int sk = si; sk < sj; sk ++) {
        char c = s[sk];
        if (c >= 'A' && c <= 'Z') { 
            v = v * 10 +  map_int[c - 'A'];
        }
        if (c >= '0' && c <= '9') { 
            v = v * 10 + c - '0';
        }
    }
    return v;
}

int cmp(const void *a, const void *b) {
    return *(int *) a - *(int *) b;
}


void swap(int* a, int ai, int aj) {
    int t = a[ai];
    a[ai] = a[aj];
    a[aj] = t;
}

int qsort_part(int* a, int ai, int aj) {
    aj --;
    int v = a[ai];
    while (ai < aj) {
        while (ai < aj && a[aj] >= v) {
            aj --;
        } 
        a[ai] = a[aj];
        while (ai < aj && a[ai] <= v) {
            ai ++;
        }
        a[aj] = a[ai];
    }
    a[ai] = v;
    return ai;
}

void qsort_main(int* a, int ai, int aj) {
    if (ai < aj) {
        int ap = qsort_part(a, ai, aj);
        qsort_main(a, ai, ap);
        qsort_main(a, ap + 1, aj);
    }
}



int main_tle() {
    int t;
    string s;
    cin >> t;
    int* a = new int[t];
    for (int tk = 0; tk < t; tk ++) {
        cin >> s;
        a[tk] = map_string_to_arr(s);
    }
    qsort(a, t, sizeof(a[0]), cmp);   
    int k = a[0];
    int v = 1;
    bool out = false;
    for (int ak = 1; ak < t; ak ++) {
        if (k == a[ak]) {
            v ++;
        } else {
            if (v != 1) {
                printf("%03d-%04d %d\n", (int)(k / 10000), (int)(k % 10000), v);
                out = true;;
            }
            k = a[ak];
            v = 1;
        }
    }
    if (v != 1) {
        printf("%03d-%04d %d\n", (int)(k / 10000), (int)(k % 10000), v);
        out = true;;
    }
    if (! out) {
        printf("No duplicates.\n");
    }
    return 0;
}


int string_to_int(char* cs) {
    char c = cs[0];
    int ci = 1;
    int v = 0;
    while (c != 0) {
        if (c >= 'A' && c <= 'Z') {
            v = v * 10 + map_int[c - 'A'];
        }
        if (c >= '0' && c <= '9' ) {
            v = v * 10 + c - '0';
        }
        c = cs[ci ++];
    }
    return v;
}

int main() {
    int t;
    char cs[108];
    scanf("%d\n", &t);
    int* a = new int[t];
    for (int tk = 0; tk < t; tk ++) {
        scanf("%s", cs);
        //a[tk] = map_string_to_arr(s);
        a[tk] = string_to_int(cs);
    }
    qsort(a, t, sizeof(a[0]), cmp);   
    //qsort_main(a, 0, t);
    int k = a[0];
    int v = 1;
    bool out = false;
    for (int ak = 1; ak < t; ak ++) {
        if (k == a[ak]) {
            v ++;
        } else {
            if (v != 1) {
                printf("%03d-%04d %d\n", (int)(k / 10000), (int)(k % 10000), v);
                out = true;;
            }
            k = a[ak];
            v = 1;
        }
    }
    if (v != 1) {
        printf("%03d-%04d %d\n", (int)(k / 10000), (int)(k % 10000), v);
        out = true;;
    }
    if (! out) {
        printf("No duplicates.\n");
    }
    return 0;
}

int main_map() {
    int t;
    char cs[108];
    scanf("%d\n", &t);
    map<int, int> m;
    for (int tk = 0; tk < t; tk ++) {
        scanf("%s", cs);
        int v = string_to_int(cs);
        m[v] ++;
    }
    bool out = false;
    for (map<int, int>::iterator it = m.begin(); it != m.end(); it ++) {
        if (it->second > 1) {
            out = true;
            printf("%03d-%04d %d\n", (int)(it->first / 10000), (int)(it->first % 10000), it->second);
        }
    }
    if (! out) {
        printf("No duplicates.\n");
    }
    return 0;
}
