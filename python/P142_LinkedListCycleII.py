#coding=utf-8

'''
    url: leetcode.com/problems/linked-list-cycle-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月31日
    @details:    Solution:  92ms 46.35%
'''

class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def detectCycle(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head == None: return None
        fast, slow = head, head
        while True:
            if fast.next == None: return None
            fast = fast.next
            if fast.next == None: return None
            fast = fast.next
            if slow.next == None: return None
            slow = slow.next
            if (fast == slow): break
        fast = head
        while fast != slow:
            fast = fast.next
            slow = slow.next
        return fast    
        