#coding=utf-8

'''
    url: leetcode.com/problems/unique-binary-search-trees-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月24日
    @details:    Solution: 109ms 36.42%
'''

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def clone(self, n, offset):
        if n == None: return None
        c = TreeNode(n.val + offset)
        c.left = self.clone(n.left, offset)
        c.right = self.clone(n.right, offset)
        return c
    
    def generateTrees(self, n):
        """
        :type n: int
        :rtype: List[TreeNode]
        """
        if n < 1: return []
        dp = [[] for i in range(n+1)]
        dp[0].append(None)
        for j in range(1, n+1):
            for i in range(0, j):
                for l in dp[i]:
                    for r in dp[j-1-i]:
                        n = TreeNode(i+1)
                        n.left = l
                        n.right = self.clone(r, i+1)
                        dp[j].append(n)
        return dp[n]