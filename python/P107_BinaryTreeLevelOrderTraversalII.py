#coding=utf-8

'''
    url: leetcode.com/problems/binary-tree-level-order-traversal-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月28日
    @details:    Solution: 56ms 59.43%
'''

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def levelOrderBottom(self, n):
        """
        :type n: TreeNode
        :rtype: List[List[int]]
        """
        if n == None: return []
        ans, l = [], [n]
        while len(l) != 0:
            ans.append([nn.val for nn in l])
            ll = []
            for nn in l:
                if nn.left != None: ll.append(nn.left)
                if nn.right != None: ll.append(nn.right)
            l = ll
        ans.reverse()
        return ans