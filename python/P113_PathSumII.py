#coding=utf-8

'''
    url: leetcode.com/problems/path-sum-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月30日
    @details:    Solution: 102ms 24.53%
'''

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def search(self, n, s, c, a, t):
        if n == None: return
        t.append(n.val)
        if n.left == None and n.right == None:
            if s == c + n.val: a.append(list(t))
        self.search(n.left, s, c+n.val, a, t)
        self.search(n.right, s, c+n.val, a, t)
        t.pop()
        
    def pathSum(self, n, s):
        """
        :type n: TreeNode
        :type s: int
        :rtype: List[List[int]]
        """
        a, t = [], []
        self.search(n, s, 0, a, t)
        return a
        