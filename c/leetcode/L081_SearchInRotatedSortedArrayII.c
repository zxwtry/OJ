/*
    url: leetcode.com/problems/search-in-rotated-sorted-array-ii
    AC 6ms 0.00%
*/

#include <stdio.h>
#include <stdlib.h>

#define bool int

bool _just(int* n, int ni, int nn, int t) {
    int nm = 0;
    if (ni > nn) return 0;
    while (ni < nn) {
        nm = (ni + nn) / 2;
        if (n[nm] == t) return 1;
        if (n[nm] > t) nn = nm - 1;
        else ni = nm + 1;
    }
    return 0;
}

bool _search(int*n ,int ni, int nn, int t) {
    int nm = (ni + nn) / 2;
    if (ni > nn) return 0;
    if (n[ni] == t || n[nn] == t || n[nm] == t) return 1;
    if (n[nm] > n[ni]) {
        if (_just(n, ni, nm, t)) return 1;
        return _search(n, nm+1, nn, t);
    } else if (n[nm] < n[ni]) {
        if (_just(n, nm+1, nn, t)) return 1;
        return _search(n, ni, nm - 1, t);
    } else {
        return _search(n, ni+1, nm-1, t) || _search(n, nm+1, nn-1, t);
    }
}

bool search(int* n, int nn, int t) {
     return _search(n, 0, nn-1, t);
}

int main() {
    int n[] = {3, 1, 1};
    printf("answer is %d\r\n", search(n, 3, 3));
}
