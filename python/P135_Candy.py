#coding=utf-8

'''
    url: leetcode.com/problems/candy
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月18日
    @details:    Solution:  89ms 68.38%
'''

class Solution(object):
    def candy(self, r):
        """
        :type r: List[int]
        :rtype: int
        """
        rn = 0 if r == None else len(r)
        c, n, p, ans = [1] * rn, 0, 1, 0
        for i in range(1, rn, 1):
            if (r[i] > r[i-1]):
                c[i] = c[i-1] + 1
            else: c[i] = 1
        ans += max(1, c[rn-1])
        for i in range(rn-2, -1, -1):
            if (r[i] > r[i+1]):
                n = p+1
                ans += max(c[i], n)
            else: 
                n = 1
                ans += max(c[i], n)
            p = n
        return ans
    
