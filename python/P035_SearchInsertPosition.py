#coding=utf-8

'''
    url: leetcode.com/problems/search-insert-position/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月4日
    @details:    Solution: 58ms 21.67%
'''

class Solution(object):
    def searchInsert(self, n, t):
        """
        :type n: List[int]
        :type t: int
        :rtype: int
        """
        nn = 0 if n == None else len(n)
        if nn == 0: return 0
        return self.binarySearchFirstEqualOrLarger(n, 0, nn, t)
    #[i, j)
    def binarySearchFirstEqualOrLarger(self, n, i, j, t):
        j -= 1
        if n[j] < t: return j + 1
        while i < j:
            m = (i + j) // 2
            if n[m] >= t:
                j = m
            else:
                i = m + 1
        return i
