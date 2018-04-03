#coding=utf-8

'''
    url: leetcode.com/problems/minimum-path-sum
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月14日
    @details:    Solution: 55ms 95.38%
'''

class Solution(object):
    def minPathSum(self, g):
        """
        :type g: List[List[int]]
        :rtype: int
        """
        if g == None or len(g) == 0: return 0
        rn, cn = len(g), len(g[0])
        h = [0] * cn
        for j in range(cn-1, -1, -1):
            h[j] = (0 if j == cn-1 else h[j+1])+g[rn-1][j]
        for i in range(rn-2, -1, -1):
            h[cn-1] += g[i][cn-1]
            for j in range(cn-2, -1, -1):
                h[j] = min(h[j], h[j+1]) + g[i][j]
        return h[0]
