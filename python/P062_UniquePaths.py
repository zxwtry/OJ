#coding=utf-8

'''
    url: leetcode.com/problems/unique-paths
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月13日
    @details:    Solution: 79ms 10.36%
'''

class Solution(object):
    def uniquePaths(self, m, n):
        """
        :type m: int
        :type n: int
        :rtype: int
        """
        dp = [1] * n
        for j in range(m-2, -1, -1):
            for i in range(n-2, -1, -1):
                dp[i] += dp[i+1]
        return dp[0]