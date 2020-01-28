#include<stdio.h>
#include<stdlib.h>
#include<cstring>
using namespace std;

char s[1 << 22];
int next[1 << 22];
int sn;
void process(const char*, const int);
void processKMPGetNext(const char*, const int);
void processKMP(const char*, const int);

int main() {
    if (getenv("ZXWPC")) {
        freopen("p2406.in", "r", stdin);
        freopen("p2406.out", "w", stdout);
    }

    while (true) {
        scanf("%s\n", s);
        sn = s == NULL ? 0 : strlen(s);
        if (sn == 1 && s[0] == '.') {
            break;
        }
        processKMP(s, sn);
    }
    return 0;
}


void process(const char* s, const int sn) {
    for (int si = 1; si <= sn / 2; si ++) {
        if (sn % si != 0) {
            continue;
        }
        // 总共有 sn / si 个片段，判断每个片段是否相同就行。
        int count = 0;
        for (int index = 0; index < si; index ++) {
            int i = index;
            char pre = s[i];
            while (i < sn) {
                if (s[i] != pre) {
                    pre = s[i];
                    break;
                }
                i += si;
            }
            if (pre != s[index]) {
                continue;
            }
            count ++;
        }
        if (count == si) {
            printf("%d\n", sn / si);
            return;
        }
    }
    printf("1\n");
}

void processKMPGetNext(const char* s, const int sn) {
    next[0] = -1;
    int fi = 0;
    int bi = -1;
    while (fi != sn) {
        if (bi == -1 || s[fi] == s[bi]) {
            fi ++;
            bi ++;
            next[fi] = bi;
        } else {
            bi = next[bi];
        }
    }
}

void processKMP(const char* s, const int sn) {
    processKMPGetNext(s, sn);
    int range = sn - next[sn];
    if (sn % range == 0) {
        printf("%d\n", sn / range);
    } else {
        printf("1\n");
    }
 }