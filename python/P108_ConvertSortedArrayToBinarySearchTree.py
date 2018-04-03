#coding=utf-8

'''
    url: leetcode.com/problems/convert-sorted-array-to-binary-search-tree
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月28日
    @details:    Solution: 145ms 9.81%
'''

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def search(self, n, ni, nj):
        if ni > nj: return None
        nm = (ni+nj) // 2
        root = TreeNode(n[nm])
        root.left = self.search(n, ni, nm-1)
        root.right = self.search(n, nm+1, nj)
        return root
    
    def sortedArrayToBST(self, n):
        """
        :type n: List[int]
        :rtype: TreeNode
        """
        nn = 0 if n == 0 else len(n)
        return self.search(n, 0, nn-1)
        