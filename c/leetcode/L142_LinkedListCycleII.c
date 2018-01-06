/*
    url: /leetcode.com/problems/linked-list-cycle-ii
    AC 6ms 28.24%
*/

#include <stdio.h>
#include <stdlib.h>

struct ListNode {
    int val;
    struct ListNode *next;
};

struct ListNode *detectCycle(struct ListNode *head) {
    struct ListNode * fast = head;
    struct ListNode * slow = head;
    while (1) {
        if (fast == 0 || slow == 0) return NULL;
        if (fast->next == 0) return 0;
        fast = fast->next->next;
        slow = slow->next;
        if (fast == slow) break;
    }
    fast = head;
    while (fast != slow) {
        fast = fast->next;
        slow = slow->next;
    }
    return fast;
}