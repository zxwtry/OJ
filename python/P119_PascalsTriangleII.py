#coding=utf-8

'''
    url: leetcode.com/problems/pascals-triangle-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月4日
    @details:    Solution:  55ms 25.60%
'''

class Solution(object):
    def getRow(self, i):
        """
        :type i: int
        :rtype: List[int]
        """
        L, R = [1]*(i+1), [1]*(i+1)
        if i < 2: return L
        for k in range(2, i):
            for j in range(1, k):
                R[k][j] = R[k-1][j-1]+R[k-1][j]
            L, R = R, L
        return L