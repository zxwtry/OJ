/*
    url: leetcode.com/problems/evaluate-reverse-polish-notation
    AC 6ms 12.50%
*/

#include <stdlib.h>
#include <stdio.h>


int evalRPN(char** tokens, int tokensSize) {
    int* help = (int*)malloc(sizeof(int) * 10000);
    int helpIndex = 0;
    int index = 0, u = 0, v = 0;
    for (index = 0; index < tokensSize; index ++) {
        switch(tokens[index][0]) {
        case '+':
            u = help[helpIndex - 1];
            v = help[helpIndex - 2];
            helpIndex -= 2;
            help[helpIndex ++] = v + u;
        	break;
        case '-':
            if (tokens[index][1] == '\0') {
                u = help[helpIndex - 1];
                v = help[helpIndex - 2];
                helpIndex -= 2;
                help[helpIndex ++] = v - u;
            } else {
                u = 0;
                for (v = 1; ; v ++) {
                    if (tokens[index][v] == '\0') break;
                    u = u * 10;
                    u += tokens[index][v] - '0';
                }
                help[helpIndex ++] = -u;
            }
        	break;
        case '*':
            u = help[helpIndex - 1];
            v = help[helpIndex - 2];
            helpIndex -= 2;
            help[helpIndex ++] = v * u;
        	break;
        case '/':
            u = help[helpIndex - 1];
            v = help[helpIndex - 2];
            helpIndex -= 2;
            help[helpIndex ++] = v / u;
        	break;
        default:
            u = 0;
            for (v = 0; ; v ++) {
                if (tokens[index][v] == '\0') break;
                u = u * 10;
                u += tokens[index][v] - '0';
            }
            help[helpIndex ++] = u;
            break;
        }
    }
    u = help[0];
    free(help);
    return u;
}

int main(void) {
    char* a = "-2";
    char* b = "1";
    char* c = "+";
    char* d = "3";
    char* e = "*";
    int zn = 5;
    char** z = (char**)malloc(sizeof(char*) * zn);
    z[0] = a;
    z[1] = b;
    z[2] = c;
    z[3] = d;
    z[4] = e;
    printf("answer is %d\n", evalRPN(z, zn));
    free(z);
    return 0;
}