/*
    url: leetcode.com/problems/longest-palindromic-substring/
    85ms 42.99%
*/



#include <stdlib.h>
#include <stdio.h>
#include <string.h>

char _access_char_array(char* s, int i) {
    if (i % 2 == 0) return '\0';
    return *(s + i / 2);
}

char* longestPalindrome(char* s) {
    int n = strlen(s);
    int ma_n = 2 * n + 1;
    int ma_i = 0;
    int ma_li = 0;
    int ma_mi = 0;
    int ma_mt = 0;
    int l = 0, r = 0, i = 0, j = 0;
    int * ma_r = NULL;
    int ma_an = 0;
    char * answer = NULL;

    //manacher
    ma_r = (int *) malloc(sizeof(int) * ma_n);
    for (ma_i = 0; ma_i < ma_n; ma_i ++)
        *(ma_r + ma_i) = 0;
    for (ma_i = 0; ma_i < ma_n; ma_i ++) {
        if (ma_mt >= ma_n - 1) break;
        ma_mi = 2 * ma_li - ma_i;
        if (ma_i >= ma_mt || (ma_i + *(ma_r + ma_mi) == ma_mt)) {
            l = ma_i;
            r = ma_i;
            while ((l - 1) > -1 && (r + 1) < ma_n && _access_char_array(s, l - 1) == _access_char_array(s, r + 1)) {
                l --;
                r ++;
            }
            *(ma_r + ma_i) = (r - l) / 2;
            ma_an = *(ma_r + ma_an) > *(ma_r + ma_i) ? ma_an : ma_i;
            ma_li = ma_i;
        } else if (ma_i <= ma_mt) {
            *(ma_r + ma_i) = ma_mt - ma_i;
        } else {
            *(ma_r + ma_i) = *(ma_r + ma_mi);
        }
    }
    answer = (char *) malloc(sizeof(char) * (* (ma_r + ma_an) + 1));
    *(answer + *(ma_r + ma_an)) = '\0';
    l = ma_an - *(ma_r + ma_an);
    r = ma_an + *(ma_r + ma_an);
    for (i = l; i <= r; i ++) {
        if (i % 2 == 1)
            *(answer + (j ++)) = _access_char_array(s, i);
    }
    free(ma_r);
    return answer;

}

int main() {
    char* s = "cbbd";
    char* a = longestPalindrome(s);
    unsigned int i = 0;
    printf("len(a) is %d\r\n", strlen(a));
    for (i = 0; i < strlen(a); i ++)
        printf("%c", *(a + i));
    printf("\r\n");
    free(a);
    return 0;
}