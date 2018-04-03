#coding=utf-8

'''
    url: leetcode.com/problems/symmetric-tree
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月26日
    @details:    Solution: 59ms 30.66%
'''

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def isS(self, l):
        i, j = 0, len(l)-1
        while i < j:
            if (l[i] == None) ^ (l[j] == None): return False
            if l[i] != None and l[i].val != l[j].val: return False
            i, j = i+1, j-1
        return True
    
    def isSymmetric(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        if root == None: return True
        l1, s = [root], True
        while s:
            if (not self.isS(l1)): return False
            l2, s = [], False
            for n in l1:
                if n == None: continue
                s = True
                l2.append(n.left)
                l2.append(n.right)
            l1 = l2
        return True