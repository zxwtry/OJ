#coding=utf-8

'''
    url: leetcode.com/problems/binary-tree-inorder-traversal
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月24日
    @details:    Solution: 42ms 63.63%
    @details:    SolutionUnRecur: 52ms 24.22%
'''

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def inOrder(self, root, a):
        if (root == None): return
        self.inOrder(root.left, a)
        a.append(root.val)
        self.inOrder(root.right, a)

    def inorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        a = []
        self.inOrder(root, a)
        return a
    
class SolutionUnRecur(object):
    def inorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        a, s, h = [], [], root
        while (True):
            if len(s) == 0 and h == None: break
            if h == None:
                h = s.pop()
                a.append(h.val)
                h = h.right
            else:
                s.append(h)
                h = h.left        
        return a