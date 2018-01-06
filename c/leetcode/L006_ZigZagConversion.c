/*
    url: leetcode.com/problems/zigzag-conversion/
    25ms 45.36%
*/

#include <string.h>
#include <stdio.h>
#include <stdlib.h>

char _access_char_array(char* s, int n, int t, int i, int j) {
    int index = t * j + i;
    if (index >= n) return '\0';
    return *(s + index);
}

char* convert(char* s, int r) {
    int n = strlen(s);
    char *a = (char *) malloc(sizeof(char) * (n + 1));
    int t = r == 1 ? 1 : 2 * r - 2, i = 0, j = 0, ai = 0, col = n / r + 1;
    char c = '\0';
    *(a + n) = '\0';
    for (i = 0; i < r; i ++) {
        for (j = 0; j < col; j ++) {
             c = _access_char_array(s, n, t, i, j);
             if (c != '\0')
                *(a + (ai ++)) = c;
            if (i != 0 && i != r - 1 && r > 1) {
                c = _access_char_array(s, n, t, t - i, j);
                if (c != '\0')
                    *(a + (ai ++)) = c;
            }
        }
    }
    return a;
}

int main() {
    char *s = "A";
    int r = 1;
    char *a = convert(s, r);
    unsigned int i = 0;
    for (i = 0; i < strlen(a); i ++) {
        printf("%c", *(a + i));
    }
    printf("\r\n");
    free(a);
    return 0;
}