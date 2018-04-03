#coding=utf-8

'''
    url: leetcode.com/problems/copy-list-with-random-pointer
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月29日
    @details:    Solution:  135ms 80.89%
'''

class RandomListNode(object):
    def __init__(self, x):
        self.label = x
        self.next = None
        self.random = None

class Solution(object):
    def copyRandomList(self, head):
        """
        :type head: RandomListNode
        :rtype: RandomListNode
        """
        if (head == None): return None
        m, t = {}, head
        while (t != None):
            m[t] = RandomListNode(t.label)
            t = t.next
        t = head
        while (t != None):
            if t.next != None:
                m[t].next = m[t.next]
            if t.random != None:
                m[t].random = m[t.random]
            t = t.next
        return m[head]
            