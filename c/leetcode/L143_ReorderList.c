/*
    url: leetcode.com/problems/reorder-list
*/

#include <stdlib.h>
#include <stdio.h>

struct ListNode {
    int val;
    struct ListNode *next;
};

void reorderList(struct ListNode* head) {
    int len = 0, i = 0;
    struct ListNode * t = head;
    struct ListNode * r = NULL;
    struct ListNode * p = NULL;
    struct ListNode * n = NULL;

    while (t != NULL) {
        t = t->next;
        len ++;
    }
    
    if (len < 3) return;
    
    t = head;
    for (i = (len-1)/2; i > 0; i --) {
        t = t->next;
    }
    
    r = t->next;
    t->next = NULL;

    while (r != NULL) {
        n = r->next;
        
        r->next = p;
        p = r;

        r = n;
    }

    t = head;
    r = p;
    while (r != NULL) {
        p = r->next;
        n = t->next;

        t->next = r;
        r->next = n;

        r = p;
        t = n;
    }

}

int main() {
    struct ListNode * * ls ;
    struct ListNode * t;
    int n = 21, i = 0;

    ls = (struct ListNode * *) malloc(sizeof(struct ListNode *) * n);

    for (i = n-1; i > -1; i --) {
        ls[i] = (struct ListNode *) malloc(sizeof(struct ListNode));
        ls[i]->next = i == n-1 ? NULL : ls[i+1];
        ls[i]->val = i;
    }

    reorderList(ls[0]);

    t = ls[0];

    while (t != NULL) {
        printf("%d ", t->val);
        t = t->next;
    }
    printf("\n");

    return 0;
}