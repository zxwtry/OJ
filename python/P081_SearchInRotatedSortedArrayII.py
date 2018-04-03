#coding=utf-8

'''
    url: leetcode.com/problems/search-in-rotated-sorted-array-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月19日
    @details:    Solution: 49ms 52.19%
'''

class Solution(object):
    def ss(self, n, ni, nj, t):
        while ni < nj:
            nm = (ni + nj) // 2
            if n[nm] == t: return True
            if n[nm] > t: nj = nm-1
            else: ni = nm+1
        return False
    
    def s(self, n, ni, nj, t):
        if ni > nj: return False
        if n[ni] == t or n[nj] == t: return True
        nm = (ni + nj) // 2
        if n[nm] == t: return True
        if n[nm] > n[ni]:
            if self.ss(n, ni, nm, t): return True
            return self.s(n, nm+1, nj-1, t)
        elif n[nm] < n[ni]:
            if self.ss(n, nm, nj, t): return True
            return self.s(n, ni+1, nm-1, t)
        else:
            return self.s(n, ni+1, nm-1, t) or \
                self.s(n, nm+1, nj-1, t)
        
    def search(self, n, t):
        """
        :type n: List[int]
        :type t: int
        :rtype: bool
        """
        nn = 0 if n == None else len(n)
        if nn == 0: return False
        return self.s(n, 0, nn-1, t)