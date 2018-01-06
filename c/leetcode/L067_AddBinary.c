/*
    url: leetcode.com/problems/add-binary
*/

#include <stdio.h>
#include <stdlib.h>

char* addBinary(char* a, char* b) {
    int ai = 0, bi = 0, c = 0, ansi = 0;
    char * ans = NULL;
    while (a[ai] != '\0') ai ++;
    while (b[bi] != '\0') bi ++;
    ans = (char*) malloc(sizeof(ai + bi + 1));
    ai --;
    bi --;
    ans[ai + bi] = '\0';
    ansi = ai + bi - 1;
    while (ai > -1 && bi > -1) {
        c = a[ai] - '0' + b[bi] - '0' + c;
        ans[ansi] = (char)('0' + (c % 2));
        c = c / 2;
        ai --;
        bi --;
        ansi --;
    }
    while (ai > -1) {
        c = a[ai] - '0'  + c;
        ans[ansi] = (char)('0' + (c % 2));
        c = c / 2;
        ai --;
        ansi --;
    }
    while (bi > -1) {
        c = b[bi] - '0' + c;
        ans[ansi] = (char)('0' + (c % 2));
        c = c / 2;
        bi --;
        ansi --;
    }
    if (c != 0) {
        ans[ansi] = (char)('0' + (c % 2));
        ansi --;
    }
    return ans + ansi + 1;
}

int main() {
    char* a = "11";
    char* b = "1";
    char* ans = addBinary(a, b);
    printf("answer is: \r\n%s\r\n", ans);
    //free(ans);
}