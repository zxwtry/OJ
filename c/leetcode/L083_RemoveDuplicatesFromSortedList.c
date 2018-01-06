/*
    url: leetcode.com/problems/remove-duplicates-from-sorted-list
    AC 6ms 9.62%
*/

#include <stdio.h>
#include <stdlib.h>

typedef struct ListNode * pln;
typedef struct ListNode sln;

struct ListNode {
    int val;
    struct ListNode *next;
};

pln deleteDuplicates(pln h) {
    pln p = NULL, n = h;
    while (n != NULL) {
        if (p == NULL) {
            p = n;
        } else {
            if (p->val != n->val) {
                p->next = n;
                p = n;
            }
        }
        n = n->next;
    }
    if (p != NULL) p->next = NULL;
    return h;
}

int main() {

}