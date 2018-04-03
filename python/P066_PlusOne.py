#coding=utf-8

'''
    url: leetcode.com/problems/plus-one
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月14日
    @details:    Solution: 45ms 63.57%
'''

class Solution(object):
    def plusOne(self, d):
        """
        :type d: List[int]
        :rtype: List[int]
        """
        if d == None or len(d) == 0: return [1]
        dn = len(d)
        d[dn-1] += 1
        c = 0
        for i in range(dn-1, -1, -1):
            d[i] += c
            c = d[i] // 10
            d[i] = d[i] % 10
        if c != 0: d.insert(0, c)
        return d