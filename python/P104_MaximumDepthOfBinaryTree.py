#coding=utf-8

'''
    url: leetcode.com/problems/maximum-depth-of-binary-tree
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月27日
    @details:    Solution: 65ms 71.36%
'''

class Solution(object):
    def maxDepth(self, n):
        """
        :type n: TreeNode
        :rtype: int
        """
        if n == None: return 0
        l, cnt = [n], 0
        while len(l) != 0:
            ll = []
            for nn in l:
                if nn.left != None: ll.append(nn.left)
                if nn.right != None: ll.append(nn.right)
            l = ll
            cnt += 1
        return cnt