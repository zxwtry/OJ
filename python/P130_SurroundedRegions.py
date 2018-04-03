#coding=utf-8

'''
    url: leetcode.com/problems/surrounded-regions
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月13日
    @details:    Solution:  146ms 80.52%
'''

class Solution(object):
    def solve(self, b):
        """
        :type b: List[List[str]]
        :rtype: void Do not return anything, modify b in-place instead.
        """
        rn = 0 if b == None else len(b)
        if rn < 3: return
        cn = 0 if b[0] == None else len(b[0])
        if cn < 3: return
        save = [ij for k in range(rn+cn) for ij in ((0, k), (rn-1, k), (k, 0), (k, cn-1))]
        while save:
            i, j = save.pop()
            if 0 <= i < rn and 0 <= j < cn and b[i][j] == 'O':
                b[i][j] = '1'
                save += (i, j-1), (i, j+1), (i-1, j), (i+1, j)
        for i in range(rn):
            for j in range(cn):
                if b[i][j] == '1':
                    b[i][j] = 'O'
                elif b[i][j] == 'O':
                    b[i][j] = 'X'