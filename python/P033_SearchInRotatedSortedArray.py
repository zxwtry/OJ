#coding=utf-8

'''
    url: leetcode.com/problems/next-permutation/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月1日
    @details:    Solution: 29ms 49.33%
'''

class Solution(object):
    #[i, j)
    def binarySearch(self, n, i, j, t):
        j -= 1
        while i < j:
            m = (i + j) // 2
            if n[m] > t:
                j = m - 1
            elif n[m] == t:
                return m
            else:
                i = m + 1
        return i if n[i] == t else -1
    
    #[i, j)
    def solve(self, n, i, j, t):
        j -= 1
        while i < j:
            m, a = (i + j) // 2, -1
            if n[m] == t:
                return m
            if n[m] > n[i]:
                a = self.binarySearch(n, i, m, t)
                if a != -1: return a
                i = m + 1
            else:
                a = self.binarySearch(n, m + 1, j + 1, t)
                if a != -1: return a
                j = m
        return i if n[i] == t else -1
        
    def search(self, n, t):
        """
        :type n: List[int]
        :type t: int
        :rtype: int
        """
        nn = 0 if n == None else len(n)
        if nn == 0:return -1
        return self.solve(n, 0, nn, t)
    
if __name__ == "__main__":
    n = [1,3]
    sol = Solution()
    print(sol.search(n, 3))
        