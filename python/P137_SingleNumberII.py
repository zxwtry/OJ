#coding=utf-8

'''
    url: leetcode.com/problems/single-number-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月29日
    @details:    Solution:  49ms 71.57%
'''

from _operator import xor
class Solution(object):
    def singleNumber(self, ns):
        """
        :type ns: List[int]
        :rtype: int
        """
        s, ans = set(), 0
        for n in ns:
            if n in s:
                ans += n
            else:
                ans -= n
                ans -= n
                s.add(n)
        return ans // (-2)
    
class Solution2(object):
    def singleNumber(self, ns):
        one, two = 0, 0
        for n in ns:
            one = (xor(one, n) & (~ two))
            two = (xor(two, n) & (~ one))
        return one

if __name__ == "__main__":
    ns = [-1, 3, -1, -1]
    v = 7
    h = 5
    print(v & h)
    print(~v)
    print(Solution2().singleNumber(ns))
