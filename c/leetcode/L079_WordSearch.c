/*
    url: leetcode.com/problems/word-search
    AC 36ms 3.33%
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define bool int

bool search(char** b, int ri, int ci, int rn, int cn, char* w, int wi, int wn, char** m) {
    bool sign = 0;
    if (wi == wn) return 1;
    if (ri < 0 || ri >= rn || ci < 0 || ci >= cn) return 0;
    if (m[ri][ci] == 1) return 0;
    if (b[ri][ci] == w[wi]) {
        m[ri][ci] = 1;
        sign =  search(b, ri+1, ci, rn, cn, w, wi+1, wn, m) || 
                search(b, ri, ci+1, rn, cn, w, wi+1, wn, m) ||
                search(b, ri-1, ci, rn, cn, w, wi+1, wn, m) ||
                search(b, ri, ci-1, rn, cn, w, wi+1, wn, m);
        m[ri][ci] = 0;
        return sign;
    } else return 0;
}

bool exist(char** b, int rn, int cn, char* w) {
    char** m = (char**) malloc(sizeof(char*) * rn);
    int ri = 0, ci = 0, wn = strlen(w), k = 0, sign = 0;
    for (ri = 0; ri < rn; ri ++) {
        m[ri] = (char*) malloc(sizeof(char) * (cn+1));
        m[ri][cn] = '\0';
    }
    for (ri = 0; !sign && ri < rn; ri ++) {
        for (ci = 0; !sign && ci < cn; ci ++) {
            for (k = 0; k < rn ; k ++) memset(m[k], 0, cn);
            if (search(b, ri, ci, rn, cn, w, 0, wn, m)) sign = 1;
        }
    }
    for (ri = 0; ri < rn; ri ++) free(m[ri]);
    free(m);
    return sign;
}

int main() {
    char** b = (char**) malloc(sizeof(char*) * 3);
    int rn = 3, cn = 4;
    b[0] = "ABCE";
    b[1] = "SFES";
    b[2] = "ADEE";
    printf("answer is %d\r\n", exist(b, rn ,cn ,"ABCESEEEFS"));  
    printf("answer is %d\r\n", exist(b, rn ,cn ,"SEE"));  
    printf("answer is %d\r\n", exist(b, rn ,cn ,"ABCB"));  
}