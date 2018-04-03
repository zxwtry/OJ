#coding=utf-8

'''
    url: leetcode.com/problems/binary-tree-preorder-traversal
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月31日
    @details:    Solution:  39ms 75.23%
'''

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def preorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if (root == None): return []
        ans, q = [], [root]
        while len(q) != 0:
            n = q.pop(0)
            ans.append(n.val)
            if (n.right != None): q.insert(0, n.right)
            if (n.left != None): q.insert(0, n.left)
        return ans
        
        