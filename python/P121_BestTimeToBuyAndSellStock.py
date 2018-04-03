#coding=utf-8

'''
    url: leetcode.com/problems/best-time-to-buy-and-sell-stock
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月5日
    @details:    Solution:  68ms 26.43%
'''

class Solution(object):
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        ans, mv = 0, 1<31
        for i in prices:
            mv = min(mv, i)
            ans = max(ans, i-mv)
        return ans