#coding=utf-8

'''
    url: leetcode.com/problems/palindrome-partitioning-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月15日
    @details:    Solution:  792ms 29.31%
'''

class Solution(object):
    def index(self, s, i):
        return "#" if i % 2 == 0 else s[i // 2]
    
    def manacher(self, s, sn):
        nn = 2 * sn + 1
        m = [0] * nn
        ti = 0
        mi = 0
        ci = 0
        for i in range(nn):
            mi = 2 * ci - i
            if i >= ti or m[mi] == ti-i:
                li = i
                ri = i
                while li-1 > -1 and ri+1 < nn and \
                    self.index(s, li-1) == self.index(s, ri+1):
                    li, ri = li-1, ri+1
                ti = ri
                ci = i
                m[i] = (ri-li) // 2
            else: m[i] = min(m[mi], ti-i)    
        return m
    
    def isP(self, m, i, j):
        return m[i+j+1] > abs(i-j)
        
    def minCut(self, s):
        """
        :type s: str
        :rtype: int
        """
        sn = 0 if s == None else len(s)
        if sn < 1: return 0
        m = self.manacher(s, sn)
        s = [1 << 30] * sn
        for i in range(sn):
            if (self.isP(m, i, 0)): s[i] = 0
            else:
                for j in range(i):
                    if (not self.isP(m, i, j+1)):
                        continue
                    s[i] = min(s[i], s[j]+1)
        return s[sn-1]
        
if __name__ == "__main__":
    s = "abbab"
    print(Solution().minCut(s))