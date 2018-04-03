#coding=utf-8

'''
    url: leetcode.com/problems/unique-binary-search-trees
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月24日
    @details:    Solution: 38ms 86.68%
'''

class Solution(object):
    def numTrees(self, n):
        """
        :type n: int
        :rtype: int
        """
        dp = [0 for i in range(n+1)]
        dp[0] = 1
        for j in range(1, n+1):
            for i in range(j):
                dp[j] += dp[i] * dp[j-1-i]
        return dp[n]
    
if __name__ == "__main__":
    print(Solution().numTrees(3))
        
        