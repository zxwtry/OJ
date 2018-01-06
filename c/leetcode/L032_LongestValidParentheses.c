/*
    url: leetcode.com/problems/longest-valid-parentheses/
    9ms 21.28%
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//vc do not need below, leetcode need
#define max(a,b) ((a) > (b) ? (a) : (b))
#define min(a,b) ((a) < (b) ? (a) : (b))

struct stk {
    int val;
    struct stk * pre;
    struct stk * nxt;
};

struct stk * stk_push(struct stk * top, int val) {
    struct stk * t = (struct stk *) malloc(sizeof(struct stk));
    t->val = val;
    t->pre = top;
    t->nxt = NULL;
    if (top != NULL) top->nxt = t;
    return t;
};

struct stk * stk_pop(struct stk * top) {
    struct stk * t = top == NULL ? NULL : top->pre;
    if (top == NULL) return NULL;
    if (t != NULL) t->nxt = NULL;
    free(top);
    return t;
};

void stk_free(struct stk * top) {
    struct stk * t = NULL;
    while (top != NULL) {
        t = top->pre;
        free(top);
        top = t;
    }
}

int longestValidParentheses(char* s) {
    int lft = 0, rgt = 0, cnt = 0, ans = 0, i = 0, len = 0;
    struct stk * top = NULL;
    char * m = NULL;
    len = s == NULL ? 0 : strlen(s);
    for (lft = 0, rgt = 0, i = 0; i < len; i ++) {
        if (* (s + i) == '(') lft ++;
        else rgt ++;
    }
    if (lft == 0 || rgt == 0) return 0;
    m = (char *) malloc(sizeof(char) * len);
    memset(m, '\0', len);
    for (i = 0; i < len; i ++) {
        if (*(s + i) == '(') {
            top = stk_push(top, i);
        } else if (top != NULL) {
            *(m + i) = 1;
            *(m + top->val) = 1;
            top = stk_pop(top);
        }
    }
    for (i = 0; i < len; i ++) {
        cnt = *(m + i) == 1 ? cnt+1 : 0;
        ans = max(ans, cnt);
    }
    free(m);
    stk_free(top);
    return ans;
}

int main() {
    char * s = "(())";
    printf("answer is %d\r\n", longestValidParentheses(s));
    return 0;
}