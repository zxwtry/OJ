#coding=utf-8

'''
    url: leetcode.com/problems/valid-sudoku/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月4日
    @details:    Solution: 112ms 26.68%
'''

class Solution(object):
    def isValidSudoku(self, b):
        """
        :type b: List[List[str]]
        :rtype: bool
        """
        for k in range(9):
            m = [False] * 9
            for i in range(9):
                if b[k][i] == '.': continue
                v = int(b[k][i]) - 1
                if m[v]: return False
                m[v] = True
            m = [False] * 9
            for i in range(9):
                if b[i][k] == '.': continue
                v = int(b[i][k]) - 1
                if m[v]: return False
                m[v] = True
            m = [False] * 9
            x_base, y_base = (k // 3) * 3, (k % 3) * 3
            for i in range(9):
                x = x_base + (i // 3)
                y = y_base + (i % 3)
                if b[x][y] == '.':continue
                v = int(b[x][y]) - 1
                if m[v]: return False
                m[v] = True
        return True
            
            
                    