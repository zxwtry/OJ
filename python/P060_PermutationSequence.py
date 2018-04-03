#coding=utf-8

'''
    url: leetcode.com/problems/permutation-sequence
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月12日
    @details:    Solution: 75ms 10.83%
'''

class Solution(object):
    def getPermutation(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: str
        """
        q, m, ti, a, k = [1]*n, [False]*n, 0, [""]*n, k-1
        for i in range(n-2, -1, -1):
            q[i] = q[i+1] * (n-1-i)
        for i in range(n-1):
            t, ti = k // q[i], 0
            while t > 0:
                ti += 1
                if m[ti-1]:continue
                t -= 1
                k -= q[i]
            while m[ti]: ti += 1
            m[ti] = True
            a[i] = str(ti+1)
        ti = 0
        while m[ti]: ti += 1
        a[n-1] = str(ti+1)
        return "".join(a)

if __name__ == "__main__":
    print(Solution().getPermutation(3, 6))
            
            
            
                