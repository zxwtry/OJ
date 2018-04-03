#coding=utf-8

'''
    url: leetcode.com/problems/sudoku-solver/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月5日
    @details:    Solution: 1709ms 12.51%
'''
from jinja2._compat import unichr

class Solution(object):
    def isValid(self, b, x, y):
        x_base, y_base = (x // 3) * 3, (y // 3) * 3
        for k in range(9):
            if x != k and b[x][y]==b[k][y]:
                return False
            if y != k and b[x][y]==b[x][k]:
                return False
            new_x, new_y = x_base+(k // 3), y_base+(k % 3)
            if new_x != x and new_y != y and b[x][y] == b[new_x][new_y]:
                return False
        return True
    
    def solve(self, b):
        for x in range(9):
            for y in range(9):
                if b[x][y] != '.':continue
                for k in range(9):
                    b[x][y]=unichr(ord('1')+k)
                    if self.isValid(b, x, y)\
                    and self.solve(b):
                        return True
                    b[x][y] = '.'
                return False
        return True
    
    def solveSudoku(self, b):
        """
        :type b: List[List[str]]
        :rtype: void Do not return anything, modify b in-place instead.
        """
        self.solve(b)

if __name__ == "__main__":
    b=[list("..9748..."),\
       list("7........"),\
       list(".2.1.9..."),\
       list("..7...24."),\
       list(".64.1.59."),\
       list(".98...3.."),\
       list("...8.3.2."),\
       list("........6"),\
       list("...2759..")]
    Solution().solve(b)
    print(b)
