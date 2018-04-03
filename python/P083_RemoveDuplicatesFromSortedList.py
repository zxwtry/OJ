#coding=utf-8

'''
    url: leetcode.com/problems/remove-duplicates-from-sorted-list
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月20日
    @details:    Solution: 78ms 25.05%
'''

class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None
    def __repr__(self, *args, **kwargs):
        return str(self.val)
        
def construct(l):
    ln = len(l)
    ls = [None] * ln
    for i in range(ln-1, -1, -1):
        ls[i] = ListNode(l[i])
        if i != ln-1:
            ls[i].next = ls[i+1]
    return None if ln == 0 else ls[0]
    
def print_list_node(l):
    print("==================")
    while l != None:
        print(l.val)
        l = l.next
    print("==================")
    
class Solution(object):
    def deleteDuplicates(self, h):
        """
        :type h: ListNode
        :rtype: ListNode
        """
        t, p = h, None
        while t != None:
            if p == None:
                p = t
            elif p.val != t.val:
                p.next = t
                p = t
            t = t.next
        if p != None and p.next != None:
            p.next = None
        return h

if __name__ == "__main__":
    #test case:
    s = [
    [1, 2, 2, 3],
    [1, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 6],
    [2, 2, 3, 3, 4, 5, 5, 6, 6, 6, 7],
    [1, 2, 2, 2, 2],
    [2, 2, 3, 3, 4, 5, 5],
    [2, 2, 3, 3, 4, 4, 5, 5],
    []
    ]
    for t in s:
        h = construct(t)
        print_list_node(Solution().deleteDuplicates(h))