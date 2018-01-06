/*
    url: leetcode.com/problems/jump-game-ii/
    jump: 519ms 14.29%
    jump2: 6ms 52.38%
*/

#include <stdio.h>
#include <stdlib.h>

int _min(int a, int b) {
    return a < b ? a : b;
}

int _max(int a, int b) {
    return a < b ? b : a;
}

int jump(int* n, int s) {
    int* m = (int*) malloc(sizeof(int) * (s));
    int i = 0, j = 0, mi = 0;
    m[s-1] = 0;
    for (i = s-2; i > -1; i --) {
        mi = s;
        for (j = i + 1; j < s && j - i <= n[i]; j ++) {
            mi = _min(mi, m[j] + 1);
        }
        m[i] = mi;
    }
    //save ans to mi
    mi = m[0];
    free(m);
    return mi;
}

int jump2(int* n, int s) {
    int ans = 0, mti = 0, cri = 0, i = 0;
    for (i = 0; i < s; i ++) {
        if (cri < i) {
            ans ++;
            cri = mti;
        }
        mti = _max(mti, n[i] + i);
    }
    return ans;
}

int main() {
    int n[] = {2, 3 ,1 ,1, 4};
    int s = 5;
    printf("answer is %d\r\n", jump2(n, s));
}