#coding=utf-8

'''
    url: leetcode.com/problems/rotate-list
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月13日
    @details:    Solution: 79ms 10.36%
'''

class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def rotateRight(self, h, k):
        """
        :type h: ListNode
        :type k: int
        :rtype: ListNode
        """
        hn, t, a = 1, h, None
        if h == None: return h
        while t.next != None:
            hn += 1
            t  = t.next
        if hn == 1: return h
        t.next = h
        k = hn - (k % hn)
        t = h
        for i in range(1, k):
            t = t.next
        a = t.next
        t.next = None
        return a