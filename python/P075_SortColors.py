#coding=utf-8

'''
    url: leetcode.com/problems/set-matrix-zeroes/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月17日
    @details:    Solution: 78ms 5.45%
'''

class Solution(object):
    def sortColors(self, n):
        """
        :type n: List[int]
        :rtype: void Do not return anything, modify n in-place instead.
        """
        if n == None or len(n) == 0: return
        c0, c1, c2 = 0, 0, 0
        for val in n:
            if val == 0: c0 += 1
            if val == 1: c1 += 1
            if val == 2: c2 += 1
        for i in range(c0):
            n[i] = 0
        for i in range(c1):
            n[c0+i] = 1
        for i in range(c2):
            n[c0+c1+i] = 2
        