#coding=utf-8

'''
    url: leetcode.com/problems/word-search
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月19日
    @details:    Solution: 292ms 86.90%
'''

class Solution(object):
    def search(self, b, i, j, rn, cn, m, w, wi, wn):
        if wi == wn: return True
        if i < 0 or i >= rn: return False
        if j < 0 or j >= cn: return False
        if m[i][j]: return False
        if b[i][j] == w[wi]:
            m[i][j] = True
            a = self.search(b, i-1, j, rn, cn, m, w, wi+1, wn) \
                or self.search(b, i, j-1, rn, cn, m, w, wi+1, wn) \
                or self.search(b, i+1, j, rn, cn, m, w, wi+1, wn) \
                or self.search(b, i, j+1, rn, cn, m, w, wi+1, wn)
            m[i][j] = False
            return a
        else: return False
        
    def exist(self, b, w):
        """
        :type b: List[List[str]]
        :type w: str
        :rtype: bool
        """
        wn = 0 if w == None else len(w)
        rn = 0 if b == None else len(b)
        if rn == 0: return wn == 0
        cn = 0 if b[0] == None else len(b[0])
        if cn == 0: return wn == 0
        m = [[False for j in range(cn)] for i in range(rn)]
        for i in range(rn):
            for j in range(cn):
                if self.search(b, i, j, rn, cn, m, w, 0, wn):
                    return True
        return False
