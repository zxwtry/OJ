#coding=utf-8

'''
    url: leetcode.com/problems/subsets
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月18日
    @details:    Solution: 59ms 49.62%
'''

class Solution(object):
    def search(self, n, ni, nn, s, si, sn, a):
        if si == sn:
            a.append(list(s))
            return
        for i in range(ni, nn):
            if i != 0 and n[i]==n[i-1]: continue
            s[si] = n[i]
            self.search(n, i+1, nn, s, si+1, sn, a)
        
    def subsets(self, n):
        """
        :type n: List[int]
        :rtype: List[List[int]]
        """
        if n == None or len(n) == 0: return []
        n.sort(key=None, reverse=False)
        a, nn = [], len(n)
        for i in range(nn+1):
            s = [0] * i
            self.search(n, 0, nn, s, 0, i, a)
        return a

if __name__ == "__main__":
    n = [1 ,2, 3]
    print(Solution().subsets(n))