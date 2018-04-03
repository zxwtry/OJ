#coding=utf-8

'''
    url: leetcode.com/problems/recover-binary-search-tree
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月25日
    @details:    Solution: 149ms 23.71%
'''

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def inOrder(self, n, p, s):
        if n == None: return
        self.inOrder(n.left, p, s)
        if p[0] != None:
            if n.val <= p[0].val:
                if s[0] == None:
                    s[0] = p[0]
                s[1] = n
        p[0] = n
        self.inOrder(n.right, p, s)
        
    def recoverTree(self, n):
        """
        :type n: TreeNode
        :rtype: void Do not return anything, modify n in-place instead.
        """
        p, s = [None], [None, None]
        self.inOrder(n, p, s)
        s[0].val, s[1].val = s[1].val, s[0].val
        