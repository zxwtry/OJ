#coding=utf-8

'''
    url: leetcode.com/problems/combinations
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月18日
    @details:    Solution: 779ms 13.24%
'''

class Solution(object):
    def search(self, n, ni, nn, s, si, sn, a):
        if si == sn:
            a.append(list(s))
            return
        for i in range(ni , nn):
            s[si] = n[i]
            self.search(n, i+1, nn, s, si+1, sn, a)
            
    def combine(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: List[List[int]]
        """
        a, s = [], [0]*k
        self.search(n, 0, len(n), s, 0, k, a)
        return a