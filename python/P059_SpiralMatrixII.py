#coding=utf-8

'''
    url: leetcode.com/problems/spiral-matrix-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月12日
    @details:    Solution: 62ms 20.60%
'''

class Solution(object):
    def shell_add(self, ans, ai, i , j):
        for k in range(i, j):
            ans[i][k] = ai[0]
            ai[0] += 1
        for k in range(i, j):
            ans[k][j] = ai[0]
            ai[0] += 1
        for k in range(j, i, -1):
            ans[j][k] = ai[0] 
            ai[0] += 1
        for k in range(j, i, -1):
            ans[k][i] = ai[0]
            ai[0] += 1
            
    def generateMatrix(self, n):
        """
        :type n: int
        :rtype: List[List[int]]
        """
        ans = [[0 for i in range(n)] for j in range(n)]
        i, j, ai = 0, n-1, [1]
        while i < j:
            self.shell_add(ans, ai, i, j)
            i += 1
            j -= 1
        if n % 2 == 1:
            ans[i][j] = ai[0]
        return ans
            


if __name__ == "__main__":
    n = 3
    print(Solution().generateMatrix(n))
    