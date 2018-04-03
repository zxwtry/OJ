#coding=utf-8

'''
    url: leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月27日
    @details:    Solution: 495ms 11.75%
'''

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def search(self, p, pi, pj, n, ni, nj):
        if pi > pj: return None
        r = TreeNode(p[pi])
        lt = 0
        while n[ni+lt] != p[pi]:
            lt += 1
        rt = pj-pi-lt
        r.left = self.search(p, pi+1, pi+lt, n, ni, ni+lt-1)
        r.right = self.search(p, pj-rt+1, pj, n, nj-rt+1, nj)
        return r
        
    def buildTree(self, p, n):
        """
        :type p: List[int]
        :type i: List[int]
        :rtype: TreeNode
        """
        pn = 0 if p == None else len(p)
        nn = 0 if n == None else len(n)
        return self.search(p, 0, pn-1, n, 0, nn-1)