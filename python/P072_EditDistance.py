#coding=utf-8

'''
    url: leetcode.com/problems/edit-distance/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月16日
    @details:    Solution: 342ms 17.65%
    @details:    Solution: 199ms 94.33%
'''

class Solution(object):
    def minDistance(self, w1, w2):
        """
        :type w1: str
        :type w2: str
        :rtype: int
        """
        n1 = 0 if w1 == None else len(w1)
        n2 = 0 if w2 == None else len(w2)
        dp = [[0 for j in range(n2+1)] for i in range(n1+1)]
        for j in range(n2+1): dp[0][j] = j
        for i in range(n1+1): dp[i][0] = i
        for i in range(n1):
            for j in range(n2):
                dp[i+1][j+1] = min(dp[i][j]+(0 if w1[i] == w2[j] else 1),
                                   dp[i+1][j]+1, dp[i][j+1]+1)
        return dp[n1][n2]

class Solution2(object):
    def minDistance(self, w1, w2):
        """
        :type w1: str
        :type w2: str
        :rtype: int
        """
        n1 = 0 if w1 == None else len(w1)
        n2 = 0 if w2 == None else len(w2)
        if n1 == 0 or n2 == 0: return n1+n2
        d1, d2 = [0]*(n2+1), [0]*(n2+1)
        for j in range(n2+1): d1[j] = j
        for i in range(n1):
            d2[0] = i+1
            for j in range(n2):
                d2[j+1] = min(d1[j]+(0 if w1[i] == w2[j] else 1),
                                   d2[j]+1, d1[j+1]+1)
            t = d1
            d1 = d2
            d2 = t
        return d1[n2]
    
if __name__ == "__main__":
    w1 = "ab"
    w2 = "bc"
    print(Solution().minDistance(w1, w2))   