#coding=utf-8

'''
    url: leetcode.com/problems/binary-tree-zigzag-level-order-traversal
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月27日
    @details:    Solution: 49ms 88.74%
'''

class Solution(object):
    def zigzagLevelOrder(self, n):
        """
        :type n: TreeNode
        :rtype: List[List[int]]
        """
        if n == None: return []
        ans, l, sign = [], [n], False
        while len(l) != 0:
            if sign: ans.append([l[len(l)-1-i].val for i in range(len(l))])
            else: ans.append([nn.val for nn in l])
            ll = []
            for nn in l:
                if nn.left != None: ll.append(nn.left)
                if nn.right != None: ll.append(nn.right)
            l = ll
            sign = not sign
        return ans
