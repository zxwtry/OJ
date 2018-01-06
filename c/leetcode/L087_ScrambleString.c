/*
    url: leetcode.com/problems/scramble-string
    AC 3ms 50.00%
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define bool int

bool equals(char* s1, int i1, int j1, char* s2, int i2, int j2) {
    int k = 0;
    if (j1 - i1 != j2 - i2 || i1 > j1) return 0;
    for (k = j1-i1; k > -1; k --)
        if (s1[i1+k] != s2[i2+k]) return 0;
    return 1;
}

bool search(char* s1, int i1, int j1, char* s2, int i2, int j2, int* m) {
    int i = 0;
    if (equals(s1, i1, j1, s2, i2, j2)) return 1;
    for (i = 0; i < 26; i ++) m[i] = 0;
    for (i = i1; i <= j1; i ++) m[s1[i]-'a'] ++;
    for (i = i2; i <= j2; i ++) {
        m[s2[i] - 'a'] --;
        if (m[s2[i] - 'a'] < 0) return 0; 
    }
    for (i = j1-i1-1; i > -1; i --) {
        if (search(s1, i1, i1+i, s2, i2, i2+i, m) && 
            search(s1, i1+i+1, j1, s2, i2+i+1, j2, m)) return 1;
        if (search(s1, i1, i1+i, s2, j2-i, j2, m) && 
            search(s1, i1+i+1, j1, s2, i2, j2-i-1, m)) return 1;
    }
    return 0;
}

bool isScramble(char* s1, char* s2) {
    int j1 = s1 == NULL ? -1 : strlen(s1)-1;
    int j2 = s2 == NULL ? -1 : strlen(s2)-1;
    int i1 = 0, i2 = 0, m[26];
    if (j1 < 0 || j2 < 0) return j1 == j2;
    return search(s1, i1, j1, s2, i2, j2, m);
}

int main() {
    char* s1 = "abc";
    char* s2 = "cba";
    printf("answer is %d\r\n", isScramble(s1, s2));
    return 0;
}