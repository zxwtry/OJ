#coding=utf-8

'''
    url: leetcode.com/problems/best-time-to-buy-and-sell-stock-iii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月5日
    @details:    Solution:  86ms 36.12%
'''

class Solution(object):
    def maxProfit(self, p):
        """
        :type p: List[int]
        :rtype: int
        """
        l, r = [], [0]*(len(p)+1)
        mv, m = 1 << 31, 0
        l.append(0)
        for v in p:
            mv = min(mv, v)
            m = max(m, v-mv)
            l.append(m)
        mv, m = 0, 0
        for i in range(len(p)-1, -1, -1):
            mv = max(p[i], mv)
            m = max(m, mv-p[i])
            r[i] = m
        return max(l[i]+r[i] for i in range(len(p)+1))
        
if __name__ == "__main__":
    p = [2,1,2,0,1]
    print(Solution().maxProfit(p))