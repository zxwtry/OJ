/*
    url: leetcode.com/problems/merge-sorted-array
    AC 3ms 21.00%
*/

void merge(int* n1, int n1n, int* n2, int n2n) {
    int i1 = n1n -1, i2 = n2n - 1, i = n1n+n2n-1;
    while (i1 > -1 && i2 > -1) {
        if (n1[i1] > n2[i2]) {
            n1[i --] = n1[i1 --];
        } else {
            n1[i --] = n2[i2 --];
        }
    }
    while (i1 > -1) n1[i --] = n1[i1 --];
    while (i2 > -1) n1[i --] = n2[i2 --];
}