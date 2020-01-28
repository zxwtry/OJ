#include <bits/stdc++.h>
using namespace std;

const int N = (1 << 22) + (1 << 21);
char s[N];
char t[N];
int r[N];

void process();

int main() {
    if (getenv("ZXWPC")) {
        freopen("w001.in", "r", stdin);
        freopen("w001.out_2", "w", stdout);
    }
    int i;
    scanf("%d", &i);
    while (i --) {
        scanf("%s", s);
        process();
    }
    return 0;
}

void process() {
    int ans = 0;

    // s: 输入字符串
    // t: 处理之后的s
    // r: 半径

    // 将s处理成t
    t[0] = ' ';
    t[1] = '#';
    int tn = 2;
    for (int i = 0; s[i]; i ++) {
        t[tn ++] = s[i];
        t[tn ++] = '#';
    }

    // manacher核心流程
    // 关注两个变量
    // maxi: 碰到的最大i
    // lasti: 上一次更新maxi时候的i
    int maxi = 0, lasti = 0;
    for (int i = 1; i < tn; i ++) {
        // 初始化当前的r[i]
        r[i] = max(min(maxi - i, r[2 * lasti - i]), 1);
        // 接下来就是扩r[i]
        while (t[i - r[i]] == t[i + r[i]]) {
            r[i] ++;
        }
        //　更新maxi和lasti
        if (r[i] + i > maxi) {
            maxi = r[i] + i;
            lasti = i;
        }
        // 更新结果
        ans = max(ans, r[i] - 1);
    }
    // 打印
    printf("%d\n", ans);
}