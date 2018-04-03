#coding=utf-8

'''
    url: leetcode.com/problems/merge-sorted-array
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月22日
    @details:    Solution: 52ms 46.81%
'''

class Solution(object):
    def merge(self, n1, m, n2, n):
        """
        :type n1: List[int]
        :type m: int
        :type n2: List[int]
        :type n: int
        :rtype: void Do not return anything, modify n1 in-place instead.
        """
        i1, i2, i = m-1, n-1, m+n-1
        while i1 > -1 and i2 > -1:
            if n1[i1] > n2[i2]:
                n1[i] = n1[i1]
                i, i1 = i-1, i1-1
            else:
                n1[i] = n2[i2]
                i, i2 = i-1, i2-1
        while i1 > -1:
            n1[i] = n1[i1]
            i, i1 = i-1, i1-1
        while i2 > -1:
            n1[i] = n2[i2]
            i, i2 = i-1, i2-1
        
            