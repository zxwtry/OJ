/*
    url: leetcode.com/problems/longest-common-prefix/

*/

#include <stdlib.h>
#include <string.h>

char* longestCommonPrefix(char** strs, int strsSize) {
    int len = 0;
    int sign = 1;
    int i = 0;
    char c = '\0';
    char nc = '\0';
    char * a = NULL;
    while (sign) {
        c = *(*(strs + 0) + len);
        if (c == '\0') break;
        for (i = 1; i < strsSize; i ++) {
            nc = *(*(strs + i) + len);
            if (nc != c) sign = 0;
        }
        if (sign) len ++;
    }
    a = (char *) malloc(sizeof(char) * (len + 1));
    *(a + len) = '\0';
    for (i = 0; i < len; i ++)
        *(a + i) = *(*strs + i);
    return a;
}

int main() {
    char * sa = "abc";
    char * sb = "";
    char ** strs = (char **) malloc(sizeof(char *) * 2);
    int strsSize = 2;
    int i = 0;
    char * a = NULL;
    *(strs + 0) = sa;
    *(strs + 1) = sb;
    a = longestCommonPrefix(strs, strsSize);
    printf("answer is ");
    for (i = 0; i < strlen(a); i ++) {
        printf("%c", *(a + i));
    }
    free(a);
    free(strs);
    return 0;
}