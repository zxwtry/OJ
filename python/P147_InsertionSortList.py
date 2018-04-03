#coding=utf-8

'''
    url: leetcode.com/problems/word-break-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月31日
    @details:    Solution:  2319ms 27.12%
    @details:    Solution:    92ms 98.25%
'''

class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def insertionSortList(self, h):
        """
        :type h: ListNode
        :rtype: ListNode
        """
        h1, h2, t, tt, tp = None, h, None, None, None
        while h2 != None:
            t = h2
            h2 = h2.next
            
            if h1 == None:
                h1 = t
                h1.next = None
                continue
                
            tt = h1
            tp = None
            while tt != None and tt.val < t.val:
                tp = tt
                tt = tt.next
            
            if tp == None:
                t.next = h1
                h1 = t
            else:
                t.next = tp.next
                tp.next = t
        
        return h1
    
class Solution2(object):
    def insertionSortList(self, h):
        if (h == None): return None
        l, t = [], h
        while t != None:
            l.append(t)
            t = t.next
        l.sort(key=lambda a: a.val, reverse=False)
        le = len(l)
        for i in range(le):
            l[i].next = None if i == le-1 else l[i+1]
        return l[0]

