#coding=utf-8

'''
    url: leetcode.com/problems/pascals-triangle
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月4日
    @details:    Solution:  55ms 23.05%
'''

class Solution(object):
    def generate(self, n):
        """
        :type n: int
        :rtype: List[List[int]]
        """
        a = [[1]*(i+1) for i in range(n)]
        for i in range(2, n):
            for j in range(1, i):
                a[i][j] = a[i-1][j-1]+a[i-1][j]
        return a