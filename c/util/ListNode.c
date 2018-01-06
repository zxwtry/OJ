#include <stdio.h>
#include <stdlib.h>

struct ListNode {
    int val;
    struct ListNode * next;
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
    int arr[] = {1, 2, 3, 4};
    struct ListNode * l = convert_int_to_ListNode(arr, 4);
    free_ListNode(l);
    return 0;
}
