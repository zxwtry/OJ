/*
    url: leetcode.com/problems/reverse-words-in-a-string
    AC 3ms 6.58%
*/
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

void _swap(char* s, int i, int j) {
    char t = s[i];
    s[i] = s[j];
    s[j] = t;
}

void reverseWords(char *s) {
    int index = 0, preIndex = -1, i = 0, j = 0;
    int startIndex = 0, endIndex = strlen(s) - 1, cut = 0;
    while (startIndex <= endIndex && s[startIndex] == ' ') startIndex ++;
    while (endIndex >= startIndex && s[endIndex] == ' ') endIndex --;
    i = startIndex;
    j = endIndex;
    while (i < j) {
        _swap(s, i, j);
        i ++;
        j --;
    }
    preIndex = startIndex - 1;
    for (index = startIndex; index <= endIndex; index ++) {
        if (s[index] == ' ') {
            i = preIndex + 1;
            j = index - 1;
            while (i < j) {
                _swap(s, i, j);
                i ++;
                j --;
            }
            preIndex = index;
        } else if (index == endIndex) {
            i = preIndex + 1;
            j = index;
            while (i < j) {
                _swap(s, i, j);
                i ++;
                j --;
            }
        }
    }
    cut = startIndex;
    for (index = startIndex; index <= endIndex; index ++) {
        if (s[index] == ' ' && (index != startIndex && s[index-1] == ' ')) cut ++;
        s[index - cut] = s[index];
    }
    s[index - cut] = '\0';
}


int main(void) {
    char* s =  "  the sky   is    blue   ";
    char* t = (char*) malloc(sizeof(char) * (strlen(s) + 1));
    strcpy(t, s);
    
    printf("%s\n", t);
    reverseWords(t);
    printf("%s\n", t);
    printf("len1:%d len2:%d\n", strlen(s), strlen(t));
    return 0;
}