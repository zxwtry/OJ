#coding=utf-8

'''
    url: leetcode.com/problems/same-tree
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月26日
    @details:    Solution: 72ms 2.26%
'''

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def isSameTree(self, p, q):
        """
        :type p: TreeNode
        :type q: TreeNode
        :rtype: bool
        """
        if p == None or q == None:
            return p == None and q == None
        if p.val != q.val: return False
        return self.isSameTree(p.left, q.left) \
            and self.isSameTree(p.right, q.right)
        