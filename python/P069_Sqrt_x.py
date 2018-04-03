#coding=utf-8

'''
    url: leetcode.com/problems/sqrtx/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月15日
    @details:    Solution: 56ms 55.71%
    @details:    Solution: 59ms 45.65%
'''
from math import sqrt

class Solution(object):
    def mySqrt(self, x):
        """
        :type x: int
        :rtype: int
        """
        return int(sqrt(x))
    
class Solution2(object):
    def mySqrt(self, x):
        """
        :type x: int
        :rtype: int
        """
        n, xx = 0, x
        while xx != 0:
            xx, n = xx // 2, n+1
        if n < 2: return x
        sqrt_max = 1 << (n // 2 + 1)
        sqrt_min = 1 << (n // 2 - 1)
#        print("%d %d" %(sqrt_max, sqrt_min))
        sqrt_mid = 0
        while sqrt_min < sqrt_max:
            sqrt_mid = (sqrt_max + sqrt_min+1) // 2
            val = sqrt_mid * sqrt_mid
            if val < x:
                sqrt_min = sqrt_mid
            elif val == x:
                return sqrt_mid
            else:
                sqrt_max = sqrt_mid - 1
        return sqrt_min
    
if __name__ == "__main__":
    print(Solution2().mySqrt(1))
                
        