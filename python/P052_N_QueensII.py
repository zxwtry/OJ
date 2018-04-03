#coding=utf-8

'''
    url: leetcode.com/problems/n-queens-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月10日
    @details:    Solution: 66ms 84.62%
'''

class Solution(object):
    def search(self, a, c, l, r, i, n):
        if i == n:
            a[0] += 1
            return
        for j in range(n):
            if not c[j] and not l[j+i] and not r[j-i+n-1]:
                c[j]=l[j+i]=r[j-i+n-1]=True
                self.search(a, c, l, r, i+1, n)
                c[j]=l[j+i]=r[j-i+n-1]=False
                
    def totalNQueens(self, n):
        """
        :type n: int
        :rtype: List[List[str]]
        """
        a,c,l,r=[0],[False]*n,[False]*(2*n-1),[False]*(2*n-1)
        self.search(a, c, l, r, 0, n)
        return a[0]
    
if __name__ == "__main__":
    print(Solution().totalNQueens(5))