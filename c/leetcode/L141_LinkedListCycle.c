/*
    url: leetcode.com/problems/linked-list-cycle
    AC 6ms 19.26%
*/

struct ListNode {
    int val;
    struct ListNode *next;
};

typedef int bool;

bool hasCycle(struct ListNode *head) {
    struct ListNode * fast = head;
    struct ListNode * slow = head;
    while (1) {
        if (fast == 0 || slow == 0) return 0;
        if (fast->next == 0) return 0;
        fast = fast->next->next;
        slow = slow->next;
        if (fast == slow) return 1;
    }
    return 0;
}

