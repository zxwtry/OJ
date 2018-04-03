#coding=utf-8

'''
    url: leetcode.com/problems/maximum-subarray
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月10日
    @details:    Solution: 59ms 53.69%
'''

class Solution(object):
    def maxSubArray(self, n):
        """
        :type n: List[int]
        :rtype: int
        """
        nn = 0 if n == None else len(n)
        if nn == 0: return 0
        a, sum = n[0], n[0]
        for i in range(1, nn):
            if sum < 0: sum = 0
            sum += n[i]
            a = max(a, sum)
        return a
        