/*
    url: leetcode.com/problems/longest-substring-without-repeating-characters/
    19ms 60.40%
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int lengthOfLongestSubstring(char* s) {
    int m[128];
    int i = 0, j = 0;
    int n = 0, p = 0;
    int * c = NULL;
    int max = 0;
    int v = 0;
    if (s == NULL || * s == '\0')
        return 0;
    for (i = 0; i < 128; i ++)
        m[i] = -1;
    n = strlen(s);
    c = (int *) malloc(sizeof(int) * n);
    for (i = 0,j = 0; j < n; j ++) {
        v = *(s + j);
        p = m[v];
        m[v] = j;
        if (p >= i) {
            max = max < j - i ? j - i : max;
            i = p + 1;
        }
    }
    max = max < j - i ? j - i : max;
    free(c);
    return max;
}

int main() {
    char s[] = "pwwkew";
    printf("answer is %d\r\n", lengthOfLongestSubstring(s));
    return 0;
}