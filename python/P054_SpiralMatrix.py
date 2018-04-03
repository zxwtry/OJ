#coding=utf-8

'''
    url: leetcode.com/problems/spiral-matrix
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月10日
    @details:    Solution: 45ms 46.70%
'''

class Solution(object):
    def shell_add(self, a, ai, i1, j1, i2, j2, m):
        for k in range(j1, j2):
            a[ai[0]] = m[i1][k]
            ai[0] += 1
        for k in range(i1 , i2):
            a[ai[0]] = m[k][j2]
            ai[0] += 1
        for k in range(j2, j1, -1):
            a[ai[0]] = m[i2][k]
            ai[0] += 1
        for k in range(i2, i1, -1):
            a[ai[0]] = m[k][j1]
            ai[0] += 1
        
    def spiralOrder(self, m):
        """
        :type m: List[List[int]]
        :rtype: List[int]
        """
        mi = 0 if m == None else len(m)
        if mi == 0: return []
        mj = 0 if m[0] == None else len(m[0])
        if mj == 0: return []        
        i1, j1, i2, j2, ai, a = 0,0,mi-1,mj-1,[0],[0]*(mi*mj)
        while i1 < i2 and j1 < j2:
            self.shell_add(a, ai, i1, j1, i2, j2, m)
            i1, i2, j1, j2 = i1+1,i2-1,j1+1,j2-1
        if i1 == i2:
            for k in range(j1, j2+1):
                a[ai[0]] = m[i1][k]
                ai[0] += 1
        elif j1 == j2:
            for k in range(i1, i2+1):
                a[ai[0]] = m[k][j1]
                ai[0] += 1
        return  a
            