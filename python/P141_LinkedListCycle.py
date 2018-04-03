#coding=utf-8

'''
    url: leetcode.com/problems/linked-list-cycle
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月31日
    @details:    Solution:  82ms 5.89%
'''

class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def hasCycle(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        if head == None: return False
        fast, slow = head, head
        while True:
            if fast.next == None: return False
            fast = fast.next
            if fast.next == None: return False
            fast = fast.next
            if slow.next == None: return False
            slow = slow.next
            if (fast == slow): break
        return True
            