#coding=utf-8

'''
    url: leetcode.com/problems/unique-paths-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月13日
    @details:    Solution: 66ms 18.15%
'''

class Solution(object):
    def uniquePathsWithObstacles(self, g):
        """
        :type g: List[List[int]]
        :rtype: int
        """
        if g == None or g[0] == None: return 0
        m = len(g)
        n = len(g[0])
        dp = [0] * n
        for j in range(n-1, -1, -1):
            if j == n-1 or dp[j+1] == 1:
                if g[m-1][j] == 0:
                    dp[j] = 1
                    continue
            dp[j] = 0
        for i in range(m-2, -1, -1):
            if g[i][n-1] == 1: dp[n-1] = 0
            for j in range(n-2, -1, -1):
                if g[i][j] == 1: dp[j] = 0
                else:dp[j] += 0 if g[i][j+1] == 1 else dp[j+1]
        return dp[0]