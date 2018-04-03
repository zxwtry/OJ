#coding=utf-8

'''
    url: leetcode.com/problems/sum-root-to-leaf-numbers
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月13日
    @details:    Solution:  52ms 35.43%
'''

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def search(self, root, val, ans):
        if root.left == None and root.right == None:
            ans[0] += (val * 10 + root.val)
        if root.left != None:
            self.search(root.left, val * 10 + root.val, ans) 
        if root.right != None:
            self.search(root.right, val * 10 + root.val, ans)
            
    def sumNumbers(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if root == None: return 0
        ans = [0]
        self.search(root, 0, ans)
        return ans