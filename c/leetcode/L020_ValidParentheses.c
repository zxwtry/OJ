/*
    url: leetcode.com/problems/valid-parentheses/
    3ms 23.11%
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define bool int

struct stack {
    char c;
    struct stack * next;
    struct stack * last;
};

struct stack * push_stack(char c, struct stack * stk) {
    struct stack * temp = (struct stack *) malloc(sizeof(struct stack));
    temp->next = NULL;
    temp->last = NULL;
    temp->c = c;
    if (stk != NULL)
        stk->next = temp;
    temp->last = stk;
    return temp;
};

struct stack * pop_stack(struct stack * stk) {
    struct stack * last = NULL;
    if (stk == NULL) return stk;
    last = stk->last;
    free(stk->next);
    return last;
};

bool isValid(char* s) {
    int i = 0, r = strlen(s);
    char c = '\0', t = '\0';
    struct stack * head = NULL;
    for (i = 0; i < r; i ++) {
        c = *(s + i);
        if (c == '(') {
            head = push_stack(')', head);
        } else if (c == '{') {
            head = push_stack('}', head);
        } else if (c == '[') {
            head = push_stack(']', head);
        } else if (head == NULL) {
            return 0;
        } else {
            t = head->c;
            head = head->last;
            if (t != c) {
                return 0;
            }
        }
    }
    //ans
    i = head == NULL;
    //free
    while (head != NULL) {
        head = pop_stack(head);
    }
    return i;
}

int main() {
    char *s = ")";
    printf("answer is %d\r\n", isValid(s));
}