/*
    url: leetcode.com/problems/remove-nth-node-from-end-of-list/
    6ms 19.31%
*/

#include <stdlib.h>
#include <stdio.h>

struct ListNode {
    int val;
    struct ListNode *next;
};

//all head and n is valid
struct ListNode* removeNthFromEnd(struct ListNode* head, int n) {
    struct ListNode * front = head, * tail = head;
    int i = 0;
    for (i = 0; i < n; i ++) {
        front = front->next;
    }
    if (front == NULL) return head->next;
    front = front->next;
    while (front != NULL) {
        front = front->next;
        tail = tail->next;
    }
    tail->next = tail->next->next;
    return head;
}

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
    struct ListNode * l1 = l, * l2 = l;
    if (l == NULL) return;
    while (l1 != NULL) {
        l2 = l2->next;
        free(l1);
        l1 = l2;
    }
}

void print_ListNode(struct ListNode * h) {
    while (h != NULL) {
        printf("%d ", h->val);
        h = h->next;
    }
    printf("\r\n");
}

int main() {
    int a[] = {1, 2, 3, 4, 5};
    struct ListNode * head = convert_int_to_ListNode(a, 5);
    struct ListNode * answer = removeNthFromEnd(head, 2);
    print_ListNode(answer);
}