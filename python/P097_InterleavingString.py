#coding=utf-8

'''
    url: leetcode.com/problems/interleaving-string
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月25日
    @details:    Solution: 92ms 18.64%
'''

class Solution(object):
    def isInterleave(self, s1, s2, s3):
        """
        :type s1: str
        :type s2: str
        :type s3: str
        :rtype: bool
        """
        n1 = 0 if s1 == None else len(s1)
        n2 = 0 if s2 == None else len(s2)
        n3 = 0 if s3 == None else len(s3)
        if n1+n2 != n3: return False
        dp = [[False for j in range(n2+1)] for i in range(n1+1)]
        dp[0][0] = True
        for j in range(1, n2+1):
            if n2[j-1] != s3[j-1]: break
            dp[0][j] = True
        for i in range(1, n1+1):
            if n1[i-1] != s3[i-1]: break
            dp[i][0] = True
        for i in range(1, n1+1):
            for j in range(1, n2+1):
                if s1[i-1] == s3[i+j-1] and dp[i-1][j]:
                    dp[i][j] = True
                if s2[j-1] == s3[i+j-1] and dp[i][j-1]:
                    dp[i][j] = True
        return dp[n1][n2]