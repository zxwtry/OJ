#coding=utf-8

'''
    url: leetcode.com/problems/word-break
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月29日
    @details:    Solution:  49ms 84.54%
'''

class Solution(object):
    def wordBreak(self, s, d):
        """
        :type s: str
        :type d: List[str]
        :rtype: bool
        """
        sn = 0 if s == None else len(s)
        dp = [False] * (sn + 1)
        dp[0] = True
        for i in range(sn):
            if not dp[i]: continue
            for w in d:
                l = len(w)
                if (i+l <= sn and (not dp[i+l]) and s[i :i+l] == w):
                    dp[i+l] = True
        return dp[sn]

