/*
    url: leetcode.com/problems/string-to-integer-atoi/
    25ms 12.81%
*/

#include <stdlib.h>
#include <stdio.h>
#include <limits.h>

int myAtoi(char* str) {
    int index = 0;
    int a = 0;
    int s = 1;
    char c = '\0';
    while (*(str + index) == ' ')
        index ++;
    if ('+' == *(str + index)) {
        index ++;
    }  else if ('-' == *(str + index)) {
        s = -1;
        index ++;
    }
    while (1) {
        c = *(str + (index ++));
        if (c < '0' || c > '9')
            break;
        if (a > 0 && a < 10 && a * s < 0)
            a = -a;
        if (a > INT_MAX / 10 || (a == INT_MAX / 10 && c - '0' > INT_MAX % 10))
            return INT_MAX;
        else if (a < INT_MIN / 10 || (a == INT_MIN / 10 && c - '0' > -(INT_MIN % 10)))
            return INT_MIN;
        a = a * 10 + (s * (c - '0'));
    }
    return a;
}

int main() {
    //sample of atoi
    int n; 
    char *str = "2147483648"; 

    long l = INT_MIN;
    int v = (int)l;

    printf("int_max is %d\t%d\r\n", v, INT_MIN);

    printf("my answer is %d\r\n", myAtoi(str));

    n = atoi(str); 
    printf("string = %s integer = %d\n", str, n); 
}