#coding=utf-8

'''
    url: leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月28日
    @details:    Solution: 415ms 16.78%
'''

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def search(self, n, ni, nj, p, pi, pj):
        if ni > nj: return None
        r = TreeNode(p[pj])
        lt = 0
        while n[ni+lt] != p[pj]:
            lt += 1
        rt = nj - ni - lt
        r.left = self.search(n, ni, ni+lt-1, p, pi, pi+lt-1)
        r.right = self.search(n, nj-rt+1, nj, p, pj-rt, pj-1)
        return r
    
    def buildTree(self, n, p):
        """
        :type n: List[int]
        :type p: List[int]
        :rtype: TreeNode
        """
        nn = 0 if n == None else len(n)
        pn = 0 if p == None else len(p)
        return self.search(n, 0, nn-1, p, 0, pn-1)
        
        
        