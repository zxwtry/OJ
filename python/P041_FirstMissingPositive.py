#coding=utf-8

'''
    url: leetcode.com/problems/first-missing-positive/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月6日
    @details:    Solution: 55ms 34.42%
'''

class Solution(object):
    def swap(self, n, i, j):
        t = n[i]
        n[i] = n[j]
        n[j] = t
    
    def firstMissingPositive(self, n):
        """
        :type n: List[int]
        :rtype: int
        """
        nn = 0 if n == None else len(n)
        if nn == 0: return 1
        for i in range(nn):
            while n[i] > 0 and n[i] <= nn \
            and n[i] != i+1 and n[n[i]-1]!=n[i]:
                self.swap(n, n[i] - 1, i)
        for i in range(nn):
            if i+1 != n[i]:
                return i+1
        return nn+1

if __name__ == "__main__":
    n = [1, 1]
    print(Solution().firstMissingPositive(n))