#coding=utf-8

'''
    url: leetcode.com/problems/flatten-binary-tree-to-linked-lis
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月30日
    @details:    Solution:  55ms 58.71%
    @details:    Solution2: 45ms 96.52%
'''

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def search(self, n, l):
        if n == None: return
        l.append(n)
        self.search(n.left, l)
        self.search(n.right, l)
        
    def flatten(self, n):
        """
        :type n: TreeNode
        :rtype: void Do not return anything, modify root in-place instead.
        """
        l = []
        self.search(n, l)
        l.append(None)
        for i in range(len(l)-1):
            l[i].left = None
            l[i].right = l[i+1]

class Solution2(object):
    def flatten(self, n):
        r, p = n , None
        while r != None:
            if (r.left != None):
                p = r.left
                while p.right != None:
                    p = p.right
                p.right = r.right
                r.right = r.left
                r.left = None
            r = r.right
                
        
