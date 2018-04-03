#coding=utf-8

'''
    url: leetcode.com/problems/binary-tree-postorder-traversal
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月31日
    @details:    Solution:  45ms 44.95%
'''

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def postorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root == None: return []
        ans, q = [], [root]
        while len(q) != 0:
            n = q.pop(0)
            ans.append(n.val)
            if (n.left != None): q.insert(0, n.left)
            if (n.right != None): q.insert(0, n.right)
        ans.reverse()
        return ans
        
