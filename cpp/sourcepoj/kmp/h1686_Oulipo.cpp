#include <bits/stdc++.h>
using namespace std;

// ac 78MS 2408K

char s[1 << 21];
char p[1 << 20];
int n[1 << 20];

void getNext(char *p, int pn);
int kmp(char *s, int sn, char *p, int pn);
void process();

int main() {
    if (getenv("ZXWPC")) {
        freopen("h1686.in", "r", stdin);
        freopen("h1686.out", "w", stdout);
    }
    int n;
    scanf("%d", &n);
    while (n--) {
        scanf("%s\n", p);
        scanf("%s\n", s);
        process();
    }
}

void process() {
    int sn = strlen(s);
    int pn = strlen(p);
    int count = kmp(s, sn, p, pn);
    printf("%d\n", count);
}

void getNext(char *p, int pn) {
    n[0] = -1;
    int fi = 0;
    int bi = -1;
    while (fi < pn) {
        if (-1 == bi || p[fi] == p[bi]) {
            bi++;
            fi++;
            n[fi] = bi;
        } else {
            bi = n[bi];
        }
    }
}

int kmp(char *s, int sn, char *p, int pn) {
    int si = 0;
    int pi = 0;
    int count = 0;
    getNext(p, pn);
    while (si != sn || pi == pn) {
        if (pi == -1 || s[si] == p[pi]) {
            si++;
            pi++;
        } else {
            pi = n[pi];
        }
        if (pi == pn) {
            count++;
            pi = n[pi];
        }
    }
    return count;
}
