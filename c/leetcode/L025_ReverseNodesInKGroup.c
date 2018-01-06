/*
    url: leetcode.com/problems/reverse-nodes-in-k-group/
    12ms 7.08%
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

struct ListNode* reverseKGroup(struct ListNode* head, int k) {
    int i = 0;
    struct ListNode * s = head, * t = NULL, * v = NULL, * pv = NULL;
    struct ListNode * t1 = NULL, * t2 = NULL, * t0 = NULL;
    struct ListNode * answer = head;
    if (k < 2 || head == NULL) return answer;
    while (s != NULL) {
        t = s;
        for (i = 1; i < k; i ++) {
            if (t == NULL) break;
            t = t->next;
        }
        if (pv != NULL) {
            pv->next = t == NULL ? s : t;
        }
        pv = s;
        if (t == NULL) break;
        if (answer == head) answer = t;
        v = t->next;

        t0 = s;
        t1 = t0->next;
        t2 = t1 != NULL ? t1->next : NULL;
        while (1) {
            t1->next = t0;
            if (t1 == t) break;
            t0 = t1;
            t1 = t2;
            t2 = t2 == NULL ? NULL : t2->next;
        }
        s = v;
    }
    if (s == NULL)
        pv->next = NULL;
    return answer;
}

int main() {
    int a[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int k = 11;
    struct ListNode * head = convert_int_to_ListNode(a, 10);
    struct ListNode * answer = reverseKGroup(head, k);
    print_ListNode(answer);
    free_ListNode(answer);
}