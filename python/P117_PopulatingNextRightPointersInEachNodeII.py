#coding=utf-8

'''
    url: leetcode.com/problems/populating-next-right-pointers-in-each-node-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月1日
    @details:    Solution:  99ms 78.29%
'''

class TreeLinkNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
        self.next = None

class Solution:
    # @param n, a tree link node
    # @return nothing
    def connect(self, n):
        if n == None: return
        n.next = None
        l1, l2 = n, None
        while l1 != None:
            l2 = l1
            l1 = None
            t = None
            while l2 != None:
                if l2.left != None:
                    if l1 == None: t = l1 = l2.left
                    else:
                        t.next = l2.left
                        t = t.next
                if l2.right != None:
                    if l1 == None: t = l1 = l2.right
                    else:
                        t.next = l2.right
                        t = t.next
                l2 = l2.next
            