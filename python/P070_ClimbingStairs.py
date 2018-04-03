#coding=utf-8

'''
    url: leetcode.com/problems/climbing-stairs
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月16日
    @details:    Solution: 39ms 67.67%
'''

class Solution(object):
    def climbStairs(self, n):
        """
        :type n: int
        :rtype: int
        """
        if n < 3: return n
        dp = [0] * (n+1)
        dp[1] = 1
        dp[2] = 2
        for i in range(3, n+1):
            dp[i] = dp[i-1]+dp[i-2]
        return dp[n]