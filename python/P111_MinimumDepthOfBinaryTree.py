#coding=utf-8

'''
    url: leetcode.com/problems/minimum-depth-of-binary-tree
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月29日
    @details:    Solution: 66ms 83.33%
'''

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def minDepth(self, n):
        """
        :type n: TreeNode
        :rtype: int
        """
        if n == None: return 0
        l1, a = [n], 0
        while len(l1) != 0:
            a, l2 = a+1, []
            for l in l1:
                if l.left == None and l.right == None: return a
                if l.left != None: l2.append(l.left)
                if l.right != None: l2.append(l.right)
            l1 = l2
        return a
        