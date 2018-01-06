/*
    url: leetcode.com/problems/add-two-numbers/
    32ms 62.62%
*/

#include <stdlib.h>

struct ListNode {   
    int val;
    struct ListNode *next;
};

struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2) {
    struct ListNode *head = l1;
    struct ListNode *cur = l1;
    struct ListNode *tmp = NULL;
    int assist = 0;
    while (l1 != NULL) {
        assist += l1->val;
        if (l2 != NULL) {
            assist += l2->val;
            l2 = l2->next;
        }
        l1->val = assist % 10;
        assist = assist / 10;
        cur = l1;
        l1 = l1->next;
    }
    if (l2 != NULL) {
        cur->next = l2;
        while(l2 != NULL) {
            if (assist == 0) break;
            assist += l2->val;
            l2->val = assist % 10;
            assist = assist / 10;
            cur = l2;
            l2 = l2->next;
        }
    }
    if (assist != 0) {
        tmp = (struct ListNode *) malloc(sizeof(struct ListNode));
        tmp->val = assist;
        tmp->next = NULL;
        cur->next = tmp;
    }
    return head;
}

int main() {
    
}