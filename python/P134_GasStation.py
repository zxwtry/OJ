#coding=utf-8

'''
    url: leetcode.com/problems/gas-station
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月17日
    @details:    Solution:  69ms 25.38%
'''

class Solution(object):
    def real(self, i, n):
        while i >= n: i -= n
        while i < 0: i += n
        return i
    
    def search(self, g, c, i, n):
        b = 0
        for j in range(n):
            k = self.real(i+j, n)
            b += g[k] - c[k]
            if b < 0: return k
        return -1
            
    def canCompleteCircuit(self, g, c):
        """
        :type g: List[int]
        :type c: List[int]
        :rtype: int
        """
        gn = 0 if g == None else len(g)
        cn = 0 if c == None else len(c)
        if (gn == 0 or cn == 0 or gn != cn): return -1
        i = 0
        while i < cn:
            f = self.search(g, c, i, cn)
            if f == -1: return i
            if f < i: return -1
            i = f
            i += 1
        return -1