#coding=utf-8

'''
    url: leetcode.com/problems/validate-binary-search-tree
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月25日
    @details:    Solution: 82ms 51.50%
'''

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def search(self, n, i, j):
        if n == None: return True
        if n.val > j or n.val < i: return False
        return self.search(n.left, i, min(j, n.val-1)) \
            and self.search(n.right, max(i, n.val+1), j)
        
    def isValidBST(self, n):
        """
        :type n: TreeNode
        :rtype: bool
        """
        if n == None: return True
        return self.search(n, -2147483648, 2147483647)