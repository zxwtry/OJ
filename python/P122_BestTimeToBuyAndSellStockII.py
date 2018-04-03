#coding=utf-8

'''
    url: leetcode.com/problems/best-time-to-buy-and-sell-stock-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月5日
    @details:    Solution:  56ms 39.52%
'''

class Solution(object):
    def maxProfit(self, p):
        """
        :type p: List[int]
        :rtype: int
        """
        return  sum(max((p[i]-p[i-1]), 0) for i in range(1, len(p)))

if __name__ == "__main__":
    p = [1, 2, 3, 6, 4, 7]        
    print(Solution().maxProfit(p))