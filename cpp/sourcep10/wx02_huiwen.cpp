#include <algorithm>
#include <iostream>
#include <map>
#include <stack>
#include <stdio.h>
#include <string>
#include <vector>
#include <queue>

using namespace std;

struct ListNode
{
    int value;
    struct ListNode *next;
};

// 回文 aabaa 链表用快慢指针
bool checkHuiWenFastSlow(ListNode *head)
{
    if (NULL == head || NULL == head->next)
    {
        return true;
    }
    ListNode *slow = head;
    ListNode *fast = head;
    ListNode *pre = slow;
    while (fast != NULL && fast->next != NULL)
    {
        pre = slow;
        slow = slow->next;
        fast = fast->next->next; // 跑两次
    }
    if (fast == NULL)
    {
        slow = slow->next;
    }
    ListNode *cur = slow;
    ListNode *temp = slow->next;
    pre->next = NULL; // 拆成两个链表

    // 执行后半反转
    while (NULL != temp)
    {
        cur = temp;
        temp = temp->next;
        cur->next = pre;
        pre = cur;
    }

    // 两半链表对比
    while (cur != NULL)
    {
        if (cur->value != head->value)
        {
            return false;
        }
        cur = cur->next;
        head = head->next;
    }
    return true;
}

int main()
{

    // ListNode *h0 = new (ListNode);
    // h0->value = 10;
    // h0->next = NULL;
    // ListNode *h1 = new (ListNode);
    // h1->value = 30;
    // h1->next = NULL;
    // ListNode *h2 = new (ListNode);
    // h2->value = 20;
    // h2->next = NULL;
    // ListNode *h3 = new (ListNode);
    // h3->value = 10;
    // h3->next = NULL;

    // h0->next = h1;
    // h1->next = h2;
    // h2->next = h3;

    // cout << checkHuiWenFastSlow(h0) << endl;

    const int N = 9;
    int arr[N] = {0, 1, 2, 3, 4, 3, 2, 1, 0};
    ListNode *la[N + 1];
    for (int i = 0; i <= N; i++)
    {
        la[i] = NULL;
    }
    for (int i = N - 1; i > -1; i--)
    {
        la[i] = new (ListNode);
        la[i]->value = arr[i];
        la[i]->next = la[i + 1];
    }
    cout << checkHuiWenFastSlow(la[0]) << endl;
}