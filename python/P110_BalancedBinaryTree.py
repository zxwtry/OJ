#coding=utf-8

'''
    url: leetcode.com/problems/balanced-binary-tree
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月29日
    @details:    Solution: 82ms 70.51%
'''

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def search(self, n, sign):
        if n == None or sign[0]: return 0
        lc = self.search(n.left, sign)
        rc = self.search(n.right, sign)
        if abs(lc-rc) > 1: sign[0] = True
        return max(lc, rc) + 1
        
    def isBalanced(self, n):
        """
        :type n: TreeNode
        :rtype: bool
        """
        if n == None: return True
        sign = [False]
        lc = self.search(n.left, sign)
        if (sign[0]): return False
        rc = self.search(n.right, sign)
        if (sign[0]): return False
        return abs(lc-rc) < 2