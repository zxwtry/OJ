/*
    author:  zxwtry
    email:   zxwtry@qq.com
    project: ojcpp
    gcc:     4.8.4
    date:    2018-01-06 11:26:00
    status:  3.64 %
*/

#include<iostream>
#include<stdio.h>
#include<string>
#include<algorithm>
#include<map>
#include<vector>

using namespace std;


void print_arr(int* a, int ai, int aj) {
    printf("starting...\n");
    for (int ak = ai; ak < aj; ak ++) {
        printf("%-4d   %d\n", ak, a[ak]);
    }
    printf("ending...\n");
}



struct ListNode {
    int val;
    ListNode *next;
    ListNode (int x) : val(x), next(NULL){}
};


ListNode * arr_to_ListNodes(int* a, int al, int ar) {
    ListNode * head = NULL;
    ListNode * s = NULL, * t = NULL;
    for (int ai = al; ai < ar; ai ++) {
        s = new ListNode(a[ai]);
        if (t == NULL) {
            head = t = s;
        } else {
            t->next = s;
            t = s;
        }
    }
    return head;
}


void free_ListNodes(ListNode * l) {
    while (l != NULL) {
        ListNode * to_free = l;
        l = l->next;
        delete to_free;
    }
}


void print_ListNodes(ListNode * l) {
    printf("starting ...\n");
    while (l != NULL) {
        printf("%d\n", l->val);
        l = l->next;
    }
    printf("ended ...\n");
}


class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode * h = new ListNode(0);
        ListNode * t = h;
        while (l1 != NULL || l2 != NULL) {
            int v = 0;
            v += l1 == NULL ? 0 : l1->val;
            v += l2 == NULL ? 0 : l2->val;
            t->next = new ListNode(v);
            t = t->next;
            l1 = l1 == NULL ? NULL : l1->next;
            l2 = l2 == NULL ? NULL : l2->next;
        }
        int carry = 0;
        t = h->next;
        while (t != NULL) {
            carry += t->val;
            if (t->next == NULL && (carry / 10) != 0) {
                t->next = new ListNode(0);
            }
            t->val = carry % 10;
            t = t->next;
            carry = carry / 10;
        }
        t = h->next;
        delete h;
        return t;
    }
};



int main() {
    int a[] = {6, 7, 8, 9};
    ListNode * l1 = arr_to_ListNodes(a, 0, 4);
    ListNode * l2 = arr_to_ListNodes(a, 0, 4);
    ListNode * r = Solution().addTwoNumbers(l1, l2);
    print_ListNodes(r);
    free_ListNodes(l1);
    free_ListNodes(l2);
    free_ListNodes(r);
    return 0;
}
