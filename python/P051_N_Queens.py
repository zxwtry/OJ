#coding=utf-8

'''
    url: leetcode.com/problems/n-queens
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月9日
    @details:    Solution: 92ms 83.33%
'''

class Solution(object):
    def search(self, a, c, l, r, i, n, rec):
        if i == n:
            a_t,a_j=[],['.']*8
            for j in range(n):
                for j in range(n):
                    a_j[j]=(rec[j][j])
                a_t.append("".join(a_j))
            a.append(a_t)
            return
        for j in range(n):
            if not c[j] and not l[j+i] and not r[j-i+n-1]:
                c[j]=l[j+i]=r[j-i+n-1]=True
                rec[i][j]='Q'
                self.search(a, c, l, r, i+1, n, rec)
                c[j]=l[j+i]=r[j-i+n-1]=False
                rec[i][j]='.'
                
    def solveNQueens(self, n):
        """
        :type n: int
        :rtype: List[List[str]]
        """
        a,c,l,r=[],[False]*n,[False]*(2*n-1),[False]*(2*n-1)
        rec=[['.' for i in range(n)] for j in range(n)]
        self.search(a, c, l, r, 0, n, rec)
        return a

if __name__ == "__main__":
    print(Solution().solveNQueens(5))