// AC   124MS	13432K

#include <bits/stdc++.h>
using namespace std;
int n;
char s[1 << 21];
int nxt[1 << 21];

void getNext() {
    nxt[0] = -1;
    int fi = 0;
    int bi = -1;
    while (fi < n) {
        if (-1 == bi || s[fi] == s[bi]) {
            fi++;
            bi++;
            nxt[fi] = bi;
        } else {
            bi = nxt[bi];
        }
    }
}

struct Ans {
    int len;
    int times;
};

struct Ans *newAns(int len, int time) {
    struct Ans *one = (struct Ans *)malloc(sizeof(struct Ans));
    one->len = len;
    one->times = time;
    return one;
}

bool compare(const struct Ans &x, const struct Ans &y) {
    if (x.len < y.len) {
        return true;
    } else if (x.len > y.len) {
        return false;
    }

    if (x.times < y.times) {
        return true;
    } else if (x.times > y.times) {
        return false;
    }
    return false;
}

void process() {
    getNext();
    vector<struct Ans> v;
    map<int, bool> m;
    for (int i = 2; i <= n; i++) {
        // 查看是否有两个
        if (nxt[i] * 2 == i) {
            if (m.count(i) > 0)
                continue;
            v.push_back(*newAns(i, i / nxt[i]));
            m[i] = true;
            for (int k = i + nxt[i]; k <= n; k += nxt[i]) {
                if (m.count(k) > 0)
                    continue;
                if (nxt[k] < nxt[i]) {
                    break;
                }
                m[k] = true;
                v.push_back(*newAns(k, k / nxt[i]));
            }
        }
    }
    sort(v.begin(), v.end(), compare);
    for (vector<struct Ans>::iterator it = v.begin(); it != v.end(); ++it) {
        printf("%d %d\n", it->len, it->times);
    }
}

int main() {
    if (getenv("ZXWPC")) {
        freopen("h1358.in", "r", stdin);
        freopen("h1358.out", "w", stdout);
    }
    int i = 0;
    while (true) {
        scanf("%d", &n);
        if (n == 0) {
            break;
        }
        i++;
        scanf("%s\n", s);
        printf("Test case #%d\n", i);
        process();
        printf("\n");
    }

    return 0;
}