#coding=utf-8

'''
    url: leetcode.com/problems/divide-two-integers/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月1日
    @details:    Solution: 62ms 47.40%
'''

from _testcapi import INT_MAX, INT_MIN

class Solution(object):
    def divide(self, dividend, divisor):
        """
        :type dividend: int
        :type divisor: int
        :rtype: int
        """
        #return divide by 0
        if divisor == 0: return INT_MAX
        #return INT_MIN / -1
        if dividend == INT_MIN and divisor == -1:
            return INT_MAX
        sign = (dividend > 0) ^ (divisor > 0)
        dividend, divisor = abs(dividend), abs(divisor)
        if divisor == 1: 
            return -dividend if sign else dividend
        ans, ans_add, val_add = 0, 1, divisor
        while dividend >= 0:
            ans_add, val_add = 1, divisor
            while val_add + val_add < dividend:
                val_add = val_add + val_add
                ans_add = ans_add + ans_add
            dividend -= val_add
            ans += ans_add
        ans -= 1
        return -ans if sign else ans 
    
if __name__ == "__main__":
    print(Solution().divide(-5, 1))