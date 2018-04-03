#coding=utf-8

'''
    url: leetcode.com/problems/maximal-rectangle
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月21日
    @details:    Solution: 52ms 54.78%
'''

class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None
    def __str__(self, *args, **kwargs):
        return str(self.val)

def construct(l):
    ln = 0 if l == None else len(l)
    if ln == 0: return None
    lns = [None] * ln
    for i in range(ln-1, -1, -1):
        lns[i] = ListNode(l[i])
        if i != ln-1: 
            lns[i].next = lns[i+1]
    return lns[0]

def print_ListNode(l):
    print("================")
    while l != None:
        print(l.val)
        l = l.next
    print("================")

class Solution(object):
    def add(self, s, t, i, j):
        if s[i] == None:
            s[i] = s[j] = t
        else:
            s[j].next = t
            s[j] = t
            
    def partition(self, h, x):
        """
        :type h: ListNode
        :type x: int
        :rtype: ListNode
        """
        s, t = [None] * 4, h
        while t != None:
            g = t.next
            t.next = None
            if t.val < x:
                self.add(s, t, 0, 1)
            else:
                self.add(s, t, 2, 3)
            t = g
        if s[0] == None: return h
        s[1].next = s[2]
        return s[0]

if __name__ == "__main__":
    m = [
            [1, 4, 3, 2, 5, 2],
            [1],
            [4, 3, 5],
            [5, 2],
            
        ]
    for n in m:
        h = construct(n)
        print_ListNode(Solution().partition(h, 3))