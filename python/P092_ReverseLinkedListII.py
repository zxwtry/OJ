#coding=utf-8

'''
    url: leetcode.com/problems/reverse-linked-list-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月23日
    @details:    Solution: 46ms 49.65%
'''

class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None
    def __str__(self, *args, **kwargs):
        return str(self.val)

def con(l):
    n = [ListNode(v) for v in l]
    for i in range(len(l)-1):
        n[i].next = n[i+1]
    return n[0]

def pri(d):
    print("=====================")
    while d != None:
        print(d.val)
        d = d.next
    print("=====================")

class Solution(object):
    def reverseBetween(self, h, m, n):
        """
        :type h: ListNode
        :type m: int
        :type n: int
        :rtype: ListNode
        """
        t, d, p = 1, h, None
        h1, h2, e1, e2 = None, None, None, None
        while d != None:
            if t == m-1: e1=d
            if t == m: h1=d
            if t == n: h2=d
            if t == n+1:
                e2=d
                break
            s = d.next
            if t >= m:
                d.next=p
            d, t, p = s, t+1, d
        if e1 != None: e1.next = h2
        if h1 != None: h1.next = e2
        if m == 1: return h2
        return h
    
if __name__ == "__main__":
    h = con([1, 2, 3, 4, 5])
    a = Solution().reverseBetween(h, 1, 5)
    pri(a)
    