/*
    url: leetcode.com/problems/merge-two-sorted-lists/
    9ms 7.76%
*/

#include <stdio.h>
#include <stdlib.h>

struct ListNode {
    int val;
    struct ListNode * next;
};

struct ListNode* mergeTwoLists(struct ListNode* l1, struct ListNode* l2) {
    struct ListNode * save1 = l1;
    struct ListNode * travel = NULL;
    struct ListNode * tmp = NULL;
    if (l1 == NULL) return l2;
    else if (l2 == NULL) return l1;
    if (l1->val > l2->val)
        return mergeTwoLists(l2, l1);
    while (l1 != NULL && l2 != NULL) {
        if (l1->val > l2->val) {
            tmp = l2->next;
            travel->next = l2;
            l2 = tmp;
            travel = travel->next;
        } else {
            tmp = l1->next;
            if (travel == NULL) {
                travel = l1;
            } else {
                travel->next = l1;
                travel = travel->next;
            }
            l1 = tmp;
        }
    }
    if (l1 != NULL) {
        travel->next = l1;
    }
    if (l2 != NULL) {
        travel->next = l2;
    }
    return save1;
}
