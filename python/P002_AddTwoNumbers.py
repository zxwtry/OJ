#coding=utf-8

'''
You are given two non-empty linked lists representing two non-negative integers. 
The digits are stored in reverse order and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
'''
from leetcode.Utils import ListNode

'''
@author:     zxwtry
@email:      zxwtry@qq.com
@date:       2017年2月12日
@details:    Solution: AC 182ms 11.93%
'''

class Solution(object):
    def addTwoNumbers(self, l1, l2):
        carry = 0
        save = node = ListNode(0)
        while l1 or l2 or carry:
            v1 = v2 = 0
            if l1:
                v1 = l1.val
                l1 = l1.next
            if l2:
                v2 = l2.val
                l2 = l2.next
            carry, val = divmod(v1 + v2 + carry, 10)
            node.next = ListNode(val)
            node = node.next
        return save.next

if __name__ == "__main__":
    print("aaa")
