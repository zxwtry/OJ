/*
    url: leetcode.com/problems/merge-k-sorted-lists/
    12ms 74.53%
*/

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

struct ListNode {
    int val;
    struct ListNode *next;
};

void print_ListNode(struct ListNode * h) {
    while (h != NULL) {
        printf("%d ", h->val);
        h = h->next;
    }
    printf("\r\n");
}

int cmp(struct ListNode * l1, struct ListNode * l2) {
    if (NULL == l1 && NULL == l2) return 0;
    if (l1 == NULL) return 1;
    if (l2 == NULL) return -1;
    if (l1->val < l2->val)
        return -1;
    if (l1->val > l2->val)
        return 1;
    return 0;
}

void swap(struct ListNode ** l1, struct ListNode ** l2) {
    struct ListNode * t = *l1;
    *l1 = *l2;
    *l2 = t;
}

void swap_heap(struct ListNode * heap, int i, int j) {
    struct ListNode t = heap[i];
    heap[i] = heap[j];
    heap[j] = t;
}

struct ListNode* mergeKLists(struct ListNode** lists, int listsSize) {
    struct ListNode* head = (struct ListNode *) malloc(sizeof(struct ListNode));
    struct ListNode* travel = head;
    struct ListNode* temp = NULL;
    struct ListNode* heap = (struct ListNode *) malloc(sizeof(struct ListNode) * listsSize);
    int i = 0, p = 0, c = 0, hi = 0;
    if (listsSize == 0) return NULL; 
    head->next = NULL;
    for (i = 0; i < listsSize; i ++) {
        if (*(lists+ i) != 0)        
        *(heap + (hi ++)) = *(*(lists + i)); 
    }
    //build heap
    for (i = (listsSize - 1) / 2; i > -1; i --) {
        //heap down
        p = i;
        c = 2 * p + 1;
        while (c < hi) {
            if (c + 1 < hi && cmp(heap + c  + 1, heap + c) < 0) c ++;
            if (cmp(heap + p, heap + c) > 0) {
                swap_heap(heap, p, c);
            } else break;
            p = c;
            c = 2 * p + 1;
        }
    }
    while (heap != NULL) {
        temp = (struct ListNode *) malloc(sizeof(struct ListNode));
        temp->val = heap->val;
        temp->next = NULL;
        travel->next = temp;
        travel = temp;
        
        if (heap->next == NULL) {
            heap[0] = heap[hi - 1];
            hi --;
            if (hi == 0) break;
        } else {
            heap->val = heap->next->val;
            heap->next = heap->next->next;
        }
        
        //*heap = *(heap->next);
        //heap down
        p = 0;
        c = 2 * p + 1;
        while (c < hi) {
            if (c + 1 < hi && cmp(heap + c  + 1, heap + c) < 0) c ++;
            if (cmp(heap + p, heap + c) > 0) {
                swap_heap(heap, p, c);
            } else break;
            p = c;
            c = 2 * p + 1;
        }
    }
    return head->next;
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
    struct ListNode * temp = NULL;
    while (l != NULL) {
        temp = l->next;
        free(l);
        l = temp;
    }
}

int main() {
    struct ListNode** lists = NULL;
    int listsSize = 2;
    int i = 0;
    int len = 2;
    int a0[] = {1, 3};
    int *a1 = NULL;
    int **a;
    int count[] = {0, 2};
    struct ListNode * answer = NULL;
    a = (int **) malloc(sizeof(int *) * len);
    *(a + 0) = a1;
    *(a + 1) = a0;
    lists = (struct ListNode **) malloc(sizeof(struct ListNode *) * listsSize);
    for (i = 0; i < listsSize; i ++) {
        *(lists + i) = convert_int_to_ListNode(*(a + i), count[i]);
    }
    
    answer = mergeKLists(lists, listsSize);
    
    print_ListNode(answer);

    free_ListNode(answer);

    free(a);
    for (i = 0; i < listsSize; i ++) {
        free(*(lists + i));
    }
    free(lists);
    
    printf("end\r\n");
    
    return 0;
}

