#coding=utf-8

'''
    url: leetcode.com/problems/rotate-image
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月8日
    @details:    Solution: 75ms 8.23%
'''

class Solution(object):
    def shell_rotate(self, m, i, j):
        for k in range(i, j):
            t = m[i][k]
            m[i][k] = m[i+j-k][i]
            m[i+j-k][i] = m[j][i+j-k]
            m[j][i+j-k] = m[k][j]
            m[k][j] = t
    
    def rotate(self, m):
        """
        :type m: List[List[int]]
        :rtype: void Do not return anything, modify m in-place instead.
        """
        n = 0 if m == None else len(m)
        if n == 0: return
        i, j = 0, n-1
        while i < j:
            self.shell_rotate(m, i, j)
            i, j = i+1, j-1

if __name__ == "__main__":
    m = [[1,2,3,4] for i in range(4)]
    Solution().rotate(m)
#     Solution().rotate(m)
    print(m)