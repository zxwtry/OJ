/*
    url: leetcode.com/problems/rotate-list
    AC 6ms 31.94%
*/

#include <stdio.h>
#include <stdlib.h>

typedef struct ListNode sln;
typedef struct ListNode * pln;

struct ListNode {
    int val;
    struct ListNode *next;
};

pln rotateRight(pln h, int k) {
    pln t = h, a = NULL;
    int hn = 1;
    if (h == NULL) return h;
    while (t->next != NULL) {
        hn ++;
        t = t->next;
    }
    if (hn == 1) return h;
    t->next = h;
    k =hn - (k % hn);
    t = h;
    printf("h val is %d\r\n", h->val);
    for (hn = 1; hn < k; hn ++)
        t = t->next;
    a = t->next;
    t->next = NULL;
    return a;
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

int main() {
    int arr[] = {0, 1, 2, 3, 4, 5, 6};
    pln h = convert_int_to_ListNode(arr, 7);
    pln a = rotateRight(h, 8);
    pln t = a;
    while (t != NULL) {
        printf("%d ", t->val);
        t = t->next;
    }

    printf("\r\n");
}