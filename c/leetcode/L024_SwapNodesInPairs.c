/*
    url: leetcode.com/problems/swap-nodes-in-pairs/
    6ms 0.0%
*/

#include <stdio.h>
#include <stdlib.h>

struct ListNode {
    int val;
    struct ListNode *next;
};

struct ListNode * convert_int_to_ListNode(int * arr, int n) {
    struct ListNode * head = NULL;
    struct ListNode * travel = NULL;
    struct ListNode * temp = NULL;
    int i = 0;
    if (n == 0 || n < 0) return NULL;
    travel = (struct ListNode *) malloc(sizeof(struct ListNode));
    travel->val = *(arr + 0);
    travel->next = NULL;
    head = travel;
    for (i = 1; i < n; i ++) {
        temp = (struct ListNode *) malloc(sizeof(struct ListNode));
        temp->val = *(arr + i);
        temp->next = NULL;
        travel->next = temp;
        travel = travel->next;
    }
    return head;
}

void free_ListNode(struct ListNode * l) {
    struct ListNode * temp = NULL;
    while (l != NULL) {
        temp = l->next;
        free(l);
        l = temp;
    }
}

void print_ListNode(struct ListNode * h) {
    while (h != NULL) {
        printf("%d ", h->val);
        h = h->next;
    }
    printf("\r\n");
}

struct ListNode* swapPairs(struct ListNode* head) {
    struct ListNode * t1 = head, * t2 = NULL, * t3 = NULL, * t0 = NULL;
    struct ListNode * answer = head;
    t2 = t1 == NULL ? NULL : t1->next;
    t3 = t2 == NULL ? NULL : t2->next;
    while (t2 != NULL) {
        t2->next = t1;
        if (t0 != NULL)
            t0->next = t2;
        if (head == answer)
            answer = t2;
        t0 = t1;
        t1 = t3;
        t2 = t1 == NULL ? NULL : t1->next;
        t3 = t2 == NULL ? NULL : t2->next;
    }
    if (t0 != NULL)
        t0->next = t1;
    return answer;
}

int main() {
    int a[] = {1};
    struct ListNode * l = convert_int_to_ListNode(a, 1);
    struct ListNode * answer = swapPairs(l);
    print_ListNode(answer);
    free_ListNode(answer);
}