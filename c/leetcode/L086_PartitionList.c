/*
    url: leetcode.com/problems/partition-list
    AC 3ms 19.74%
*/

#include <stdio.h>
#include <stdlib.h>

typedef struct ListNode * pln;
typedef struct ListNode sln;

struct ListNode {
    int val;
    struct ListNode *next;
};

void solve(pln* sh, pln* st, pln* t) {
    if (*sh == NULL) {
        *sh = *st = *t;
    } else {
        (*st)->next = *t;
        *st = *t;
    }
}

pln partition(pln head, int x) {
    pln sh = NULL, bh = NULL;
    pln st = NULL, bt = NULL;
    pln t = head;
    while (t != NULL) {
        if (t->val < x) {
            solve(&sh, &st, &t);
        } else {
            solve(&bh, &bt, &t);
        }
        t = t->next;
    }
    if (sh == NULL) return bh;
    if (bh == NULL) return sh;
    st->next = bh;
    bt->next = NULL;
    return sh;
}

pln ln_construct(int* a, int n) {
    pln* l = (pln*) malloc(sizeof(pln) * n);
    int i = 0;
    pln ans = NULL;
    for (i = n-1; i > -1; i --) {
        l[i] = (pln) malloc(sizeof(sln));
        l[i]->next = i == n-1 ? NULL: l[i+1];
        l[i]->val = a[i];
    }
    if (n-1 > -1)
        l[n-1]->next = NULL;
    ans = l[0];
    free(l);
    return ans;
}

void ln_print(pln l) {
    while (l != NULL) {
        printf("%d ", l->val);
        l = l->next;
    }
    printf("\r\n");
}

void ln_free(pln l) {
    pln i = l, j = NULL;
    while(i != NULL) {
        j = i->next;
        free(i);
        i = j;
    }
}

int main() {
    int n[] ={1,4,3,2,5,2};
    pln l = ln_construct(n, 6);
    pln a = partition(l, 3);
    ln_print(a);
    ln_free(l);
}