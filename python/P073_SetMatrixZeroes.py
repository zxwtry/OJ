#coding=utf-8

'''
    url: leetcode.com/problems/set-matrix-zeroes/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月17日
    @details:    Solution: 182ms 36.33%
'''

class Solution(object):
    def setZeroes(self, m):
        """
        :type m: List[List[int]]
        :rtype: void Do not return anything, modify m in-place instead.
        """
        if m == None or len(m) == 0: return
        if m[0] == None or len(m[0]) == 0: return
        rn, cn = len(m), len(m[0])
        rs, cs = False, False
        for ri in range(rn):
            rs = rs or (m[ri][0] == 0)
        for ci in range(cn):
            cs = cs or (m[0][ci] == 0)
        for ri in range(1, rn):
            for ci in range(1, cn):
                if m[ri][ci] == 0:
                    m[0][ci] = 0
                    m[ri][0] = 0
        for ri in range(1, rn):
            for ci in range(1, cn):
                if m[0][ci] == 0 or m[ri][0] == 0:
                    m[ri][ci] = 0
        if rs:
            for ri in range(rn):
                m[ri][0] = 0
        if cs:
            for ci in range(cn):
                m[0][ci] = 0
                

if __name__ == "__main__":
    m = [[1, 2, 3], [0, 4, 5]]
    Solution().setZeroes(m)
    print(m)
        